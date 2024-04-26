package com.example.task.fileParser;

import com.example.task.validator.FileValidator.CSVValidator;
import com.example.task.validator.FileValidator.FileValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Qualifier("csvParser")
public class CsvFileParser implements FileParser {
    private final FileValidator csvValidator = new CSVValidator();
    @Override
    public List<Object> parse(byte[] file) throws IOException {
        if (csvValidator.validate(file)) {
            System.out.println("CSV PARSER");
        }

        return null;
    }

    @Override
    public boolean supports(String contentType) {
        String requiredContentType = "text/csv";
        return requiredContentType.equals(contentType);
    }

}
