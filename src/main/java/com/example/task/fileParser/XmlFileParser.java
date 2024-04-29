package com.example.task.fileParser;

import com.example.task.dto.Animal;
import com.example.task.mapper.AnimalMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
@Qualifier("xmlParser")
@RequiredArgsConstructor
public class XmlFileParser implements FileParser {
    private final AnimalMapper animalMapper;

    @Data
    private static class XmlRecord {
        private List<String> requiredFields = List.of("name", "type", "sex", "weight", "cost");
        private Map<String, String> xmlRecord;
        public Map<String, String> createXmlRecord(Node node) {
            xmlRecord = getDefaultMap();
            NodeList nodes = node.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node childNode = nodes.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    String tagName = childElement.getTagName().toLowerCase();
                    String textContent = childElement.getTextContent();
                    setField(tagName, textContent);
                }
            }
            return xmlRecord;
        }
        private Map<String, String> getDefaultMap() {
            Map<String, String> defaultMap = new HashMap<>();
            for (String field : requiredFields) {
                defaultMap.put(field, "");
            }
            return defaultMap;
        }
        private void setField(String tagName, String textContent) {
            if (requiredFields.contains(tagName)) {
                xmlRecord.put(tagName, parseTextContent(textContent));
            }
        }
        private String parseTextContent(String textContent) {
            return textContent;
        }

    }
    @Override
    public List<Animal> parse(byte[] file) throws IOException {
        List<Animal> animals = new ArrayList<>();
        try {
            final InputStream stream = new ByteArrayInputStream(file);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(stream);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("animal");
            for (int i = 0; i < nodeList.getLength(); i++) {
                XmlRecord xmlRecord = new XmlRecord();
                Node node = nodeList.item(i);
                Animal animal = animalMapper.toAnimal(xmlRecord.createXmlRecord(node));
                animals.add(animal);
            }
        }
        catch (ParserConfigurationException | SAXException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return animals;
    }

    @Override
    public boolean supports(String contentType) {
        String requiredContentType = "application/xml";
        return requiredContentType.equals(contentType);
    }

}
