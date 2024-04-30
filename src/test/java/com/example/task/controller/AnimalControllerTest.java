package com.example.task.controller;

import com.example.task.dto.AnimalDocument;
import com.example.task.service.AnimalQueryService;
import com.example.task.util.AnimalQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static com.example.task.util.test.TestAnimalDocumentUtil.createAnimalDocument;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnimalControllerTest {

    @Mock
    private AnimalQueryService animalQueryService;

    @InjectMocks
    private AnimalController animalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void getAnimals_WithFiltersAndSortBy_ReturnsListOfAnimalDocuments() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("type", "cat");
        filters.add("sex", "female");
        List<String> sortBy = List.of("name:desc");

        AnimalDocument animal1 = createAnimalDocument("Buddy", "cat", "female", 41D, 101D, 4);
        List<AnimalDocument> animals = Collections.singletonList(animal1);

        AnimalQueryBuilder animalQueryBuilder = new AnimalQueryBuilder(sortBy, filters);
        when(animalQueryService.getAnimals(animalQueryBuilder.buildQuery()))
                .thenReturn(animals);

        ResponseEntity<List<AnimalDocument>> responseEntity = animalController.getAnimals(filters, sortBy);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(animals, responseEntity.getBody());
    }
}