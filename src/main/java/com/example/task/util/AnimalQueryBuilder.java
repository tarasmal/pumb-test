package com.example.task.util;

import com.example.task.dto.Filter;
import com.example.task.dto.SortBy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnimalQueryBuilder {
    private final Set<String> validFilters;
    private final List<String> sortBy;
    private final MultiValueMap<String, String> filters;

    public Query buildQuery() {
        Query query = new Query();
        addFilterCriterias(parseFilters(), query);
        addSortCriterias(parseSortCriterias(), query);
        return query;
    }

    private List<SortBy> parseSortCriterias() {
        return sortBy != null ? sortBy.stream().map(SortBy::new).toList() : Collections.emptyList();
    }
    private List<Filter> parseFilters(){
        return filters.entrySet().stream()
                .filter(entry -> validFilters.contains(entry.getKey()))
                .flatMap(entry -> entry.getValue().stream().map(value -> new Filter(entry.getKey(), value))).collect(Collectors.toList());
    }
    private void addSortCriterias(List<SortBy> sortCriterias, Query query) {
        if (sortCriterias != null) {
            for (SortBy sortBy : sortCriterias) {
                query.with(Sort.by(sortBy.getDirection(), sortBy.getFieldName()));
            }
        }
    }
    private void addFilterCriterias(List<Filter> filters, Query query) {
        if (filters != null) {
            for (Filter filter : filters) {
                query.addCriteria(Criteria.where(filter.getFieldName()).is(filter.getValue()));
            }
        }
    }
}
