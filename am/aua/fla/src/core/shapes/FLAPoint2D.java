package core.shapes;

import core.Global;
import core.styled.FLAStyle;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import utils.Configs;

/**
 * The FLAPoint2D class represents a 2D point annotation in a frame labeling application.
 * It extends the FLAAnnotation2D class and implements the IDraggable and IDrawable interfaces.
 * 
 * The FLAPoint2D class provides methods to create and manipulate 2D points, including setting the coordinates,
 * drawing the point on a container, dragging the point, and handling mouse events.
 * 
 * Example usage:
 * FLAPoint2D point = new FLAPoint2D(10, 20, Color.RED, 5, container);
 * point.drawOnNode(container);
 * point.drag(30, 40);
 * 
 * @see FLAAnnotation2D
 * @see IDraggable
 * @see IDrawable
 */
/**
 * Represents a 2D point in the Frame Labeling Application.
 * Extends the FLAAnnotation2D class and implements the IDraggable and IDrawable interfaces.
 */
public class FLAPoint2D extends FLAShape2D {
    protected Circle pointImage;
    protected boolean consumeMousePressed=true;
    /**
     * Constructs a FLAPoint2D object with the specified coordinates, fillColor, radius, and container.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param radius The radius of the point.
     * @param container The container where the point will be drawn.
     */
    public FLAPoint2D(double x, double y, double radius, Group container) {
        super();
        this.pointImage = new Circle(x, y, radius);
        this.setX(x);
        this.setY(y);
        this.pointImage.setCursor(Cursor.MOVE);
        this.pointImage.setOnMouseDragged(this::onMouseDragged);
        this.pointImage.setOnMousePressed(this::onMousePressed);
        this.pointImage.scaleXProperty().bind(Global.worldScaleInverse.multiply(1.5));
        this.pointImage.scaleYProperty().bind(Global.worldScaleInverse.multiply(1.5));

        if (container!=null)
            this.drawOnNode(container);
    }

    /**
     * Constructs a FLAPoint2D object with the specified coordinates, fillColor, and radius.
     * The point will not be drawn on any container.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param radius The radius of the point.
     */
    public FLAPoint2D(double x, double y, double radius) {
        this(x, y,  radius, null);
    }

    /**
     * Constructs a FLAPoint2D object with the specified Point2D, fillColor, and radius.
     * The point will not be drawn on any container.
     * @param point The Point2D representing the coordinates of the point.
     * @param fillColor The fillColor of the point.
     * @param radius The radius of the point.
     */
    public FLAPoint2D(Point2D point, double radius) {
        this(point.getX(), point.getY(), radius);
    }

    /**
     * Constructs a FLAPoint2D object with the specified coordinates.
     * The point will be black in fillColor and have a radius of 5.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public FLAPoint2D(double x, double y){
        this(x, y, Configs.POINT_RADIUS);
    }

    /**
     * Constructs a FLAPoint2D object with the specified Point2D.
     * The point will be black in fillColor and have a radius of 5.
     * @param point The Point2D representing the coordinates of the point.
     */
    public FLAPoint2D(Point2D point){
        this(point.getX(), point.getY());
    }

    /**
     * Gets the x-coordinate of the point.
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return this.pointImage.getCenterX();
    }

    /**
     * Gets the y-coordinate of the point.
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return this.pointImage.getCenterY();
    }

    /**
     * Sets the x-coordinate of the point.
     * @param x The new x-coordinate of the point.
     */
    public void setX(double x) {
        this.pointImage.setCenterX(x);
    }

    /**
     * Sets the y-coordinate of the point.
     * @param y The new y-coordinate of the point.
     */
    public void setY(double y) {
        this.pointImage.setCenterY(y);
    }


    /**
     * Draws the point on the specified container (Pane).
     * @param mouseArea The container where the point will be drawn.
     */
    @Override
    public <T extends Group> void drawOnNode(T mouseArea) {
        if (!mouseArea.getChildren().contains(this.pointImage))
            mouseArea.getChildren().add(this.pointImage);
        this.pointImage.toFront();
    }

    /**
     * Drags the point to the specified coordinates.
     * @param x The new x-coordinate of the point.
     * @param y The new y-coordinate of the point.
     */
    @Override
    public void drag(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    
    /**
     * Drags the point to the specified Point2D coordinates.
     * @param point The new coordinates of the point.
     */
    @Override
    public void drag(Point2D point) {
        this.drag(point.getX(), point.getY());
    }
    
    /**
     * Drags the point to the specified coordinates.
     * @param x The new x-coordinate of the point.
     * @param y The new y-coordinate of the point.
     */
    @Override
    public void dragByDelta(double dx, double dy) {
        this.drag(this.getX()-dx, this.getY()-dy);
    }
    
    /**
     * Drags the point to the specified Point2D coordinates.
     * @param point The new coordinates of the point.
     */
    @Override
    public void dragByDelta(Point2D dPoint) {
        this.dragByDelta(dPoint.getX(), dPoint.getY());
    }

    /**
     * Event handler for the onMouseDragged event.
     * Updates the point's coordinates based on the mouse position.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = Global.pointOnCanvas(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));      
        this.mouseDown.set(mousePos);
    }

    /**
     * Event handler for the onMousePressed event.
     * Consumes the event.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMousePressed(MouseEvent e) {
        if (this.consumeMousePressed || !e.isPrimaryButtonDown()){
            e.consume();
        }
        this.mouseDown.set(Global.pointOnCanvas(e.getSceneX(), e.getSceneY()));
    }

    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler) {
        this.pointImage.setOnMouseDragged(eventHandler);
    }

    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler) {
        this.pointImage.setOnMouseEntered(eventHandler);
    }

    /**
     * Returns a string representation of the FLAPoint2D object.
     * @return A string representation of the FLAPoint2D object.
     */
    @Override
    public String toString(){
        return String.format("FLAPoint2D(%.2f, %.2f)", this.getX(), this.getY());
    }

    public DoubleProperty xProperty() {
        return this.pointImage.centerXProperty();
    }
    
    public DoubleProperty yProperty() {
        return this.pointImage.centerYProperty();
    }

    public Point2D getLocation() {
        return new Point2D(this.getX(), this.getY());
    }
    /**
     * Creates a new FLAPoint2D object with the same properties as the original object.
     * @return A new FLAPoint2D object.
     */
    public FLAPoint2D copy() {
        FLAPoint2D copy = new FLAPoint2D(this.getX(), this.getY());
        copy.pointImage = new Circle(this.getX(), this.getY(), this.pointImage.getRadius());
        return copy;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FLAPoint2D)){
            return false;
        }
        else {
            FLAPoint2D other = (FLAPoint2D) obj;
            return this.getX() == other.getX() && this.getY() == other.getY();
        }
    }

    public void setConsumeMousePressed(boolean value){
        this.consumeMousePressed = value;
    }

    @Override
    public <T extends Group> void removeFromNode(T container) {
        container.getChildren().remove(this.pointImage);
    }

    @Override
    public void bindComponentStylesTo(FLAStyle style) {
        this.pointImage.fillProperty().bind(style.fillColorProperty());
        this.pointImage.strokeProperty().bind(style.strokeColorProperty());
    }

    @Override
    public FLAPoint2D clone() {
        return new FLAPoint2D(this.getX(), this.getY(), this.pointImage.getRadius());
    }

    @Override
    public void remove(Group node){
        node.getChildren().remove(this.pointImage);
    }
}