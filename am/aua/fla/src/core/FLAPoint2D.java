package core;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FLAPoint2D extends FLAAnnotation2D implements IDraggable, IDrawable{
    private Circle pointImage;
    private double x;
    private double y;

    public FLAPoint2D(double x, double y, Color color, double radius, Pane container) {
        super();
        this.pointImage = new Circle(x, y, radius);
        this.setX(x);
        this.setY(y);
        this.pointImage.setFill(color);
        this.pointImage.setCursor(Cursor.MOVE);
        this.pointImage.setOnMouseDragged(this::onMouseDragged);
        this.pointImage.setOnMouseEntered(this::onMouseEntered);
        if (container!=null)
            this.drawOnNode(container);
    }

    public FLAPoint2D(double x, double y, Color color, double radius) {
        this(x, y, color, radius, null);
    }

    public FLAPoint2D(Point2D point, Color color, double radius, Pane container) {
        this(point.getX(), point.getY(), color, radius);
        this.drawOnNode(container);
    }

    public FLAPoint2D(Point2D point, Color color, double radius) {
        this(point.getX(), point.getY(), color, radius);
    }

    public FLAPoint2D(double x, double y, double radius){
        this(x, y, Color.BLACK, radius);
    }
    
    public FLAPoint2D(double x, double y, Color color){
        this(x, y, color, 5 );
    }

    public FLAPoint2D(double x, double y){
        this(x, y, Color.BLACK, 5);
    }

    public FLAPoint2D(Point2D point){
        this(point.getX(), point.getY());
    }

    public FLAPoint2D(){
        this(0, 0);
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        this.pointImage.setCenterX(x);
    }

    public void setY(double y) {
        this.y = y;
        this.pointImage.setCenterY(y);
    }

    @Override
    public void drawOnNode(Pane container) {
        container.getChildren().add(this.pointImage);
    }

    @Override
    public void drag(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
    }

    @Override
    public String toString(){
        return String.format("FLAPoint2D(%.2f, %.2f)", this.x, this.y);
    }
}