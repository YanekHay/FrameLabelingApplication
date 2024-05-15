package core.shapes;

import core.Global;
import core.styled.FLAStyle;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * A class representing a rectangle in 2D space.
 */
public class FLARectangle2D extends FLAShape2D {
    private FLAPoint2D topLeft;
    private FLAPoint2D topRight;
    private FLAPoint2D bottomRight;
    private FLAPoint2D bottomLeft;

    private FLAPoint2D[] points = new FLAPoint2D[4];
    private FLALine2D[] lines = new FLALine2D[4];
    private Rectangle rectangle = new Rectangle();
    
    /**
     * Creates a rectangle with the given coordinates.
     * @param xMin The x-coordinate of the top-left corner.
     * @param yMin The y-coordinate of the top-left corner.
     * @param xMax The x-coordinate of the bottom-right corner.
     * @param yMax The y-coordinate of the bottom-right corner.
     */
    public FLARectangle2D(double xMin, double yMin, double xMax, double yMax) {
        this.topLeft = new FLAPoint2D(xMin, yMin);
        this.topRight = new FLAPoint2D(xMax, yMin);
        this.bottomRight = new FLAPoint2D(xMax, yMax);
        this.bottomLeft = new FLAPoint2D(xMin, yMax);
        this.points[0] = topLeft;
        this.points[1] = topRight;
        this.points[2] = bottomRight;
        this.points[3] = bottomLeft;
        this.bindRectanglePoints();
        this.addLines();
        this.rectangle.setOnMouseDragged(this::onMouseDragged);
        this.rectangle.setOnMousePressed(this::onMousePressed);
        this.rectangle.setCursor(Cursor.CLOSED_HAND);
        this.rectangle.setFill(Color.valueOf("#ff000077"));

    }

    /**
     * Creates a rectangle with the given top-left corner.
     * @param topleft The top-left corner of the rectangle.
     */
    public FLARectangle2D(Point2D topleft) {
        this(topleft.getX(), topleft.getY(), topleft.getX(), topleft.getY());
    }


    /**
     * Creates a rectangle with the given top-left and bottom-right corners.
     * @param topleft The top-left corner of the rectangle.
     * @param bottomRight The bottom-right corner of the rectangle.
     */
    public FLARectangle2D(Point2D topleft, Point2D bottomRight) {
        this(topleft.getX(), topleft.getY(), bottomRight.getX(), bottomRight.getY());
    }

    /**
     * Adds the lines of the rectangle to the lines array.
     */
    private void addLines(){
        for(int i=0; i<this.points.length; i++){
            this.lines[i] = new FLALine2D(this.points[i], this.points[(i+1)%4]);
        }

        this.lines[0].makeHorizontal();
        this.lines[1].makeVertical();
        this.lines[2].makeHorizontal();
        this.lines[3].makeVertical();
    }

    /**
     * Updates the rectangle's position when the top-left or bottom-right corner changes.
     */
    private void onPointXPropChange(){
        this.rectangle.setX(Math.min(this.topLeft.getX(), this.topRight.getX()));
        this.rectangle.setWidth(Math.abs(this.topRight.getX() - this.topLeft.getX()));

    }

    /**
     * Updates the rectangle's position when the top-left or bottom-right corner changes.
     */
    private void onPointYPropChange(){
        this.rectangle.setY(Math.min(this.topLeft.getY(), this.bottomLeft.getY()));
        this.rectangle.setHeight(Math.abs(this.bottomLeft.getY() - this.topLeft.getY()));
    }

    /**
     * Binds the points of the rectangle to the rectangle's properties.
     */
    private void bindRectanglePoints(){
        this.bottomRight.xProperty().bindBidirectional(this.topRight.xProperty());
        this.bottomRight.yProperty().bindBidirectional(this.bottomLeft.yProperty());
        this.topLeft.xProperty().bindBidirectional(this.bottomLeft.xProperty());
        this.topLeft.yProperty().bindBidirectional(this.topRight.yProperty());
        this.onPointXPropChange();
        this.onPointYPropChange();

        this.topLeft.xProperty().addListener((obs, oldVal, newVal) -> onPointXPropChange());
        this.topRight.xProperty().addListener((obs, oldVal, newVal) -> onPointXPropChange());
        this.bottomRight.xProperty().addListener((obs, oldVal, newVal) -> onPointXPropChange());
        this.bottomLeft.xProperty().addListener((obs, oldVal, newVal) -> onPointXPropChange());

        this.topLeft.yProperty().addListener((obs, oldVal, newVal) -> onPointYPropChange());
        this.topRight.yProperty().addListener((obs, oldVal, newVal) -> onPointYPropChange());
        this.bottomRight.yProperty().addListener((obs, oldVal, newVal) -> onPointYPropChange());
        this.bottomLeft.yProperty().addListener((obs, oldVal, newVal) -> onPointYPropChange());
    }

    /**
     * Returns the top-left corner of the rectangle.
     * @return
     */
    public FLAPoint2D getStartPoint() {
        return this.topLeft;
    }

    /**
     * Returns the bottom-right corner of the rectangle.
     * @return
     */
    public FLAPoint2D getEndPoint() {
        return this.bottomRight;
    }

    /**
     * Moves the top-left corner of the rectangle to the given point.
     * @param point The point to move the top-left corner to.
     */
    public void moveBottomRightTo(Point2D point){
        this.bottomRight.drag(point);
    }
        
    /**
     * Get width of the rectangle
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }
    /**
     * Get height of the rectangle
     */
    public double getHeight() {
        return this.rectangle.getHeight();
    }
    /**
     * Get area of the rectangle
     */
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public <T extends Group> void drawOnNode(T container) {
        container.getChildren().add(this.rectangle);
        for(FLALine2D line: this.lines){
            line.drawOnNode(container);
        }
    }

    @Override
    public void drag(double x, double y) {
        this.topLeft.drag(x,y);
        this.bottomRight.drag(x,y);
    }

    @Override
    public void drag(Point2D point) {
        this.drag(point.getX(), point.getY());
    }

    @Override
    public void dragByDelta(double dx, double dy) {
        this.topLeft.dragByDelta(dx, dy);
        this.bottomRight.dragByDelta(dx, dy);
    }
    @Override
    public void dragByDelta(Point2D dP) {
        this.dragByDelta(dP.getX(), dP.getY());
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = Global.pointOnCanvas(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));      
        this.mouseDown.set(mousePos);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        e.consume();
        this.mouseDown.set(Global.pointOnCanvas(e.getSceneX(), e.getSceneY()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FLARectangle2D)){
            return false;
        }
        else {
            FLARectangle2D other = (FLARectangle2D) obj;
            return this.topLeft.equals(other.topLeft) && this.bottomRight.equals(other.bottomRight);
        }
    }

    @Override
    public String toString() {
        return "Rectangle: " + this.topLeft + " " + this.bottomRight;
    }

    @Override
    public <T extends Group> void removeFromNode(T container) {
        for (FLALine2D line : lines) {
            line.removeFromNode(container);
        }
        container.getChildren().remove(this.rectangle);
    }

    @Override
    public void bindComponentStylesTo(FLAStyle style) {
        for (FLALine2D line : lines) {
            line.bindComponentStylesTo(style);
        }
        this.rectangle.fillProperty().bind(style.fillColorProperty());
    } 

    @Override
    public void remove(Group node){
        this.removeFromNode(node);
    }
}