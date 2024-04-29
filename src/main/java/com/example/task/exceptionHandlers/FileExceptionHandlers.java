package com.example.task.exceptionHandlers;

import com.example.task.exception.InvalidFileFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class FileExceptionHandlers {
    @ExceptionHandler(InvalidFileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidExtensionException(InvalidFileFormatException ex) {
        return ResponseEntity.badRequest().body(ex.getResult().getAllErrors().toString());
    }
}
