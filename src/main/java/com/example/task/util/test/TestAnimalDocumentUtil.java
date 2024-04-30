package com.example.task.util.test;

import com.example.task.dto.AnimalDocument;

public class TestAnimalDocumentUtil {
    public static AnimalDocument createAnimalDocument(String name, String type, String sex, Double weight, Double cost, Integer category) {
        AnimalDocument animalDocument = new AnimalDocument();
        animalDocument.setName(name);
        animalDocument.setType(type);
        animalDocument.setSex(sex);
        animalDocument.setWeight(weight);
        animalDocument.setCost(cost);
        animalDocument.setCategory(category);
        return animalDocument;
    }
}
