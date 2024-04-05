package utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.io.File;

public class XMLParser {

    public static Element getRootElement(Path configFilePath) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Load the XML file into a Document
            Document document = builder.parse(new File(configFilePath.toString()));

            // Get the root element of the XML document
            return document.getDocumentElement();
        } catch (Exception e) {
            // Handle any exceptions
            throw new RuntimeException("Error loading configuration from XML file: " + e.getMessage());
        }
    }
    
    public static String getNodeValue(Element element, String tagName) throws IllegalArgumentException{
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            throw new IllegalArgumentException("Element '" + tagName + "' not found in XML.");
        }
    }

}
