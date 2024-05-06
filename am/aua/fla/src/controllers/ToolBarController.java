package controllers;

public class ToolBarController {
    public enum Tool{
        SELECT,
        POINT,
        LINE,
        RECTANGLE,
        POLYGON,
    }

    private static Tool currentTool = Tool.SELECT;

    public static void setCurrentTool(Tool tool){
        currentTool = tool;
    }
    
}
