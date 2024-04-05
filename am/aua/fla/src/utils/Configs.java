package utils;

import java.nio.file.Path;
import org.w3c.dom.Element;

public class Configs {
    public static final int WINDOW_WIDTH;
    public static final int WINDOW_HEIGHT;
    public static final int LINE_THICKNESS;
    public static final int POINT_RADIUS;

    static {
        Path configFilePath = Path.of("am/aua/fla/configs/MainConfig.xml");// specify your config file path here
        Element configRoot = XMLParser.getRootElement(configFilePath);
        WINDOW_WIDTH = Integer.parseInt(XMLParser.getNodeValue(configRoot, "WINDOW_WIDTH"));
        WINDOW_HEIGHT = Integer.parseInt(XMLParser.getNodeValue(configRoot, "WINDOW_HEIGHT"));
        LINE_THICKNESS = Integer.parseInt(XMLParser.getNodeValue(configRoot, "LINE_THICKNESS"));
        POINT_RADIUS = Integer.parseInt(XMLParser.getNodeValue(configRoot, "POINT_RADIUS"));
    }
}
