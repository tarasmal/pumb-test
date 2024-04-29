package com.example.task.mapper;

import com.example.task.dto.Animal;
import com.example.task.dto.AnimalDocument;
import org.mapstruct.*;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(target = "cost", source = "cost", qualifiedByName = "mapNullableStringToInt")
    @Mapping(target = "weight", source = "weight", qualifiedByName = "mapNullableStringToInt")
    Animal toAnimal(Map<String, String> animalRecord);

    @BeforeMapping
    default void addCategory(@MappingTarget AnimalDocument animalDocument, Animal animal) {
        animalDocument.setCategory(animal.determineCategory());
    }

    @Named("mapNullableStringToInt")
    default Integer mapNullableStringToInt(String nullableInput) {
        return nullableInput.isEmpty() ? 0 : Integer.parseInt(nullableInput);
    }
    AnimalDocument toAnimalDocument(Animal animal);
}
