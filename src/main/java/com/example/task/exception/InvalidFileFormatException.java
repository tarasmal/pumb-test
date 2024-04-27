package com.example.task.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

public class InvalidFileFormatException extends RuntimeException {
    @Getter private final BindingResult result;
    public InvalidFileFormatException (BindingResult result) {
        super();
        this.result = result;
    }
}
