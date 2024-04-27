package com.example.task.service;


import com.example.task.dto.Animal.Animal;
import com.example.task.exception.InvalidFileFormatException;
import com.example.task.fileParser.FileParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final List<FileParser> parsers;
    private final AnimalService animalService;

    public String processContent(byte[] content, String contentType) throws IOException, InvalidFileFormatException {
        FileParser parser = parsers.
                stream().
                filter(p -> p.supports(contentType)).
                findFirst().orElseThrow(InternalError::new);
        List<Animal> animals = parser.parse(content);
        return animalService.saveToDb(animals);

    }
}
