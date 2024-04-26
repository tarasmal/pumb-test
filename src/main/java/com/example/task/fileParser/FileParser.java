package com.example.task.fileParser;

import java.io.IOException;
import java.util.List;

public interface FileParser {

    List<Object> parse(byte[] file) throws IOException;
    boolean supports(String contentType);
}
