package com.example.task.controller;

import com.example.task.exception.InvalidFileFormatException;
import com.example.task.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/files/uploads")
public class FIleController {
    private final FileService fileService;

    public FIleController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(consumes = {"text/csv", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public String uploadFileContent(@RequestBody byte[] body, @RequestHeader("Content-Type") String contentType) throws IOException {
        fileService.processContent(body, contentType);
        return "";
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidExtensionException(InvalidFileFormatException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
