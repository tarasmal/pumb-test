package com.example.task.fileParser;

import com.example.task.validator.FileValidator.FileValidator;
import com.example.task.validator.FileValidator.XMLValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Qualifier("xmlParser")
public class XmlFileParser implements FileParser {
    private final FileValidator xmlValidator = new XMLValidator();
    @Override
    public List<Object> parse(byte[] file) throws IOException {
        if (xmlValidator.validate(file)){
            System.out.println("XML file parser");
        }
        return null;
    }

    @Override
    public boolean supports(String contentType) {
        String requiredContentType = "application/xml";
        return requiredContentType.equals(contentType);
    }

}
