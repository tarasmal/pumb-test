package com.example.task.fileParser;

import com.example.task.dto.Animal;
import com.example.task.mapper.AnimalMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier("xmlParser")
@RequiredArgsConstructor
public class XmlFileParser implements FileParser {

    private Map<String, String> xmlRecord;
    private final AnimalMapper animalMapper;
    @Override
    public List<Animal> parse(byte[] file) throws IOException {
        try {
            List<Animal> animals = new ArrayList<>();
            final InputStream stream = new ByteArrayInputStream(file);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(stream);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("animal");
            int animalsCount = nodeList.getLength();
            System.out.println(animalsCount);
            for (int i = 0; i < animalsCount; i++) {
                xmlRecord = new HashMap<>();
                Node node = nodeList.item(i);
                Element element = (Element) node;
                setField("name", element);
                setField("type", element);
                setField("sex", element);
                setField("weight", element);
                setField("cost", element);
                animals.add(animalMapper.toAnimal(xmlRecord));

            }
            return animals;
        }
        catch (ParserConfigurationException | SAXException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    private void setField(String fieldName, Element element) {
        Node node = element.getElementsByTagName(fieldName).item(0);
        String value = node == null ? "" : node.getTextContent();
        xmlRecord.put(fieldName, value);
    }

    @Override
    public boolean supports(String contentType) {
        String requiredContentType = "application/xml";
        return requiredContentType.equals(contentType);
    }

}
