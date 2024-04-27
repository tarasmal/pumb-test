package com.example.task.dto.Animal;

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
        animal.setCost(testData.cost);
        assertEquals(testData.expectedCategory, animal.determineCategory());
    }
    private static class TestData {
        int cost;
        Category expectedCategory;
        TestData(int cost, Category expectedCategory) {
            this.cost = cost;
            this.expectedCategory = expectedCategory;
        }
    }
}