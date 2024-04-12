package controllers;

import static utils.CalculationUtil.clamp;

import java.io.File;

import core.FLALine2D;
import core.FLAPoint2D;
import core.Global;
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
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Configs;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.ArrayList;
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
    
    private double scaleMultiplier = 1.0;

    Label label = new Label("");
    Label label2 = new Label("");
    Label label3 = new Label("");
    // FLAPoint2D point = new FLAPoint2D(0,0, Color.RED, 5);
    @FXML
    void initialize() {
        // runLater is used to ensure that the layout is already rendered
        Platform.runLater(() -> {
            FrameGroupController.setFrameGroup(frameGroup);
            FLALine2D line = new FLALine2D(new FLAPoint2D(50,50), new FLAPoint2D(100,100));
            line.drawOnNode(frameGroup);
        });
        labelArea.getChildren().add(label);
        labelArea.getChildren().add(label2);
        labelArea.getChildren().add(label3);

        frameArea.setOnMouseMoved(e->{
            label.setText("X: " + Math.round(e.getX()) + " Y: " + Math.round(e.getY()));
            label2.setText("X: " + ((int)(e.getX()-FrameGroupController.getRealXmin())) + " Y: " + (int)(e.getY()-FrameGroupController.getRealYmin()));
        });
        frameArea.setOnMousePressed(this::frameAreaOnMousePressed);
        frameArea.setOnMouseDragged(this::frameAreaOnMouseDragged);
    }

    @FXML
    void frameAreaOnScroll(ScrollEvent e) {
        if (!e.isControlDown()) {
            return;
        }
        double delta = e.getDeltaY();
        this.setScale(clamp(Math.pow(Configs.ZOOM_FACTOR, delta), FrameGroupController.MIN_PIXELS / FrameGroupController.getMinSideSize()));
        FrameGroupController.zoomToPoint(this.scaleMultiplier, new Point2D(e.getX(), e.getY()));
        for (FLAPoint2D point : Global.points) {
            System.out.println(point.getId());
            point.reScale(1/this.scaleMultiplier);
        }
        label.setText("X: " + Math.round(e.getX()) + " Y: " + Math.round(e.getY()));
        label2.setText("X: " + ((int)(e.getX()-FrameGroupController.getRealXmin())) + " Y: " + (int)(e.getY()-FrameGroupController.getRealYmin()));

    }

    public void setScale(double scaleMultiplier) {
        this.scaleMultiplier = scaleMultiplier;
    }



    @FXML
    void frameAreaOnMousePressed(MouseEvent e){
        if (e.isMiddleButtonDown()) {
            FrameGroupController.mouseDown.set(new Point2D((e.getX()), (e.getY())));
        }
        else if (e.isPrimaryButtonDown()){
            e.consume();
            Point2D pt = frameGroup.parentToLocal(e.getX(), e.getY());
            FLAPoint2D point = new FLAPoint2D(pt, Color.RED, Configs.POINT_RADIUS);
            Global.points.add(point);
            point.drawOnNode(frameGroup);
            System.out.println("Point placed on: "  + point.getX() + "  " + point.getY());
        }
    }

    @FXML
    void frameAreaOnMouseDragged(MouseEvent e){
        if (e.isMiddleButtonDown()) {
            Point2D dragPoint = new Point2D(e.getX(), e.getY());
            double deltaX = dragPoint.getX() - FrameGroupController.mouseDown.get().getX();
            double deltaY = dragPoint.getY() - FrameGroupController.mouseDown.get().getY();
            FrameGroupController.frameGroup.setTranslateX(FrameGroupController.frameGroup.getTranslateX() + deltaX);
            FrameGroupController.frameGroup.setTranslateY(FrameGroupController.frameGroup.getTranslateY() + deltaY);
            FrameGroupController.mouseDown.set(dragPoint);
        }
    }
    
    @FXML
    void btnResetViewOnMouseClicked(MouseEvent e){
        System.out.println("Reset View");
        reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
    }
    // reset to the top left:
    private void reset(ImageView imageView, double width, double height) {
        imageView.setViewport(new Rectangle2D(0, 0, width, height));
    }

    @FXML
    void frameAreaOnKeyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key==KeyCode.NUMPAD0 && e.isControlDown()) {
            reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
        }
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


    
