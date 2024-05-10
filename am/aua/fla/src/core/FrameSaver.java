package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FrameSaver {
    private BufferedImage image;
    private String filePath;

    public FrameSaver(BufferedImage image, String filePath) {
        this.image = image;
        this.filePath = filePath;
    }

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
