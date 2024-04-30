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


class AnimalQueryBuilderTest {

    @Test
    void buildQuery() {
        List<String> sortBy = List.of("fieldName:asc", "otherField:desc");
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("type", "cat");
        filters.add("category", "mammal");
        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("type").is("cat"));
        expectedQuery.addCriteria(Criteria.where("category").is("mammal"));
        expectedQuery.with(Sort.by(Sort.Direction.ASC, "fieldName"));
        expectedQuery.with(Sort.by(Sort.Direction.DESC, "otherField"));
        AnimalQueryBuilder queryBuilder = new AnimalQueryBuilder(sortBy, filters);
        Query actualQuery = queryBuilder.buildQuery();

        assertThat(actualQuery.toString(), is(equalTo(expectedQuery.toString())));
    }
}