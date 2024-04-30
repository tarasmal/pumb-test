package com.example.task.fileParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;

class XmlFileParserTest {

    @InjectMocks
    private XmlFileParser xmlFileParser;
    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void parse_WithInvalidXml_ThrowsIOException() {
        byte[] invalidXmlBytes = "Invalid XML".getBytes();

        assertThrows(RuntimeException.class, () -> xmlFileParser.parse(invalidXmlBytes));
    }

    @Test
    void supports() {
    }
}