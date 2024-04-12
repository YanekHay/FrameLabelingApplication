package core;

import controllers.FrameGroupController;
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
        this.line.setOnMouseDragEntered(this::onMouseDragEntered);
        this.line.setOnMouseDragExited(this::onMouseDragExited);
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
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
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
        container.getChildren().add(this.line);
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
    }

    @Override
    public void drawOnNode(Group container) {
        container.getChildren().add(this.line);
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
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
    public void moveStartPointTo(Point2D point) {
        this.moveStartPointTo(point.getX(), point.getY());
    }

    public void moveEndPointTo(double x, double y) {
        this.endPoint.drag(x, y);
        this.line.setEndX(x);
        this.line.setEndY(y);
    }
    public void moveEndPointTo(Point2D point) {
        this.moveEndPointTo(point.getX(), point.getY());
    }

    public void startPointOnMouseDragged(MouseEvent e) {
        moveStartPointTo(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }
    
    
    public void endPointOnMouseDragged(MouseEvent e) {
        moveEndPointTo(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }

    @Override
    public void onMouseDragEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMouseDragEntered'");
    }

    @Override
    public void onMouseDragExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMouseDragExited'");
    }
}
