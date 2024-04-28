package com.example.task.service;

import com.example.task.dto.Animal.Animal;
import com.example.task.dto.AnimalDocument;
import com.example.task.mapper.AnimalMapper;
import com.example.task.mapper.AnimalMapperImpl;
import com.example.task.validator.AnimalValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {AnimalMapperImpl.class})
class AnimalServiceTest {
    @Mock
    private AnimalValidator animalValidator;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private AnimalMapper animalMapper;

    @InjectMocks
    private AnimalService animalService;

    private Animal animalValid, animalInvalid;
    private AnimalDocument document;

    @BeforeEach
    void setUp() {
        animalValid = mock(Animal.class);
        animalInvalid = mock(Animal.class);
        document = mock(AnimalDocument.class);
        when(animalMapper.toAnimalDocument(any(Animal.class))).thenReturn(document);
    }
    @Test
    void testSaveAllValidAnimals() {
        when(animalValidator.supports(Animal.class)).thenReturn(true);
        animalService.saveToDb(Arrays.asList(animalValid, animalValid));
        verify(mongoTemplate, times(2)).save(document, "animals");
    }

    @Test
    void testHandleValidationErrors() {
        when(animalValidator.supports(Animal.class)).thenReturn(true);
        verify(mongoTemplate, never()).save(animalInvalid, "animals");
    }
}