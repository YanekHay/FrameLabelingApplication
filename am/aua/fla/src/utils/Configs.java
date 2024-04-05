package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.io.File;

public class Configs {
    public final int WINDOW_WIDTH;
    public final int WINDOW_HEIGHT;
    public final int LINE_THICKNESS;
    public final int POINT_RADIUS;

    /**
     * Constructs a new Configs object by loading the configuration values from an XML file.
     *
     * @param configFilePath the path to the config file
     */
    public Configs(Path configFilePath) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Load the XML file into a Document
            Document document = builder.parse(new File(configFilePath.toString()));

            // Get the root element of the XML document
            Element root = document.getDocumentElement();

            // Get values from the XML elements
            this.WINDOW_WIDTH = Integer.parseInt(getNodeValue(root, "WINDOW_WIDTH"));
            this.WINDOW_HEIGHT = Integer.parseInt(getNodeValue(root, "WINDOW_HEIGHT"));
            this.LINE_THICKNESS = Integer.parseInt(getNodeValue(root, "LINE_THICKNESS"));
            this.POINT_RADIUS = Integer.parseInt(getNodeValue(root, "POINT_RADIUS"));
        } catch (IllegalArgumentException e) {
            // Handle any exceptions
            throw e;
        }
        catch (Exception e) {
            // Handle any exceptions
            throw new RuntimeException("Error loading configuration from XML file: " + e.getMessage());
        }
    }

    private String getNodeValue(Element element, String tagName) throws IllegalArgumentException{
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            throw new IllegalArgumentException("Element '" + tagName + "' not found in XML.");
        }
    }
}
