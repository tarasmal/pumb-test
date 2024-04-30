package com.example.task.validator;

import com.example.task.dto.Animal;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AnimalValidatorTest {
    private AnimalValidator animalValidator;

    @BeforeEach
    void setUp () {
        animalValidator = new AnimalValidator();
    }
    @Data
    @RequiredArgsConstructor
    private static class TestDataForSupportsMethod {
        private final Class<?> target;
        private final boolean result;
    }

    static Stream<AnimalValidatorTest.TestDataForSupportsMethod> provideTestDataForSupportsMethod() {
        return Stream.of(
                new AnimalValidatorTest.TestDataForSupportsMethod(String.class, false),
                new AnimalValidatorTest.TestDataForSupportsMethod(Integer.class, false),
                new AnimalValidatorTest.TestDataForSupportsMethod(Boolean.class, false),
                new AnimalValidatorTest.TestDataForSupportsMethod(Animal.class, true)
        );
    }
    @ParameterizedTest
    @MethodSource("provideTestDataForSupportsMethod")
    void supports(TestDataForSupportsMethod data) {
        assertEquals(animalValidator.supports(data.target), data.result);
    }

    @ParameterizedTest
    @CsvSource({
            "a, b, c, 1, 2, false",
            " , b, c, 1, 2, true",
            "a,  , c, 1, 1, true",
            "a, b,  , 1, 2, true",
            "a, b, c, 0, 2, true",
            "a, b, c, 1, -1, true",
            "a, b, c, 1, 0, true"
    })
    void testAnimalValidation(String name, String type, String sex, int weight, int cost, boolean expectedErrors) {
        BindingResult result = prepareAndValidateAnimal(name, type, sex, weight, cost);
        assertEquals(expectedErrors, result.hasErrors());
    }
    private BindingResult prepareAndValidateAnimal(String name, String type, String sex, double weight, double cost) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setType(type);
        animal.setSex(sex);
        animal.setWeight(weight);
        animal.setCost(cost);
        BindingResult result = new BeanPropertyBindingResult(animal, "animal");
        animalValidator.validate(animal, result);
        return result;
    }
}