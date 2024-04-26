package com.example.task.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException (String message) {
        super(message);
    }
}
