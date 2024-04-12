package core;

import java.io.File;
import java.nio.file.Path;

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

    private Path path;
    private int height;
    private int width;
    private Media video;

    public VideoLoader() {
        super();
    }

    //TODO: Constructor with path of video (it sets the path of the video)
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
        this.loadVideo();
    }


    public void loadVideo() {
        this.video = new Media(path.toString());
        width = video.getWidth();
        height =  video.getHeight();
    }

    public BufferedImage getFrame(int frameNumber) {
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
        Media video = new Media(path.toString());

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