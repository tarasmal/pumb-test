package com.example.task.service;

import com.example.task.dto.AnimalDocument;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

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

    private static AnimalDocument createAnimalDocument(String name, String type, String sex, Integer weight, Integer cost, Integer category) {
        AnimalDocument animalDocument = new AnimalDocument();
        animalDocument.setName(name);
        animalDocument.setType(type);
        animalDocument.setSex(sex);
        animalDocument.setWeight(weight);
        animalDocument.setCost(cost);
        animalDocument.setCategory(category);
        return animalDocument;
    }
    private static Object[][] queryProvider() {
        return new Object[][] {
                { new Query(Criteria.where("type").is("dog")), Arrays.asList(
                        createAnimalDocument("a", "dog", "c", 1, 2, 1),
                        createAnimalDocument("aa", "dog", "cc", 11, 200, 4)
                )},
                { new Query(Criteria.where("type").is("cat")), Arrays.asList(
                        createAnimalDocument("a", "cat", "c", 1, 2, 1),
                        createAnimalDocument("a", "cat", "c", 1, 2, 1)
                )},
        };
    }
}