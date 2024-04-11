package core;

import java.io.File;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class VideoLoader extends FileLoader {

    private File path;
    private int height;
    private int width;

    public VideoLoader() {
        super();
    }


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

        public void setPath(File path) {
            this.path = path;
        }


        public void loadImage() {
            loadFiles();
            Media video = new Media(path.toURI().toString());
            width = (int) video.getWidth();
            height = (int) video.getHeight();
 
        }

            public BufferedImage getFrame(int frameNumber) {
                // Load the video
                loadFiles();
                Media video = new Media(path.toURI().toString());

                // Create a MediaPlayer to play the video
                MediaPlayer mediaPlayer = new MediaPlayer(video);
                double frameRate = (double) video.getMetadata().get("framerate");
                double frameDuration = 1/frameRate; // in seconds

                mediaPlayer.setAutoPlay(false);

                // Create a MediaView to display the video
                MediaView mediaView = new MediaView(mediaPlayer);

                // Set the size of the MediaView to match the video dimensions
                mediaView.setFitWidth(width);
                frameNumber = calculateFrameNumber(mediaPlayer.getCurrentTime(), frameDuration);
                mediaPlayer.seek(new Duration(frameNumber * frameDuration * 1000));

                // Create a SnapshotParameters object to capture the frame
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);

                // Capture the frame as an image
                Image frameImage = mediaView.snapshot(parameters, null);

                // Convert the image to a BufferedImage
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(frameImage, null);

                // Return the frame as a BufferedImage
                return bufferedImage;
            }

            private int calculateFrameNumber(Duration duration, double frameDuration) {
                return (int) (duration.toSeconds() / frameDuration);
            }


            public BufferedImage getFrameAtTime(Duration time) {
                // Load the video
                loadFiles();
                Media video = new Media(path.toURI().toString());

                // Create a MediaPlayer to play the video
                MediaPlayer mediaPlayer = new MediaPlayer(video);

                mediaPlayer.setAutoPlay(false);

                // Create a MediaView to display the video
                MediaView mediaView = new MediaView(mediaPlayer);

                // Set the size of the MediaView to match the video dimensions
                mediaView.setFitWidth(width);
                mediaPlayer.seek(new Duration(time.toSeconds() * 1000));

                // Create a SnapshotParameters object to capture the frame
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);

                // Capture the frame as an image
                Image frameImage = mediaView.snapshot(parameters, null);

                // Convert the image to a BufferedImage
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(frameImage, null);

                // Return the frame as a BufferedImage
                return bufferedImage;
            }

            public BufferedImage nextFrame(int frameNumber){
               return getFrame(frameNumber + 1);
            }
}