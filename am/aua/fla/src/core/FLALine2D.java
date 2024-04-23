package core;

import controllers.FrameGroupController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FLALine2D extends FLAAnnotation2D implements IDraggable, IDrawable{
    private Line line;
    private FLAPoint2D startPoint;
    private FLAPoint2D endPoint;
    private ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    
    public FLALine2D(FLAPoint2D stP, FLAPoint2D endP) {
        line = new Line(stP.getX(), stP.getY(), endP.getX(), endP.getY());
        this.setStartPoint(stP);
        this.setEndPoint(endP);
        this.line.setStrokeWidth(3);
        this.line.setCursor(Cursor.MOVE);
        this.line.setOnMouseDragged(this::onMouseDragged);
        this.line.setOnMouseEntered(this::onMouseEntered);
        this.line.setOnMouseExited(this::onMouseExited);
        this.line.setOnMousePressed(this::onMousePressed);
        this.line.setOnMouseDragEntered(this::onMouseDragEntered);
        this.line.setOnMouseDragExited(this::onMouseDragExited);



        this.startPoint.setOnMouseDragged(this::startPointOnMouseDragged);
        this.endPoint.setOnMouseDragged(this::endPointOnMouseDragged);
        
    }

    public void setStartPoint(FLAPoint2D startPoint) {
        this.startPoint = startPoint;
        this.line.startXProperty().bind(this.startPoint.xProperty());
        this.line.startYProperty().bind(this.startPoint.yProperty());
    }

    public void setEndPoint(FLAPoint2D endPoint) {
        this.endPoint = endPoint;
        this.line.endXProperty().bind(this.endPoint.xProperty());
        this.line.endYProperty().bind(this.endPoint.yProperty());
    }   

    @Override
    public void drag(double dx, double dy) {
        this.startPoint.drag(this.startPoint.getX()-dx,this.startPoint.getY()-dy);
        this.endPoint.drag(this.endPoint.getX()-dx,this.endPoint.getY()-dy);
    }

    @Override
    public void drag(Point2D dPoint) {
        this.drag(dPoint.getX(), dPoint.getY());
    }


    @Override
    public void onMouseDragged(MouseEvent e) {
        Point2D mousePos = FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY());

        this.drag(this.mouseDown.get().subtract(mousePos));      
        this.mouseDown.set(mousePos);
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
        e.consume();
        this.mouseDown.set(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }

    @Override
    public void drawOnNode(Pane container) {
        container.getChildren().add(this.line);
        this.line.toBack();
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


    public void startPointOnMouseDragged(MouseEvent e) {
        this.startPoint.drag(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }
    
    
    public void endPointOnMouseDragged(MouseEvent e) {
        this.endPoint.drag(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
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

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }
}
