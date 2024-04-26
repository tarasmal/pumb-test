package com.example.task.service;


import com.example.task.exception.InvalidFileFormatException;
import com.example.task.fileParser.FileParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final List<FileParser> parsers;
    public FileService(List<FileParser> parsers) {
        this.parsers = parsers;
    }

    public void processContent(byte[] content, String contentType) throws IOException, InvalidFileFormatException {
        FileParser parser = parsers.
                stream().
                filter(p -> p.supports(contentType)).
                findFirst().orElseThrow(InternalError::new);
        parser.parse(content);
    }
}
