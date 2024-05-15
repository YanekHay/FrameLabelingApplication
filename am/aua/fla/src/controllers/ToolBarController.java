package controllers;

import core.Global;
import core.labeled_shapes.FLALabel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * The ToolBarController class is responsible for managing the tools and labels in the application's toolbar.
 */
public class ToolBarController {
    /**
     * An enumeration representing the available tools in the toolbar.
     */
    public enum Tool{
        SELECT,
        POINT,
        RECTANGLE,
        POLYGON,
    }

    private static Tool currentTool = Tool.SELECT;
    private static FLALabel currentLabel;

    /**
     * Sets the current tool in the toolbar.
     * @param tool The tool to set as the current tool.
     * @return true if the tool was set successfully, false otherwise.
     */
    public static boolean setCurrentTool(Tool tool){
        currentTool = tool;
        if (currentLabel == null && tool != Tool.SELECT) {
            alertLabelNotSelected();
            Global.classMenu.show();
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

    /**
     * Gets the current tool in the toolbar.
     * @return The current tool.
     */
    public static Tool getCurrentTool(){
        return currentTool;
    }

    /**
     * Gets the current label in the toolbar.
     * @return The current label.
     */
    public static FLALabel getCurrentLabel(){
        return currentLabel;
    }

    /**
     * Sets the current label in the toolbar.
     * @param label The label to set as the current label.
     */
    public static void setCurrentLabel(FLALabel label){
        currentLabel = label;
    }
    
    /**
     * Displays an alert informing the user that a label has not been selected yet.
     */
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
