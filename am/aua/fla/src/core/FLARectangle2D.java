package core;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FLARectangle2D extends FLAAnnotation2D implements IDraggable, IDrawable {
    private Point2D topLeft;
    private Point2D bottomRight;
    
    public void Rectangle2D(Point2D topleft, Point2D bottomRight) {
        this.topLeft = topleft;
        this.bottomRight = bottomRight;
        new Rectangle();
    }
    public void setTopLeft(Point2D topLeft) {
        this.topLeft = topLeft;
    }
    public Point2D getTopLeft() {
        return topLeft;
    }
    public void setBottomRight(Point2D bottomRight) {
        this.bottomRight = bottomRight;
    }
    public Point2D getBottomRight() {
        return bottomRight;
    }
    public double getWidth() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }
    public double getHeight() {
        return Math.abs(bottomRight.getY() - topLeft.getY());
    }
    public double getArea() {
        return getWidth() * getHeight();
    }


     
    @Override
    public void drawOnNode(Pane container) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void drawOnNode(Group container) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void drag(double dx, double dy) {
        // TODO Auto-generated method stub
        double newTopLeftX = topLeft.getX() + dx;
        double newTopLeftY = topLeft.getY() + dx;
        double newBottomRightX = bottomRight.getX() + dx;
        double newBottomRightY = bottomRight.getY() + dx;

        topLeft = new Point2D(newBottomRightX, newBottomRightY);
        bottomRight = new Point2D(newBottomRightX, newBottomRightY);

    }

    @Override
    public void drag(Point2D point) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onMouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
    } 
}