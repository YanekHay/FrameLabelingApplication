package controllers;

import click_handlers.FLAClickHandler;
import core.Global;
import core.labeled_shapes.ILabeled;
import core.labeled_shapes.IRemovable;
import core.shapes.FLAPoint2D;
import core.styled.IStyled;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import utils.Configs;

public class FrameGroupController {
    public static Group frameGroup;
    public static StackPane parent;
    public static FLAClickHandler<StackPane, Group> clickHandler;
    
    public static ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    private static double oldX;
    private static double oldY;
    private static double oldDx;
    private static double oldDy;
    private static double relMouseX;
    private static double relMouseY;
    private static double newX;
    private static double newY;
    private static double dx;
    private static double dy;

    public static void setFrameGroup(Group frameGroup) {
        FrameGroupController.frameGroup = frameGroup;
        FrameGroupController.parent = (StackPane) frameGroup.getParent();
    }

    public static double getRealXmin() {
        return frameGroup.getBoundsInParent().getMinX();
    }
    public static double getRealXmax() {
        return frameGroup.getBoundsInParent().getMaxX();
    }
    public static double getRealYmin() {
        return frameGroup.getBoundsInParent().getMinY();
    }
    public static double getRealYmax() {
        return frameGroup.getBoundsInParent().getMaxY();
    }
    public static double getRealXc() {
        return frameGroup.getBoundsInParent().getCenterX();
    }
    public static double getRealYc() {
        return frameGroup.getBoundsInParent().getCenterY();
    }

    public static double getRealWidth() {
        return frameGroup.getBoundsInParent().getWidth();
    }

    public static double getRealHeight() {
        return frameGroup.getBoundsInParent().getHeight();
    }

    public static double getMinSideSize(){
        return Math.min(getRealWidth(), getRealHeight());
    }

    // TODO: Better Zooming
    // In order to optimize the zooming, we can bind the scaleProperty of the
    // frameGroup to the worldScale and set a listener for the wordlScale change
    public static void zoomToPoint(double scale, Point2D pivot) {
        oldX = FrameGroupController.getRealXmin();
        oldY = FrameGroupController.getRealYmin();
        oldDx = pivot.getX()-oldX;
        oldDy = pivot.getY()-oldY;
        
        // Get the relative mouse position
        relMouseX = (oldDx)/FrameGroupController.getRealWidth();
        relMouseY = (oldDy)/FrameGroupController.getRealHeight();

        // // Scale the frameGroup
        frameGroup.setScaleX(scale);
        frameGroup.setScaleY(scale);
        // Get the current REAL coordinates of the frameGroup
        newX = FrameGroupController.getRealXmin();
        newY = FrameGroupController.getRealYmin();

        // Translate the resized frameGroup so that the mouse position is the same as before the resize
        //(frameGroup.getTranslateX() + oldX-newX) is for translating the frameGroup to the same position after scaling
        //(relMouseX*FrameGroupController.getRealWidth()-oldDx) is for translating the frameGroup to the position where the mouse was before scaling
        dx = (frameGroup.getTranslateX() + oldX-newX) - (relMouseX*FrameGroupController.getRealWidth()-oldDx);
        dy = (frameGroup.getTranslateY() + oldY-newY) - (relMouseY*FrameGroupController.getRealHeight()-oldDy);
        frameGroup.setTranslateX(dx);
        frameGroup.setTranslateY(dy);
    }

    public static void shift(Point2D delta){
        frameGroup.setTranslateX(frameGroup.getTranslateX() + delta.getX());
        frameGroup.setTranslateY(frameGroup.getTranslateY() + delta.getY());
    }
    public static void shift(double dx, double dy){
        frameGroup.setTranslateX(frameGroup.getTranslateX() + dx);
        frameGroup.setTranslateY(frameGroup.getTranslateY() + dy);
    }

    public static void resetView(){
        frameGroup.setTranslateX(0);
        frameGroup.setTranslateY(0);
        frameGroup.setScaleX(1);
        frameGroup.setScaleY(1);
        Global.setWorldScale(1);
    }

    protected static void setSelectingMode(){
        if (clickHandler != null) {
            clickHandler.deselect();
        }
        clickHandler = Global.selectClickHandler.select();
    }
    protected static void setPointDrawingMode(){
        if (clickHandler != null) {
            clickHandler.deselect();
        }
        clickHandler = Global.pointClickHandler.select();
    }
    protected static void setRectangleDrawingMode(){
        if (clickHandler != null) {
            clickHandler.deselect();
        }
        clickHandler = Global.rectangleClickHandler.select();
    }
    protected static void setPolygonDrawingMode(){
        if (clickHandler != null) {
            clickHandler.deselect();
        }
        clickHandler = Global.polygonClickHandler.select();
    }


    public static void remove(IRemovable item){
        item.remove(frameGroup);
    }
}
