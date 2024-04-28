package com.example.task.fileParser;

import com.example.task.dto.Animal.Animal;
import com.example.task.exception.InvalidFileFormatException;
import com.example.task.mapper.AnimalMapper;
import com.example.task.validator.FileValidator.CSVValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("csvParser")
@RequiredArgsConstructor
public class CsvFileParser implements FileParser {
    private final CSVValidator csvValidator;
    private final AnimalMapper animalMapper;

    @Override
    public List<Animal> parse(byte[] file) throws IOException, InvalidFileFormatException {
        BindingResult result = new BeanPropertyBindingResult(file, "csvData");
        csvValidator.validate(file, result);
        if (result.hasErrors()) {
            throw new InvalidFileFormatException(result);
        }
        List<Animal> animals = new ArrayList<>();
        Reader in = new InputStreamReader(new ByteArrayInputStream(file));
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            Animal animal = animalMapper.toAnimal(record.toMap());
            animals.add(animal);
        }
        return animals;
    }

    @Override
    public boolean supports(String contentType) {
        String requiredContentType = "text/csv";
        return requiredContentType.equals(contentType);
    }

}
