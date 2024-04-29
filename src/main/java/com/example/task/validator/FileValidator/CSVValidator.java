package com.example.task.validator.FileValidator;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CSVValidator implements Validator {

    @Getter
    private static final List<String> REQUIRED_HEADERS = List.of("name", "type", "sex", "weight", "cost");

    @Override
    public boolean supports(Class<?> clazz) {
        return byte[].class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!(target instanceof byte[] data)) {
            errors.reject("unsupported.class", "Unsupported data type. Expected byte array.");
            return;
        }
        try (Reader reader = new InputStreamReader(new ByteArrayInputStream(data))) {
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .parse(reader);
            Set<String> headers = parser.getHeaderMap().keySet().stream().map(String::toLowerCase).collect(Collectors.toSet());
            if (!headers.containsAll(REQUIRED_HEADERS)) {
                errors.reject("missing.headers", "Please provide all required fields: " + REQUIRED_HEADERS);
            }
        }
        catch (IOException e) {
            errors.reject("io.exception", "Error reading CSV data: " + e.getMessage());
        }

    }
}