package core;

import java.io.File;
import java.net.URI;
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

    private URI path;
    private int height;
    private int width;
    private Media video;
    private MediaPlayer mediaPlayer; // Added mediaPlayer attribute

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
          setPath(selectedFile.toURI());
    
          // Load the video
          loadVideo();
        }
    }

    public VideoLoader(Path path) {
        super();
        this.path = path.toUri();
        loadVideo(); // Load video here
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

    public URI getPath() {
        return path;
    }

    public void setPath(URI path) {
        this.path = path;
    }

    private void loadVideo() {
        if (video == null) { // Check if video is already loaded
            this.video = new Media(path.toString());
            mediaPlayer = new MediaPlayer(video); // Create mediaPlayer here
            width = video.getWidth();
            height = video.getHeight();
        }
    }

    public Image getFrame(int frameNumber) {
        loadVideo();
        double frameRate = (double) video.getMetadata().get("framerate");
        double frameDuration = 1 / frameRate; // in seconds

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

        return frameImage;
    }

    private int calculateFrameNumber(Duration duration, double frameDuration) {
        return (int) (duration.toSeconds() / frameDuration);
    }

    public BufferedImage getFrameAtTime(Duration time) {

        loadVideo();

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

    public Image nextFrame(int frameNumber) {
        return getFrame(frameNumber + 1);
    }

    public Image previousFrame(int frameNumber) {
        return getFrame(frameNumber - 1);
    }

}
