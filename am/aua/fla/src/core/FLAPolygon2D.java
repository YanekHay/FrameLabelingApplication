package core;

import controllers.FrameGroupController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
public class FLAPolygon2D extends FLAAnnotation2D implements IDraggable, IDrawable {
    private ArrayList<FLAPoint2D> points = new ArrayList<>();
    private ArrayList<FLALine2D> lines = new ArrayList<>();
    private Polygon polygon = new Polygon();
    private ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    private boolean isClosed = false;
    
    public FLAPolygon2D(ArrayList<FLAPoint2D> points) {
        this.points = points;
        for (int i = 0; i < this.getPointCount(); i++) {
            lines.add(new FLALine2D(points.get(i), points.get((i + 1) % this.getPointCount())));
            this.addPoint(i, this.points.get(i));
        }
        this.isClosed = true;
        this.polygon.setFill(Color.rgb(100,100,100,0.5));
        this.closePolygon();
    }

    public FLAPolygon2D() {
        polygon.setFill(Color.rgb(100,100,100,0.5));
        this.polygon.setOnMouseDragged(this::polygonOnMouseDragged);
        this.polygon.setOnMousePressed(this::polygonOnMousePressed);
    }

    private void polygonOnMousePressed(MouseEvent e){
        e.consume();
        this.mouseDown.set(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }

    private void polygonOnMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));   
        this.mouseDown.set(mousePos);

    }

    public int getPointCount() {
        return points.size();
    }


    public void addPoint(FLAPoint2D point) {
        this.addPoint(this.points.size(), point);
    }

    public void addPoint(int index, FLAPoint2D point) {
        this.points.add(point);
        point.drawOnNode(FrameGroupController.frameGroup);

        if (index>0){
            this.lines.add(new FLALine2D(points.get(index-1), points.get((index) % this.getPointCount())));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup);
        }
        
        DoubleProperty xProperty = new SimpleDoubleProperty(points.get(index).getX());
        DoubleProperty yProperty = new SimpleDoubleProperty(points.get(index).getY());
        xProperty.bind(points.get(index).xProperty());
        yProperty.bind(points.get(index).yProperty());
        
        polygon.getPoints().add(xProperty.get());
        polygon.getPoints().add(yProperty.get());

        xProperty.addListener((observable, oldValue, newValue) -> {
            polygon.getPoints().set(index*2, newValue.doubleValue());
        });
        yProperty.addListener((observable, oldValue, newValue) ->{
            polygon.getPoints().set(index*2+1, newValue.doubleValue());
        });

    }

    public boolean isClosed() {
        return isClosed;
    }

    public void closePolygon(){
        if (this.points.size()>=3){
            this.lines.add(new FLALine2D(points.get(this.getPointCount()-1), points.get(0)));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup);
            this.isClosed = true;
        }
    }

    @Override
    public void dragByDelta(double dx, double dy) {
        for (FLAPoint2D point : points) {
            point.dragByDelta(dx, dy);
        }
    }

    @Override
    public void dragByDelta(Point2D point) {
        this.dragByDelta(point.getX(), point.getY());
    }

    
    @Override
    public void drag(double dx, double dy) {
        double centerX = this.polygon.getLayoutX();
        double centerY = this.polygon.getLayoutY();
        for (FLAPoint2D point : points) {
            point.dragByDelta(point.getX() - centerX, point.getY() - centerY);
        }
    }

    @Override
    public void drag(Point2D point) {
        this.drag(point.getX(), point.getY());
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
        e.consume();
        this.mouseDown.set(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }


    @Override
    public void drawOnNode(Pane container) {
        container.getChildren().add(this.polygon);   
        for (FLALine2D line : lines) {
            line.drawOnNode(container);
        }
        
    }

    @Override
    public void drawOnNode(Group container) {
        container.getChildren().add(this.polygon);   
        for (FLALine2D line : lines) {
            line.drawOnNode(container);
        }
    }

    @Override
    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler) {
        // TODO Auto-generated method stub
        
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
