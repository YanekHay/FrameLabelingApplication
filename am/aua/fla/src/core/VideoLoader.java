package core;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import javafx.scene.SnapshotParameters;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.stage.*;

public class VideoLoader {

    private File path;
    private int height;
    private int width;
    private Media video;
    private MediaPlayer mediaPlayer;
    

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

    public void chooseVideoFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.flv", "*.avi", "*.mov", "*.wmv", "*.webm")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null)
        {
            setPath(file);
        }

    }

    public void loadVideo(File file) {
        URI uri = file.toURI();
        String path = uri.toString();
        video = new Media(path);
        mediaPlayer = new MediaPlayer(video);
    }

    public Image getFrame(int frameNumber) {
        double frameRate = (double) video.getMetadata().get("framerate");
        double frameDuration = 1 / frameRate; // in seconds
    
        mediaPlayer.setAutoPlay(false);
        frameNumber = calculateFrameNumber(mediaPlayer.getCurrentTime(), frameDuration);
    
        // Create a new MediaView object with the MediaPlayer
        MediaView mediaView = new MediaView(mediaPlayer);
    
        // Set the desired frame time for the MediaView
        Duration frameTime = Duration.seconds(frameDuration * frameNumber);
        mediaPlayer.setStartTime(frameTime);
        mediaPlayer.setStopTime(frameTime.add(Duration.millis(1)));
    
        // Create a SnapshotParameters object to capture the frame
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
    
        // Capture the frame as an image from the MediaView
        Image frameImage = mediaView.snapshot(parameters, null);
    
        return frameImage;
    }

    private static int calculateFrameNumber(Duration duration, double frameDuration) {
        return (int) (duration.toSeconds() / frameDuration);
    }

    public Image getFrameAtTime(Duration time) {
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.seek(time);

        // Create a SnapshotParameters object to capture the frame
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);


        MediaView mediaView = new MediaView(mediaPlayer);
        // Capture the frame as an image
        Image frameImage = mediaView.snapshot(parameters, null);

        return frameImage;
    }

    public Image nextFrame(int frameNumber) {
        return getFrame(frameNumber + 1);
    }

    public Image previousFrame(int frameNumber) {
        return getFrame(frameNumber - 1);
    }
}