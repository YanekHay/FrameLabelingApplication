package core;

import javafx.scene.image.Image;
import javafx.scene.shape.Path;

/**
 * Represents an image in the Frame Labeling Application.
 */
public class FLAImage {
    private final Path path;
    private final Image image;
    public final int width;
    public final int height;
    
    /**
     * Constructs a FLAImage object with the specified path.
     * 
     * @param path the path to the image file
     */
    public FLAImage(Path path) {
        this.path = path;
        this.image = new Image(path.toString());
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    /**
     * Returns the path of the image file.
     * 
     * @return the path of the image file
     */
    public Path getPath() {
        return path;
    }

    /**
     * Returns the image object.
     * 
     * @return the image object
     */
    public Image getImage() {
        return image;
    }

    /**
     * Loads and returns an image from the specified path.
     * 
     * @param path the path to the image file
     * @return the loaded image object
     */
    public static Image loadImage(Path path) {
        return new Image(path.toString());
    }

    /**
     * Loads and returns an image from the specified path.
     * 
     * @param path the path to the image file
     * @return the loaded image object
     */
    public static Image loadImage(String path) {
        return new Image(path);
    }
}
