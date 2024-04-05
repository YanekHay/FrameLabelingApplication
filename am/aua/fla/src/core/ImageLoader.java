package core;

import java.io.File;
import javafx.scene.image.Image;
import java.util.Scanner;

public class ImageLoader extends FileLoader {

    private File path;
    private Image image;
    private int width;
    private int height;

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void loadImage() {
        System.out.println("Please enter the image path to upload:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        path = new File(input);
        image = new Image(path.toURI().toString());
        width = (int) image.getWidth();
        height = (int) image.getHeight();
    }
    
}
