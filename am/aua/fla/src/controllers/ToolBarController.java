package controllers;

import core.labeled_shapes.FLALabel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ToolBarController {
    public enum Tool{
        SELECT,
        POINT,
        RECTANGLE,
        POLYGON,
    }

    private static Tool currentTool = Tool.SELECT;
    private static FLALabel currentLabel;

    public static boolean setCurrentTool(Tool tool){
        currentTool = tool;
        if (currentLabel == null && tool != Tool.SELECT) {
            alertLabelNotSelected();
            return false;
        }
        switch (currentTool) {
            case Tool.SELECT:
                FrameGroupController.setSelectingMode();
                break;
            case Tool.POINT:
                FrameGroupController.setPointDrawingMode();
                break;
            case Tool.RECTANGLE:
                FrameGroupController.setRectangleDrawingMode();
                break;
            case Tool.POLYGON:
                FrameGroupController.setPolygonDrawingMode();
                break;
            default:
                break;
        }
        return true;
    }

    public static Tool getCurrentTool(){
        return currentTool;
    }

    public static FLALabel getCurrentLabel(){
        return currentLabel;
    }

    public static void setCurrentLabel(FLALabel label){
        currentLabel = label;
        System.out.println("Current label set to: " + label);
    }
    
    public static void alertLabelNotSelected() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Select a label first!");
        alert.setHeaderText("You have not selected a label yet!");
        alert.setContentText("Please select a label first before drawing.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
