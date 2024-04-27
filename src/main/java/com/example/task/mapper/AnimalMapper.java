package com.example.task.mapper;

import com.example.task.dto.Animal.Animal;
import com.example.task.dto.AnimalDocument;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    Animal toAnimal(Map<String, String> animalRecord);

    @BeforeMapping
    default void addCategory(@MappingTarget AnimalDocument animalDocument, Animal animal) {
        animalDocument.setCategory(animal.determineCategory());
    }
    AnimalDocument toAnimalDocument(Animal animal);


}
