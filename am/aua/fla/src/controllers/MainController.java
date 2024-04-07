package controllers;

import static utils.CalculationUtil.clamp;

import java.io.File;

import core.FLAPoint2D;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Configs;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainController {

    @FXML
    private BorderPane root;
    @FXML
    private ImageView imageView;
    @FXML
    private StackPane frameArea;
    @FXML
    private Group frameGroup;
    @FXML
    private Button btnResetView;
    @FXML
    private VBox labelArea;

    private ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    
    Label label = new Label("");
    Label label2 = new Label("");
    Label label3 = new Label("");
    
    @FXML
    void initialize() {
        // runLater is used to ensure that the layout is already rendered
        Platform.runLater(() -> {
            FrameGroupController.frameGroup = frameGroup;
            System.out.println("Frame Area: " + frameGroup.getLayoutX() + "x" + frameArea.getHeight());
        });
        System.out.println(frameGroup.localToScreen(0,0));
        labelArea.getChildren().add(label);
        labelArea.getChildren().add(label2);
        labelArea.getChildren().add(label3);

        frameArea.setOnMouseMoved(e->{
            label.setText("X: " + Math.round(e.getX()) + " Y: " + Math.round(e.getY()));
            label2.setText("X: " + ((int)(e.getX()-FrameGroupController.getRealXmin())) + " Y: " + (int)(e.getY()-FrameGroupController.getRealYmin()));
        });
        //center the frameGroup inside frameArea
        frameArea.setOnMousePressed(e -> {
            if (e.isMiddleButtonDown()) {
                FrameGroupController.mouseDown.set(new Point2D((e.getX()), (e.getY())));
            }
        });
        frameArea.setOnMouseDragged(e -> {
            if (e.isMiddleButtonDown()) {
                Point2D dragPoint = new Point2D(e.getX(), e.getY());
                double deltaX = dragPoint.getX() - FrameGroupController.mouseDown.get().getX();
                double deltaY = dragPoint.getY() - FrameGroupController.mouseDown.get().getY();
                FrameGroupController.frameGroup.setTranslateX(FrameGroupController.frameGroup.getTranslateX() + deltaX);
                FrameGroupController.frameGroup.setTranslateY(FrameGroupController.frameGroup.getTranslateY() + deltaY);
                FrameGroupController.mouseDown.set(dragPoint);
            }
        });
        //     else if (e.isPrimaryButtonDown()){
        //         FLAPoint2D point = new FLAPoint2D((e.getX()-frameGroup.getTranslateX()-FrameGroupController.getRealXmin()), (e.getY()-FrameGroupController.getRealYmin()), Color.RED, Configs.POINT_RADIUS);
        //         System.out.println("Mouse clicked at: " + point);
        //         point.drawOnNode(frameGroup);
        //     }
        // });
    }

    @FXML
    void frameAreaOnScroll(ScrollEvent e) {
        if (!e.isControlDown()) {
            return;
        }
        double delta = e.getDeltaY();
        double scale = clamp(Math.pow(Configs.ZOOM_FACTOR, delta), FrameGroupController.MIN_PIXELS / FrameGroupController.getMinSideSize());
        FrameGroupController.zoomToPoint(scale, new Point2D(e.getX(), e.getY()));
    }


    @FXML
    void frameAreaOnMousePressed(MouseEvent event){
        Point2D mousePress = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));
        mouseDown.set(mousePress);
    }

    @FXML
    void frameAreaOnMouseDragged(MouseEvent event){
        if (!event.isMiddleButtonDown())
            return;
        Point2D dragPoint = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));
        shift(imageView, dragPoint.subtract(mouseDown.get()));
        mouseDown.set(imageViewToImage(imageView, new Point2D(event.getX(), event.getY())));
    }
    
    @FXML
    void btnResetViewOnMouseClicked(MouseEvent event){
        System.out.println("Reset View");
        reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
    }
    // reset to the top left:
    private void reset(ImageView imageView, double width, double height) {
        imageView.setViewport(new Rectangle2D(0, 0, width, height));
    }

    @FXML
    void frameAreaOnKeyPressed(KeyEvent event) {
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
        
        double minX = clamp(viewport.getMinX() - delta.getX(), 0.0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0.0, maxY);

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Add filters (optional)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage stage = (Stage) root.getScene().getWindow(); // Get the window from the imageView
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());

            // Load the image into an Image object
            Image image = new Image(selectedFile.toURI().toString());
            
            // Set the image to the ImageView
            imageView.setImage(image);
            this.reset(imageView, image.getWidth(), image.getHeight());
        } else {
            System.out.println("No file selected.");
        }
    }
}


    
