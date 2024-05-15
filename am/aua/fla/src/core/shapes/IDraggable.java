package core.shapes;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * The IDraggable interface represents an object that can be dragged.
 */
public interface IDraggable {
    /**
     * Drags the object to the specified coordinates.
     * 
     * @param x the x-coordinate to drag to
     * @param y the y-coordinate to drag to
     */
    public void drag(double x, double y);

    /**
     * Drags the object to the specified point.
     * 
     * @param point the point to drag to
     */
    public void drag(Point2D point);

    /**
     * Drags the object by the specified delta values.
     * 
     * @param dx the change in x-coordinate
     * @param dy the change in y-coordinate
     */
    public void dragByDelta(double dx, double dy);

    /**
     * Drags the object by the specified delta point.
     * 
     * @param dP the change in point
     */
    public void dragByDelta(Point2D dP);

    /**
     * Handles the mouse dragged event.
     * 
     * @param e the mouse event
     */
    public void onMouseDragged(MouseEvent e);

    /**
     * Handles the mouse pressed event.
     * 
     * @param e the mouse event
     */
    public void onMousePressed(MouseEvent e);

    /**
     * Sets the mouse down point.
     * 
     * @param mouseDown the mouse down point
     */
    public void setMouseDown(Point2D mouseDown);
}
