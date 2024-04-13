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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class VideoLoader {

    private Path path;
    private int height;
    private int width;
    private Media video;

    public VideoLoader() {
        super();
    }

    public void chooseVideoFile() {
        // Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Choose Video File");

        // Set the initial directory of the file chooser dialog
        fileChooser.setInitialDirectory(new File("C:/"));

        // Add filters to the file chooser dialog
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Show the file chooser dialog
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            // Set the path of the selected file
            setPath(selectedFile.toPath());

            // Load the video
            loadVideo();
        }
    }

    public VideoLoader(Path path) {
        super();
        this.path = path;
        this.loadVideo();
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
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

    public BufferedImage previousFrame(int frameNumber){
        return getFrame(frameNumber - 1);
    }

    public void playVideo() {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void pauseVideo() {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.pause();
    }

    public void stopVideo() {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.stop();
    }

    public void seekVideo(Duration time) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.seek(time);
    }


    public void setOnReady(Runnable action) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setOnReady(action);
    }

    public void setOnPlaying(Runnable action) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setOnPlaying(action);
    }

    public void setOnPaused(Runnable action) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setOnPaused(action);
    }

    public void setOnStopped(Runnable action) {
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setOnStopped(action);
    }

}