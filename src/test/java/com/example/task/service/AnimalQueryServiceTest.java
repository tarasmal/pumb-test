package com.example.task.service;

import com.example.task.dto.AnimalDocument;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static com.example.task.util.test.TestAnimalDocumentUtil.createAnimalDocument;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnimalQueryServiceTest {

    private final MongoTemplate mongoTemplate = mock(MongoTemplate.class);
    private final AnimalQueryService animalQueryService = new AnimalQueryService(mongoTemplate);
    @ParameterizedTest
    @MethodSource("queryProvider")
    void testGetAnimals(Query query, List<AnimalDocument> expectedAnimals) {
        when(mongoTemplate.find(query, AnimalDocument.class, "animals")).thenReturn(expectedAnimals);
        List<AnimalDocument> actualAnimals = animalQueryService.getAnimals(query);
        System.out.println(actualAnimals.toString());
        assertEquals(expectedAnimals, actualAnimals);
    }


    private static Object[][] queryProvider() {
        return new Object[][] {
                { new Query(Criteria.where("type").is("dog")), Arrays.asList(
                        createAnimalDocument("a", "dog", "c", 1D, 2D, 1),
                        createAnimalDocument("aa", "dog", "cc", 11D, 200D, 4)
                )},
                { new Query(Criteria.where("type").is("cat")), Arrays.asList(
                        createAnimalDocument("a", "cat", "c", 1D, 2D, 1),
                        createAnimalDocument("a", "cat", "c", 1D, 2D, 1)
                )},
        };
    }
}