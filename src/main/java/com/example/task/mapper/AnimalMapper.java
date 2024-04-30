package com.example.task.mapper;

import com.example.task.dto.Animal;
import com.example.task.dto.AnimalDocument;
import org.mapstruct.*;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    @Mapping(target = "cost", source = "cost", qualifiedByName = "mapNullableStringToDouble")
    @Mapping(target = "weight", source = "weight", qualifiedByName = "mapNullableStringToDouble")
    Animal toAnimal(Map<String, String> animalRecord);

    @BeforeMapping
    default void addCategory(@MappingTarget AnimalDocument animalDocument, Animal animal) {
        animalDocument.setCategory(animal.determineCategory());
    }

    @Named("mapNullableStringToDouble")
    default Double mapNullableStringToDouble(String nullableInput) {
        return nullableInput.isEmpty() ? 0 : Double.parseDouble(nullableInput);
    }
    AnimalDocument toAnimalDocument(Animal animal);
}
