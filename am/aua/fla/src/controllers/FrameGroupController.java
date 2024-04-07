package controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;

public class FrameGroupController {
    public static Group frameGroup;
    public static final int MIN_PIXELS = 64;
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

    public static double getRealXmin() {
        return frameGroup.getBoundsInParent().getMinX();
    }
    public static double getRealYmin() {
        return frameGroup.getBoundsInParent().getMinY();
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


    public static void zoomToPoint(double scaleMultiplier, Point2D pivot) {
        oldX = FrameGroupController.getRealXmin();
        oldY = FrameGroupController.getRealYmin();
        oldDx = pivot.getX()-oldX;
        oldDy = pivot.getY()-oldY;
        
        // Get the relative mouse position
        relMouseX = (oldDx)/FrameGroupController.getRealWidth();
        relMouseY = (oldDy)/FrameGroupController.getRealHeight();

        // Scale the frameGroup
        frameGroup.setScaleX(frameGroup.getScaleX() * scaleMultiplier);
        frameGroup.setScaleY(frameGroup.getScaleY() * scaleMultiplier);

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
}
