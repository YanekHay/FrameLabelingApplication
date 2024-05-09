package controllers;

import static utils.CalculationUtil.clamp;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import core.ImageLoader;
import core.VideoLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.media.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane frameFront;
    @FXML
    private StackPane frameBack;
    @FXML
    private Button btnResetView;
    @FXML
    private Button btnNext;
    
    private VideoLoader videoLoader = new VideoLoader();

    private static final int MIN_PIXELS = 10;
    private ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

    @FXML
    void initialize() {
        imageView.fitHeightProperty().bind(frameBack.prefHeightProperty());
        imageView.fitWidthProperty().bind(frameBack.prefWidthProperty());
        imageView.setViewport(new Rectangle2D(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight()));
        btnNext.setOnAction(this::btnNextOnAction);
        }

    @FXML
    void btnNextOnAction(ActionEvent event) {
        System.out.println("Next Button Clicked");

    }

    @FXML
    void frameFront_onScroll(ScrollEvent event) {
        if (!event.isControlDown()) {
            return;
        }
        double width = imageView.getImage().getWidth();
        double height = imageView.getImage().getHeight();

        double delta = -event.getDeltaY();
        Rectangle2D viewport = imageView.getViewport();

        double scale = clamp(Math.pow(1.01, delta),
            Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
            Math.max(width / viewport.getWidth(), height / viewport.getHeight())
        );

        Point2D mouse = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));

        double newWidth = viewport.getWidth() * scale;
        double newHeight = viewport.getHeight() * scale;

        // To keep the visual point under the mouse from moving, we need
        // (x - newViewportMinX) / (x - currentViewportMinX) = scale
        // where x is the mouse X coordinate in the image

        // solving this for newViewportMinX gives

        // newViewportMinX = x - (x - currentViewportMinX) * scale 

        // we then clamp this value so the image never scrolls out
        // of the imageview:
        double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale, 
                0, width - newWidth);
        double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale, 
                0, height - newHeight);

        imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
    }

    @FXML
    void frameFront_onMousePressed(MouseEvent event){
        Point2D mousePress = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));
        mouseDown.set(mousePress);
    }

    @FXML
    void frameFront_onMouseDragged(MouseEvent event){
        if (!event.isMiddleButtonDown())
            return;
            Point2D dragPoint = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));
            shift(imageView, dragPoint.subtract(mouseDown.get()));
            mouseDown.set(imageViewToImage(imageView, new Point2D(event.getX(), event.getY())));
    }
    
    @FXML
    void btnResetView_onMouseClicked(MouseEvent event){
        System.out.println("Reset View");
        reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
    }
    // reset to the top left:
    private void reset(ImageView imageView, double width, double height) {
        imageView.setViewport(new Rectangle2D(0, 0, width, height));
    }

    @FXML
    void frameFront_onKeyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key==KeyCode.NUMPAD0 && event.isControlDown()) {
            reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
        }
    }

    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    private void shift(ImageView imageView, Point2D delta) {
        Rectangle2D viewport = imageView.getViewport();

        double width = imageView.getImage().getWidth() ;
        double height = imageView.getImage().getHeight() ;

        double maxX = width - viewport.getWidth();
        double maxY = height - viewport.getHeight();
        
        double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

        imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
    }

    // convert mouse coordinates in the imageView to coordinates in the actual image:
    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imageView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(), 
                viewport.getMinY() + yProportion * viewport.getHeight());
    }
    
    @FXML
    public void openImageFileDialog(){
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.chooseImageFile();
        File file = imageLoader.getPath();
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }

        @FXML
        public void openVideoDialog(){
            videoLoader.chooseVideoFile();
            File file = videoLoader.getPath();
            if (file != null) {
                videoLoader.loadVideo(file);
                Image image = videoLoader.getFrame(0);
                imageView.setImage(image);
            }
        }
    }


    
