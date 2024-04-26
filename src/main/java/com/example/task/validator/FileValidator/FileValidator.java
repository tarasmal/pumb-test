package com.example.task.validator.FileValidator;

import java.io.IOException;

public interface FileValidator {
    boolean validate(byte[] data) throws IOException;
}
