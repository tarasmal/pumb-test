package com.example.task.fileParser;

import com.example.task.dto.Animal;

import java.io.IOException;
import java.util.List;

public interface FileParser {

    List<Animal> parse(byte[] file) throws IOException;
    boolean supports(String contentType);
}
