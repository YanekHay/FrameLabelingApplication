package controllers;

import java.io.File;

import controllers.ToolBarController.Tool;
import core.Global;
import core.labeled_shapes.*;
import core.shapes.FLAPolygon2D;
import core.shapes.FLARectangle2D;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
public class MainController {

    
    @FXML private BorderPane root;
    @FXML private ImageView imageView;
    @FXML private StackPane frameArea;
    @FXML private Group frameGroup;
    @FXML private Button btnResetView;
    @FXML private ScrollPane labelLayersContainer;
    @FXML private ToggleButton btnDrawPoint;
    @FXML private ToggleButton btnDrawRectangle;
    @FXML private ToggleButton btnDrawPolygon;
    @FXML private ToggleButton btnSelectTool;
    @FXML private ToolBar toolbar;
    @FXML private Label coordLabel;
    
    private ToggleButton[] tools = new ToggleButton[4];
    FLAPolygon2D poly = new FLAPolygon2D();
    
    @FXML
    void initialize() {
        // runLater is used to ensure that the layout is already rendered
        Platform.runLater(() -> {
            FrameGroupController.setFrameGroup(frameGroup);
            // FLALabeledPoint pt = new FLALabeledPoint(50,50, 0);
            // FLARectangle2D rect = new FLARectangle2D(100, 100, 200, 200);
            // poly.drawOnNode(frameGroup);
            // pt.drawOnNode(frameGroup);
            // rect.drawOnNode(frameGroup);
            frameArea.requestFocus();
            tools[0] = btnSelectTool;
            tools[1] = btnDrawPoint;
            tools[2] = btnDrawRectangle;
            tools[3] = btnDrawPolygon;
            btnSelectTool.fire();

        });

        frameArea.setOnMouseMoved(e->{
            Point2D pt = frameGroup.parentToLocal(e.getX(), e.getY());
            coordLabel.setText("X: " + Math.round(pt.getX()) + " Y: " + Math.round(pt.getY()));
        });
        frameArea.addEventHandler(MouseEvent.MOUSE_PRESSED, this::frameAreaOnMousePressed);
        frameArea.addEventHandler(ScrollEvent.SCROLL, this::frameAreaOnScroll);
        frameArea.setOnMouseDragged(this::frameAreaOnMouseDragged);
        btnSelectTool.setOnAction(this::btnSelectToolOnAction);
        btnDrawPoint.setOnAction(this::btnDrawPointOnAction);
        btnDrawRectangle.setOnAction(this::btnDrawRectangleOnAction);
        btnDrawPolygon.setOnAction(this::btnDrawPolygonOnAction);
    }
    
    private void btnSelectToolOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            ToolBarController.setCurrentTool(Tool.SELECT);
            deselectOtherTools(source);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawPointOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            ToolBarController.setCurrentTool(Tool.POINT);
            deselectOtherTools(source);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawRectangleOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            ToolBarController.setCurrentTool(Tool.RECTANGLE);
            deselectOtherTools(source);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawPolygonOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            ToolBarController.setCurrentTool(Tool.POLYGON);
            deselectOtherTools(source);
        }
        else{
            source.setSelected(true);
        }
    }

    private void deselectOtherTools(ToggleButton btn){
        for (ToggleButton tool : tools) {
            if (tool != btn) {
                tool.setSelected(false);
            }
        }
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
        coordLabel.setText("X: " + Math.round(pt.getX()) + " Y: " + Math.round(pt.getY()));

    }

    @FXML
    void frameAreaOnMousePressed(MouseEvent e){
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


    
