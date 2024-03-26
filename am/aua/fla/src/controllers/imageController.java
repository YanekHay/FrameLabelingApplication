package controllers;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public class imageController {
    @FXML
    private ImageView imageView;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane scrollPaneBody;
    @FXML
    private AnchorPane scrollPaneFace;

    @FXML
    void zoom(ScrollEvent event) {
        if (!event.isControlDown()){
            return;
        }

        double scaleFactor = 1.1; // Define a scale factor for zooming
        double pivotX = event.getX(); // Get the x coordinate of the mouse cursor
        double pivotY = event.getY(); // Get the y coordinate of the mouse cursor

        // Get the bounds of the imageView in the local space
        Bounds boundsInLocal = imageView.getBoundsInLocal();

        // Calculate the center of the image
        double centerX = boundsInLocal.getWidth() / 2;
        double centerY = boundsInLocal.getHeight() / 2;

        // Determine the direction of zooming based on the mouse position
        double newScale = imageView.getScaleX();
        if (event.getDeltaY() > 0) {
            newScale *= scaleFactor;
            // Zoom in towards the cursor
            imageView.setTranslateX(imageView.getTranslateX() - (pivotX - centerX) * (scaleFactor - 1));
            imageView.setTranslateY(imageView.getTranslateY() - (pivotY - centerY) * (scaleFactor - 1));
        } else {
            newScale /= scaleFactor;
            // Zoom out from the cursor
            imageView.setTranslateX(imageView.getTranslateX() + (pivotX - centerX) * (1 - 1 / scaleFactor));
            imageView.setTranslateY(imageView.getTranslateY() + (pivotY - centerY) * (1 - 1 / scaleFactor));
        }

        // Set the new scale to the imageView
        imageView.setScaleX(newScale);
        imageView.setScaleY(newScale);
        // TODO: Make the ScrollPane zone bigger according to the image
        // TODO: Maybe take ImageView Outside of the scrollPane
        // System.out.println("Zooming: " + newScale + " at (" + pivotX + ", " + pivotY + ")");
    }
 
}


    
