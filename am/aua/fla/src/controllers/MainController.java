package controllers;


import java.io.File;

import core.ImageLoader;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.FFmpegFrameGrabber.Exception;

import core.VideoLoader;
import controllers.ToolBarController.Tool;
import core.Global;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.ScrollEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;

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
    @FXML private VBox layerContainer;
    @FXML private ChoiceBox<String> chooseLayerClass;
    private ToggleButton[] tools = new ToggleButton[4];
    
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    
    private VideoLoader videoLoader = new VideoLoader();

    @FXML
    void initialize() {
        // runLater is used to ensure that the layout is already rendered
        Platform.runLater(() -> {
            FrameGroupController.setFrameGroup(frameGroup);
            frameArea.requestFocus();
            tools[0] = btnSelectTool;
            tools[1] = btnDrawPoint;
            tools[2] = btnDrawRectangle;
            tools[3] = btnDrawPolygon;
            btnSelectTool.fire();
            chooseLayerClass.setItems(Global.layerClasses);
            chooseLayerClass.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println("Selected: " + newVal.intValue() + " " + oldVal.intValue());
                if (newVal.intValue() == -1) return;
                ToolBarController.setCurrentLabel(Global.labelList.get(newVal.intValue()));
            });
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
    


    @FXML
    private void openClassEditMenu(){
        if (!Global.classMenu.isShowing()){
            Global.classMenu.show();
        }
        else{
            Global.classMenu.hide();
            Global.classMenu.show();
        }
    }
    private void btnSelectToolOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            if (ToolBarController.setCurrentTool(Tool.SELECT)) deselectOtherTools(source);
            else source.setSelected(false);
            deselectOtherTools(source);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawPointOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            if (ToolBarController.setCurrentTool(Tool.POINT)) deselectOtherTools(source);
            else source.setSelected(false);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawRectangleOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            if (ToolBarController.setCurrentTool(Tool.RECTANGLE)) deselectOtherTools(source);
            else source.setSelected(false);
        }
        else{
            source.setSelected(true);
        }
    }
    private void btnDrawPolygonOnAction(ActionEvent e){
        ToggleButton source = (ToggleButton) e.getSource();
        if (source.isSelected()){
            if (ToolBarController.setCurrentTool(Tool.POLYGON)) deselectOtherTools(source);
            else source.setSelected(false);
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
    public void openImageFileDialog(){
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.chooseImageFile();
        File file = imageLoader.getPath();
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }

    private int frameNumber = 1;

    public void openVideoDialog() throws FrameGrabber.Exception {
        videoLoader = new VideoLoader(); // Assign to the member variable
        videoLoader.chooseVideoFile();
        File file = videoLoader.getPath();
        if (file != null) {
            try {
                videoLoader.loadVideo(file);
                Image image = videoLoader.getFrame(frameNumber);
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void btnNextOnAction(ActionEvent event) throws FrameGrabber.Exception {
        try {
            frameNumber++;
            Image nextFrameImage = videoLoader.nextFrame();
            imageView.setImage(nextFrameImage);
        } catch (FrameGrabber.Exception e) {
            System.out.println("Error retrieving next frame: " + e.getMessage());
        }
    }

    @FXML
    public void btnPreviousOnAction(ActionEvent event) throws FrameGrabber.Exception {
        try {
            frameNumber--;
            Image previousFrameImage = videoLoader.getFrame(frameNumber);
            imageView.setImage(previousFrameImage);
        } catch (FrameGrabber.Exception e) {
            System.out.println("Error retrieving previous frame: " + e.getMessage());
        }
    }

}


    
