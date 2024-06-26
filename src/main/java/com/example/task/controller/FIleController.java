package com.example.task.controller;

import com.example.task.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/files/uploads")
@RequiredArgsConstructor
public class FIleController {
    private final FileService fileService;

    @PostMapping(consumes = {"text/csv", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public String uploadFileContent(@RequestBody byte[] body, @RequestHeader("Content-Type") String contentType) throws IOException {
        String errors = fileService.processContent(body, contentType);
        return errors.isEmpty() ? "Created" : errors;
    }

}
