package com.example.task.mapper;

import com.example.task.dto.Animal;
import com.example.task.dto.Category;
import com.example.task.dto.AnimalDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class AnimalMapperTest {
    private AnimalMapper animalMapper;
    @BeforeEach
    void setUp() {
        animalMapper = Mappers.getMapper(AnimalMapper.class);
    }

    @Test
    void toAnimal() {
        Animal expectedAnimal = new Animal();
        expectedAnimal.setName("a");
        expectedAnimal.setType("b");
        expectedAnimal.setSex("c");
        expectedAnimal.setCost(1);
        expectedAnimal.setWeight(2);
        Map<String, String> testedMap = new HashMap<>();
        testedMap.put("name", "a");
        testedMap.put("type", "b");
        testedMap.put("sex", "c");
        testedMap.put("cost", "1");
        testedMap.put("weight", "2");
        assertEquals(expectedAnimal, animalMapper.toAnimal(testedMap));

    }

    @Test
    void toAnimalDocument() {
        AnimalDocument expectedAnimalDocument = new AnimalDocument();
        Animal animal = new Animal();
        expectedAnimalDocument.setName("a");
        expectedAnimalDocument.setType("b");
        expectedAnimalDocument.setSex("c");
        expectedAnimalDocument.setCost(1);
        expectedAnimalDocument.setWeight(2);
        expectedAnimalDocument.setCategory(Category.FIRST.getValue());
        animal.setName("a");
        animal.setType("b");
        animal.setSex("c");
        animal.setCost(1);
        animal.setWeight(2);
        assertEquals(expectedAnimalDocument, animalMapper.toAnimalDocument(animal));
    }
}