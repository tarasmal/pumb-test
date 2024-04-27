package com.example.task.validator.FileValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.validation.Errors;
import java.nio.charset.StandardCharsets;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CSVValidatorTest {
    private CSVValidator validator;
    @BeforeEach
    void setUp() {
        validator = new CSVValidator();
    }

    @ParameterizedTest
    @ValueSource(classes = {
            Integer.class,
            String.class,
            Object.class,
    })
    void supportsShouldReturnFalseForOtherClasses (Class <?> clazz) {
        assertFalse(validator.supports(clazz));
    }

    @Test void shouldReturnTrueForByteArray() {
        assertTrue(validator.supports(byte[].class));
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "Name,Type,Sex\nBuddy,Dog,Male",
            "",
            "sd wqe sds a",

    })
    void testValidateWithMissingHeaders(String csvData) {
        byte[] data = csvData.getBytes(StandardCharsets.UTF_8);
        Errors errors = mock(Errors.class);
        validator.validate(data, errors);
        verify(errors).reject("missing.headers", "Please provide all required fields: " + CSVValidator.getREQUIRED_HEADERS());
    }
    @Test
    void testValidateWithAllHeaders() {
        String csvData = "Name,Type,Sex,Weight,Cost\nBuddy,Dog,Male,30,100";
        byte[] data = csvData.getBytes(StandardCharsets.UTF_8);
        Errors errors = mock(Errors.class);
        validator.validate(data, errors);
        verify(errors, never()).reject(anyString(), anyString());
    }
}