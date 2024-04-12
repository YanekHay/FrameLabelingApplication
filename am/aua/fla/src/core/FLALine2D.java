package core;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FLALine2D extends FLAAnnotation2D implements IDraggable, IDrawable{
    private Line line;
    private FLAPoint2D startPoint;
    private FLAPoint2D endPoint;

    public FLALine2D(FLAPoint2D startPoint, FLAPoint2D endPoint) {
        line = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
        this.setStartPoint(startPoint);
        this.setEndPoint(endPoint);
        this.line.setOnMouseDragged(this::onMouseDragged);
        this.line.setOnMouseEntered(this::onMouseEntered);
        this.line.setOnMouseExited(this::onMouseExited);
        this.line.setOnMousePressed(this::onMousePressed);
        startPoint.setOnMouseDragged(this::startPointOnMouseDragged);
        endPoint.setOnMouseDragged(this::endPointOnMouseDragged);
        
    }

    public void setStartPoint(FLAPoint2D startPoint) {
        this.startPoint = startPoint;
        this.line.setStartX(startPoint.getX());
        this.line.setStartY(startPoint.getY());
    }

    public void setEndPoint(FLAPoint2D endPoint) {
        this.endPoint = endPoint;
        this.line.setEndX(endPoint.getX());
        this.line.setEndY(endPoint.getY());
    }   
    @Override
    public void drag(double x, double y) {
        // TODO Auto-generated method stub
        
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
    public void drawOnNode(Pane container) {
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
        container.getChildren().add(this.line);
    }

    @Override
    public void drawOnNode(Group container) {
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
        container.getChildren().add(this.line);
    }
    @Override
    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
        this.startPoint.setOnMouseDragged(eventHandler);
    }
    
    @Override
    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
        
    }

    public void moveStartPointTo(double x, double y) {
        this.startPoint.drag(x, y);
        this.line.setStartX(x);
        this.line.setStartY(y);
    }

    public void moveEndPointTo(double x, double y) {
        this.endPoint.drag(x, y);
        this.line.setEndX(x);
        this.line.setEndY(y);
    }

    public void startPointOnMouseDragged(MouseEvent e) {
        moveStartPointTo(e.getX(), e.getY());
    }
    
    
    public void endPointOnMouseDragged(MouseEvent e) {
        moveEndPointTo(e.getX(), e.getY());
    }
}
