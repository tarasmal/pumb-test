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
                new TestData(20, Category.FIRST),
                new TestData(21, Category.SECOND),
                new TestData(40, Category.SECOND),
                new TestData(41, Category.THIRD),
                new TestData(60, Category.THIRD),
                new TestData(61, Category.FOURTH)
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
        private final int cost;
        private final Category expectedCategory;
    }
}