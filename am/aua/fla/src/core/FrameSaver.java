package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The FrameSaver class is responsible for saving a BufferedImage to a file.
 */
public class FrameSaver {
    private BufferedImage image;
    private String filePath;

    /**
     * Constructs a FrameSaver object with the specified image and file path.
     * 
     * @param image    the BufferedImage to be saved
     * @param filePath the file path where the image will be saved
     */
    public FrameSaver(BufferedImage image, String filePath) {
        this.image = image;
        this.filePath = filePath;
    }

    /**
     * Saves the image to the specified file path.
     */
    public void saveImage() {
        try {
            File output = new File(filePath);
            ImageIO.write(image, "png", output);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }
}
