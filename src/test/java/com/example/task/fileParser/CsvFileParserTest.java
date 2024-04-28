package com.example.task.fileParser;

import com.example.task.dto.Animal;

import com.example.task.mapper.AnimalMapper;
import com.example.task.validator.FileValidator.CSVValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CsvFileParserTest {

    @Mock
    AnimalMapper mapper;
    @Mock CSVValidator validator;
    @BeforeEach
    void init_mocks(){
        MockitoAnnotations.openMocks(this);
    }
    static Stream<CsvFileParserTest.TestData> provideTestData() {
        return Stream.of(
                new CsvFileParserTest.TestData( "", 0),
                new CsvFileParserTest.TestData("Name,Type,Sex,Weight,Cost\nBuddy,Dog,Male,20,100\nLucy,Cat,Female,15,150\nCharlie,Dog,Male,30,200\nBella,Cat,Female,10,120", 4)
        );
    }
    @ParameterizedTest
    @MethodSource("provideTestData")
    void parse(TestData testData) {
        try {
            byte[] data = testData.content.getBytes(StandardCharsets.UTF_8);
            when(mapper.toAnimal(any())).thenReturn(new Animal());
            CsvFileParser parser = new CsvFileParser(validator, mapper);
            List<Animal> result = parser.parse(data);
            verify(validator).validate(eq(data), any(BindingResult.class));
            System.out.println(result.toString());
            assertEquals(testData.expectedCount, result.size());
        }

        catch (IOException ignore) {}
    }
    @Test
    void supports() {
        CsvFileParser parser = new CsvFileParser(validator, mapper);
        assertTrue(parser.supports("text/csv"));
    }
    @Data
    @RequiredArgsConstructor
    private static class TestData {
        private final String content;
        private final int expectedCount;
    }
}