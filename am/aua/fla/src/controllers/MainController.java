package controllers;

import java.io.File;

import controllers.ToolBarController.Tool;
import core.Global;
import core.shapes.FLAPolygon2D;
import core.shapes.FLARectangle2D;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import core.labeled.*;
public class MainController {

    
    @FXML private BorderPane root;
    @FXML private ImageView imageView;
    @FXML private StackPane frameArea;
    @FXML private Group frameGroup;
    @FXML private Button btnResetView;
    @FXML private ScrollPane labelLayersContainer;
    @FXML private Button btnDrawPoint;
    @FXML private Button btnDrawRectangle;
    @FXML private Button btnDrawPolygon;
    @FXML private Button btnSelectTool;

    FLAPolygon2D poly = new FLAPolygon2D();
    
    Label label = new Label("");
    @FXML
    void initialize() {
        // runLater is used to ensure that the layout is already rendered
        Platform.runLater(() -> {
            FrameGroupController.setFrameGroup(frameGroup);
            FrameGroupController.setSelectingMode();
            FLALabeledPoint pt = new FLALabeledPoint(50,50, 0);
            FLARectangle2D rect = new FLARectangle2D(100, 100, 200, 200);
            poly.drawOnNode(frameGroup);
            pt.drawOnNode(frameGroup);
            rect.drawOnNode(frameGroup);
            frameArea.requestFocus();
        });
        // labelArea.getChildren().add(label);

        frameArea.setOnMouseMoved(e->{
            Point2D pt = frameGroup.parentToLocal(e.getX(), e.getY());
            label.setText("X: " + Math.round(pt.getX()) + " Y: " + Math.round(pt.getY()));
        });
        frameArea.setOnMousePressed(this::frameAreaOnMousePressed);
        frameArea.setOnMouseDragged(this::frameAreaOnMouseDragged);
        btnDrawPoint.setOnMouseClicked(e->{ToolBarController.setCurrentTool(Tool.POINT);});
        btnDrawRectangle.setOnMouseClicked(e->{ToolBarController.setCurrentTool(Tool.RECTANGLE);});
        btnDrawPolygon.setOnMouseClicked(e->{ToolBarController.setCurrentTool(Tool.POLYGON);});
        btnSelectTool.setOnMouseClicked(e->{ToolBarController.setCurrentTool(Tool.SELECT);});
        
    }

    @FXML
    void frameAreaOnScroll(ScrollEvent e) {
        if (!e.isControlDown()) {
            return;
        }
        double delta = e.getDeltaY()>0 ? 1 : -1;
        
        Global.setScaleMultiplier(delta);
        FrameGroupController.zoomToPoint(Global.getWorldScale(), new Point2D(e.getX(), e.getY()));
        Point2D pt = frameGroup.parentToLocal(e.getX(), e.getY());
        label.setText("X: " + Math.round(pt.getX()) + " Y: " + Math.round(pt.getY()));

    }

    @FXML
    void frameAreaOnMousePressed(MouseEvent e){
        e.consume();
        FrameGroupController.mouseDown.set(new Point2D((e.getX()), (e.getY())));
    }

    @FXML
    void frameAreaOnMouseDragged(MouseEvent e){
        if (e.isMiddleButtonDown()) {
            Point2D dragPoint = new Point2D(e.getX(), e.getY());
            FrameGroupController.shift(dragPoint.subtract(FrameGroupController.mouseDown.get()));
            FrameGroupController.mouseDown.set(dragPoint);
        }
        else if (e.isPrimaryButtonDown()){
            // this.selectArea.setWidth(e.getX()-FrameGroupController.mouseDown.get().getX());
            // this.selectArea.setHeight(e.getY()-FrameGroupController.mouseDown.get().getY());
        }
    }
    
    @FXML
    void btnResetViewOnMouseClicked(MouseEvent e){
        FrameGroupController.resetView();
        // reset(imageView, imageView.getImage().getWidth(), imageView.getImage().getHeight());
    }

    @FXML
    void frameAreaOnKeyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key==KeyCode.NUMPAD0 && e.isControlDown()) {
            FrameGroupController.resetView();
        }
    }

    @FXML
    public void openImageFileDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
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
            

        } else {
            System.out.println("No file selected.");
        }
    }
}


    
