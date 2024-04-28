package com.example.task.mapper;

import com.example.task.dto.Animal.Animal;
import com.example.task.dto.Animal.Category;
import com.example.task.dto.AnimalDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {AnimalMapperImpl.class})
class AnimalMapperTest {

    @Autowired
    private AnimalMapper animalMapper;

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
        expectedAnimalDocument.setCategory(Category.FIRST);
        animal.setName("a");
        animal.setType("b");
        animal.setSex("c");
        animal.setCost(1);
        animal.setWeight(2);
        assertEquals(expectedAnimalDocument, animalMapper.toAnimalDocument(animal));
    }
}