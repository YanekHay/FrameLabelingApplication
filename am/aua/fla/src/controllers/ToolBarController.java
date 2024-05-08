package controllers;

public class ToolBarController {
    public enum Tool{
        SELECT,
        POINT,
        RECTANGLE,
        POLYGON,
    }

    private static Tool currentTool = Tool.SELECT;

    public static void setCurrentTool(Tool tool){
        currentTool = tool;
        System.out.println("Setting tool to: " + tool.toString());
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
    }

    public static Tool getCurrentTool(){
        return currentTool;
    }
    
}
