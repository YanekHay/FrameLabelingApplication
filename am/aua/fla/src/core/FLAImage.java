package core;

import javafx.scene.image.Image;
import javafx.scene.shape.Path;

public class FLAImage {
    private final Path path;
    private final Image image;
    public final int width;
    public final int height;
    
    public FLAImage(Path path) {
        this.path = path;
        this.image = new Image(path.toString());
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
    }

    public Path getPath() {
        return path;
    }

    public Image getImage() {
        return image;
    }

    public static Image loadImage(Path path) {
        return new Image(path.toString());
    }

    public static Image loadImage(String path) {
        return new Image(path);
    }
}
