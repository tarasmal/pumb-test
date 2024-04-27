package com.example.task.controller;

import com.example.task.exception.InvalidFileFormatException;
import com.example.task.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        String errors = fileService.processContent(body, contentType);
        if (errors.isEmpty()) {
            return "Created";
        }
        else {
            System.out.println(errors);
            return errors;
        }
    }

}
