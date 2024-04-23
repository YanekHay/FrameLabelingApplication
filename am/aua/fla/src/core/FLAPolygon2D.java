package core;

import controllers.FrameGroupController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
public class FLAPolygon2D extends FLAAnnotation2D implements IDraggable, IDrawable {
    private ArrayList<FLAPoint2D> points = new ArrayList<>();
    private ArrayList<FLALine2D> lines = new ArrayList<>();
    private ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    
    public FLAPolygon2D(ArrayList<FLAPoint2D> points) {
        this.points = points;
        for (int i = 0; i < this.getPointCount(); i++) {
            System.out.println(i + " --- " + (i + 1) % this.getPointCount());
            lines.add(new FLALine2D(points.get(i), points.get((i + 1) % this.getPointCount())));
        }
    }

    public int getPointCount() {
        return points.size();
    }

    public void addPoint(FLAPoint2D point) {
        
    }

    @Override
    public void drag(double dx, double dy) {
        for (FLAPoint2D point : points) {
            point.drag(point.getX() - dx, point.getY() - dy);
        }
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
        e.consume();
        this.mouseDown.set(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }


    @Override
    public void drawOnNode(Pane container) {
        for (FLALine2D line : lines) {
            line.drawOnNode(container);
        }
    }

    @Override
    public void drawOnNode(Group container) {
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
