package com.example.task.controller;

import com.example.task.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FIleControllerTest {
    @Mock
    private FileService fileService;

    @InjectMocks
    private FIleController fileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void uploadFileContent_WithValidCsvContent_ReturnsCreated() throws IOException {
        byte[] body = "Name,Type,Sex,Weight,Cost\nBuddy,cat,female,41,78\n".getBytes();
        String contentType = "text/csv";
        when(fileService.processContent(body, contentType)).thenReturn("");
        String response = fileController.uploadFileContent(body, contentType);
        assertEquals("Created", response);
    }
}