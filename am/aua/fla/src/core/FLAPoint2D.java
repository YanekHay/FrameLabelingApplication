package core;

import controllers.MainController;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
public class FLAPoint2D extends FLAAnnotation2D implements IDraggable, IDrawable{
    private Circle pointImage;
    private double x;
    private double y;
    private Color fillColor;
    // TODO: add radius property: Use setRadius() when zooming in or out to change the radius of the point
    // TODO: add outlineColor property

    /**
     * Constructs a FLAPoint2D object with the specified coordinates, fillColor, radius, and container.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param fillColor The fillColor of the point.
     * @param radius The radius of the point.
     * @param container The container where the point will be drawn.
     */
    public FLAPoint2D(double x, double y, Color fillColor, double radius, Pane container) {
        super();
        this.pointImage = new Circle(x, y, radius); //TODO: make the scale of the pointImage dependent on the scale of the frameGroup
        this.pointImage.setId(this.getId());
        this.setX(x);
        this.setY(y);
        this.pointImage.setFill(fillColor);
        this.pointImage.setCursor(Cursor.MOVE);
        this.pointImage.setStrokeWidth(radius/5);
        this.pointImage.setStroke(Color.rgb(200, 30, 60, 1));
        this.pointImage.setOnMouseDragged(this::onMouseDragged);
        this.pointImage.setOnMouseEntered(this::onMouseEntered);
        this.pointImage.setOnMouseExited(this::onMouseExited);
        this.pointImage.setOnMousePressed(this::onMousePressed);
        if (container!=null)
            this.drawOnNode(container);
    }

    /**
     * Constructs a FLAPoint2D object with the specified coordinates, fillColor, and radius.
     * The point will not be drawn on any container.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param fillColor The fillColor of the point.
     * @param radius The radius of the point.
     */
    public FLAPoint2D(double x, double y, Color fillColor, double radius) {
        this(x, y, fillColor, radius, null);
    }

    /**
     * Constructs a FLAPoint2D object with the specified Point2D, fillColor, radius, and container.
     * @param point The Point2D representing the coordinates of the point.
     * @param fillColor The fillColor of the point.
     * @param radius The radius of the point.
     * @param container The container where the point will be drawn.
     */
    public FLAPoint2D(Point2D point, Color fillColor, double radius, Pane container) {
        this(point.getX(), point.getY(), fillColor, radius);
        this.drawOnNode(container);
    }

    /**
     * Constructs a FLAPoint2D object with the specified Point2D, fillColor, and radius.
     * The point will not be drawn on any container.
     * @param point The Point2D representing the coordinates of the point.
     * @param fillColor The fillColor of the point.
     * @param radius The radius of the point.
     */
    public FLAPoint2D(Point2D point, Color fillColor, double radius) {
        this(point.getX(), point.getY(), fillColor, radius);
    }

    /**
     * Constructs a FLAPoint2D object with the specified coordinates and radius.
     * The point will be black in fillColor.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param radius The radius of the point.
     */
    public FLAPoint2D(double x, double y, double radius){
        this(x, y, Color.BLACK, radius);
    }
    
    /**
     * Constructs a FLAPoint2D object with the specified coordinates and fillColor.
     * The point will have a radius of 5.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param fillColor The fillColor of the point.
     */
    public FLAPoint2D(double x, double y, Color fillColor){
        this(x, y, fillColor, 5 );
    }

    /**
     * Constructs a FLAPoint2D object with the specified coordinates.
     * The point will be black in fillColor and have a radius of 5.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public FLAPoint2D(double x, double y){
        this(x, y, Color.BLACK, 5);
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
     * Constructs a FLAPoint2D object with the default coordinates (0, 0).
     * The point will be black in fillColor and have a radius of 5.
     */
    public FLAPoint2D(){
        this(0, 0);
    }
    
    /**
     * Gets the x-coordinate of the point.
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the point.
     * @param x The new x-coordinate of the point.
     */
    public void setX(double x) {
        this.x = x;
        this.pointImage.setCenterX(x);
    }

    /**
     * Sets the y-coordinate of the point.
     * @param y The new y-coordinate of the point.
     */
    public void setY(double y) {
        this.y = y;
        this.pointImage.setCenterY(y);
    }

    /**
     * Sets the fillColor of the point.
     * @param fillColor The new fillColor of the point.
     */
    public void setColor(Color fillColor){
        this.fillColor = fillColor;
    }

    /**
     * Gets the fillColor of the point.
     * @return The fillColor of the point.
     */
    public Color getColor(){
        return this.fillColor;
    }
    /**
     * Draws the point on the specified container (Pane).
     * @param container The container where the point will be drawn.
     */
    @Override
    public void drawOnNode(Pane container) {
        container.getChildren().add(this.pointImage);
    }

    /**
     * Draws the point on the specified container (Group).
     * @param container The container where the point will be drawn.
     */
    @Override
    public void drawOnNode(Group container) {
        container.getChildren().add(this.pointImage);
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
        this.setX(point.getX());
        this.setY(point.getY());
    }

    /**
     * Event handler for the onMouseDragged event.
     * Updates the point's coordinates based on the mouse position.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMouseDragged(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }

    /**
     * Event handler for the onMousePressed event.
     * Consumes the event.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMousePressed(MouseEvent e) {
        e.consume();
    }

    /**
     * Event handler for the onMouseEntered event.
     * Changes the fill fillColor of the point.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMouseEntered(MouseEvent e) {
        this.pointImage.setFill(Color.rgb(100, 0, 50, 0.3));
    }

    /**
     * Event handler for the onMouseExited event.
     * Changes the fill fillColor of the point.
     * @param e The MouseEvent object.
     */
    @Override
    public void onMouseExited(MouseEvent e) {
        this.pointImage.setFill(Color.rgb(200, 0, 50, 0.5));
    }

    
    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler) {
        this.pointImage.setOnMouseDragged(eventHandler);
    }

    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler) {
        this.pointImage.setOnMouseEntered(eventHandler);
    }

    public void reScale(double scale){
        if (this.pointImage.getRadius()*scale >= Configs.MAX_POINT_RADIUS){
            return;
        }
        this.pointImage.setRadius(this.pointImage.getRadius()*scale);
        this.pointImage.setStrokeWidth(this.pointImage.getStrokeWidth()*scale);
    }
    /**
     * Returns a string representation of the FLAPoint2D object.
     * @return A string representation of the FLAPoint2D object.
     */
    @Override
    public String toString(){
        return String.format("FLAPoint2D(%.2f, %.2f)", this.x, this.y);
    }
}