package com.example.task.util;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;
import java.util.Set;


class AnimalQueryBuilderTest {

    @Test
    void buildQuery() {

        Set<String> validFilters = Set.of("type", "category");
        List<String> sortBy = List.of("fieldName:asc", "otherField:desc");
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("type", "cat");
        filters.add("category", "mammal");
        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("type").is("cat"));
        expectedQuery.addCriteria(Criteria.where("category").is("mammal"));
        expectedQuery.with(Sort.by(Sort.Direction.ASC, "fieldName"));
        expectedQuery.with(Sort.by(Sort.Direction.DESC, "otherField"));
        AnimalQueryBuilder queryBuilder = new AnimalQueryBuilder(validFilters, sortBy, filters);
        Query actualQuery = queryBuilder.buildQuery();

        assertThat(actualQuery.toString(), is(equalTo(expectedQuery.toString())));
    }
}