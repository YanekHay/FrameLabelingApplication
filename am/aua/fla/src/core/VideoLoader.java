package core;

import java.awt.image.BufferedImage;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import org.bytedeco.javacv.*;

public class VideoLoader {

  private File path;
  private int width;
  private int height;
  private FFmpegFrameGrabber grabber;
  private int frameNumber = 0;

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }


  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public File getPath() {
    return path;
  }

  public void setPath(File file) {
    this.path = file;
  }

  public void chooseVideoFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.flv", "*.avi"));
    File file = fileChooser.showOpenDialog(null); 
    if (file != null) {
      setPath(file);
    }
  }

  public boolean loadVideo(File file) throws FrameGrabber.Exception {
    grabber = new FFmpegFrameGrabber(file);
    grabber.start();
    width = grabber.getImageWidth();
    height = grabber.getImageHeight();
    return true;
  }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
    
        return new ImageView(wr).getImage();
        }

        public Image getFrame(int frameNumber) throws FrameGrabber.Exception {
          grabber.setFrameNumber(frameNumber);
          Frame frame = grabber.grab();
          BufferedImage bufferedImage = new Java2DFrameConverter().getBufferedImage(frame);
          System.out.println("Buffered Image = " + bufferedImage);
          Image image = convertToFxImage(bufferedImage);
          System.out.println("Image = " + image);
          return image;
      }

  public Image nextFrame() throws FrameGrabber.Exception {
    Image nextImage = getFrame(frameNumber);
    System.out.println("Next Image = " + nextImage);
    return nextImage;
  }

  public Image prevFrame() throws FrameGrabber.Exception {
    Image prevImage = getFrame(frameNumber);
    System.out.println("Previous Image = " + prevImage);
    return prevImage;
  }
}
