package com.example.task.validator.FileValidator;

import com.example.task.exception.InvalidFileFormatException;

import java.io.IOException;

public interface FileValidator {
    void validate(byte[] data) throws IOException, InvalidFileFormatException;
}
