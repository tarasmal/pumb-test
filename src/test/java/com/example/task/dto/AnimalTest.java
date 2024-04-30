package com.example.task.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    static Stream<TestData> provideTestData() {
        return Stream.of(
                new TestData(20D, Category.FIRST.getValue()),
                new TestData(21D, Category.SECOND.getValue()),
                new TestData(40D, Category.SECOND.getValue()),
                new TestData(41D, Category.THIRD.getValue()),
                new TestData(60D, Category.THIRD.getValue()),
                new TestData(61D, Category.FOURTH.getValue())
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testDetermineCategory(TestData testData) {
        Animal animal = new Animal();
        animal.setCost(testData.getCost());
        assertEquals(testData.getExpectedCategory(), animal.determineCategory());
    }
    @Data
    @RequiredArgsConstructor
    private static class TestData {
        private final Double cost;
        private final Integer expectedCategory;
    }
}