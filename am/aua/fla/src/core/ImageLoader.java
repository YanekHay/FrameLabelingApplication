package core;

import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ImageLoader {

    private File path;
    private Image image;
    private int width;
    private int height;

    public ImageLoader() {
        super();
    }

    public File getPath() {
        return path;
    }

    public boolean isImageFile(String filePath) {
        return filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith(".jpeg");
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

    public void chooseImageFile(){
        // Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Choose Image File");

        // Set the initial directory of the file chooser dialog
        fileChooser.setInitialDirectory(new File("C:/"));

        // Add filters to the file chooser dialog
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Show the file chooser dialog
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            // Set the path of the selected file
            setPath(selectedFile);

            // Load the image
            loadImage();
        }
    
    }

    public void loadImage() {
        image = new Image(path.toURI().toString());
        width = (int) image.getWidth();
        height = (int) image.getHeight();
    }
    
}
