package core;

import java.util.Scanner;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.ImageView;

public class FileLoader {
    private Scanner scanner;
    

    public FileLoader() {
        scanner = new Scanner(System.in);
    }

    public boolean isImageFile(String filePath) {
        return filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith(".jpeg");
    }

    public boolean isVideoFile(String filePath) {
        return filePath.endsWith(".mp4") || filePath.endsWith(".avi") || filePath.endsWith(".mov");
    }

    public void loadFiles() {
        System.out.println("Please enter the file path(s) to upload (separated by commas):");
        String input = scanner.nextLine();
        String[] filePaths = input.split(",");

        for (String filePath : filePaths) {
            if (isImageFile(filePath)) {
                Image image = new Image(new File(filePath).toURI().toString());
                ImageView imageView = new ImageView(image);
                // Add the imageView to your UI or perform any other desired operations
                System.out.println("Loading image: " + filePath);
            } else if (isVideoFile(filePath)) {
                Media media = new Media(new File(filePath).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                System.out.println("Loading video: " + filePath);
            }
                System.out.println("Unsupported file type: " + filePath);
            }
        }
    }
