package com.example.task.validator.FileValidator;

import com.example.task.exception.InvalidFileFormatException;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;

public class CSVValidator implements FileValidator {

    private static final List<String> REQUIRED_HEADERS = List.of("Name", "Type", "Sex", "Weight", "Cost");

    @Override
    public boolean validate(byte[] data) throws IOException, InvalidFileFormatException {
        try (Reader reader = new InputStreamReader(new ByteArrayInputStream(data))) {
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .parse(reader);

            Set<String> headers = parser.getHeaderMap().keySet();
            if (!headers.containsAll(REQUIRED_HEADERS)) {
                throw new InvalidFileFormatException("Please provide all required fields: " + REQUIRED_HEADERS);
            }
        }
        return true;
    }


}