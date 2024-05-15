package controllers;

import click_handlers.FLAClickHandler;
import core.Global;
import core.labeled_shapes.IRemovable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

/**
 * The controller class for managing the frame group in the application.
 */
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

    /**
     * Sets the frame group and its parent stack pane.
     * 
     * @param frameGroup The frame group to set.
     */
    public static void setFrameGroup(Group frameGroup) {
        FrameGroupController.frameGroup = frameGroup;
        FrameGroupController.parent = (StackPane) frameGroup.getParent();
    }

    /**
     * Gets the minimum X coordinate of the frame group in the parent's coordinate system.
     * 
     * @return The minimum X coordinate.
     */
    public static double getRealXmin() {
        return frameGroup.getBoundsInParent().getMinX();
    }

    /**
     * Gets the maximum X coordinate of the frame group in the parent's coordinate system.
     * 
     * @return The maximum X coordinate.
     */
    public static double getRealXmax() {
        return frameGroup.getBoundsInParent().getMaxX();
    }

    /**
     * Gets the minimum Y coordinate of the frame group in the parent's coordinate system.
     * 
     * @return The minimum Y coordinate.
     */
    public static double getRealYmin() {
        return frameGroup.getBoundsInParent().getMinY();
    }

    /**
     * Gets the maximum Y coordinate of the frame group in the parent's coordinate system.
     * 
     * @return The maximum Y coordinate.
     */
    public static double getRealYmax() {
        return frameGroup.getBoundsInParent().getMaxY();
    }

    /**
     * Gets the X coordinate of the center of the frame group in the parent's coordinate system.
     * 
     * @return The X coordinate of the center.
     */
    public static double getRealXc() {
        return frameGroup.getBoundsInParent().getCenterX();
    }

    /**
     * Gets the Y coordinate of the center of the frame group in the parent's coordinate system.
     * 
     * @return The Y coordinate of the center.
     */
    public static double getRealYc() {
        return frameGroup.getBoundsInParent().getCenterY();
    }

    /**
     * Gets the width of the frame group in the parent's coordinate system.
     * 
     * @return The width of the frame group.
     */
    public static double getRealWidth() {
        return frameGroup.getBoundsInParent().getWidth();
    }

    /**
     * Gets the height of the frame group in the parent's coordinate system.
     * 
     * @return The height of the frame group.
     */
    public static double getRealHeight() {
        return frameGroup.getBoundsInParent().getHeight();
    }

    /**
     * Gets the minimum side size of the frame group (minimum of width and height).
     * 
     * @return The minimum side size.
     */
    public static double getMinSideSize(){
        return Math.min(getRealWidth(), getRealHeight());
    }

    // TODO: Better Zooming
    // In order to optimize the zooming, we can bind the scaleProperty of the
    // frameGroup to the worldScale and set a listener for the wordlScale change
    /**
     * Zooms the frame group to a specific scale around a pivot point.
     * 
     * @param scale The scale factor to apply.
     * @param pivot The pivot point for zooming.
     */
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

    /**
     * Shifts the frame group by a given delta.
     * 
     * @param delta The delta to shift the frame group by.
     */
    public static void shift(Point2D delta){
        frameGroup.setTranslateX(frameGroup.getTranslateX() + delta.getX());
        frameGroup.setTranslateY(frameGroup.getTranslateY() + delta.getY());
    }

    /**
     * Shifts the frame group by a given delta in the X and Y directions.
     * 
     * @param dx The delta to shift the frame group in the X direction.
     * @param dy The delta to shift the frame group in the Y direction.
     */
    public static void shift(double dx, double dy){
        frameGroup.setTranslateX(frameGroup.getTranslateX() + dx);
        frameGroup.setTranslateY(frameGroup.getTranslateY() + dy);
    }

    /**
     * Resets the view of the frame group to its initial state.
     */
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

    /**
     * Removes an item from the frame group.
     * 
     * @param item The item to remove.
     */
    public static void remove(IRemovable item){
        item.remove(frameGroup);
    }
}
