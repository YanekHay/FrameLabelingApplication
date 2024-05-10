package core.shapes;

import controllers.FrameGroupController;
import core.Global;
import core.styled.FLAStyle;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.ArrayList;

public class FLAPolygon2D extends FLAShape2D {
    private ArrayList<FLAPoint2D> points = new ArrayList<>();
    private ArrayList<FLALine2D> lines = new ArrayList<>();
    private Polygon polygon = new Polygon();
    private boolean isClosed = false;
    private ArrayList<DoubleProperty[]> pointProperties = new ArrayList<>();
    
    public FLAPolygon2D(ArrayList<FLAPoint2D> points) {
        this.points = points;
        for (int i = 0; i < this.getPointCount(); i++) {
            lines.add(new FLALine2D(points.get(i), points.get((i + 1) % this.getPointCount())));
            this.addPoint(i, this.points.get(i));
        }
        this.isClosed = true;
        this.polygon.setCursor(Cursor.HAND);
        this.polygon.setFill(Color.rgb(100,100,200,0.4));
        this.close();
    }

    public FLAPolygon2D() {
        this.polygon.setFill(Color.rgb(100,100,200,0.4));
        this.polygon.setCursor(Cursor.HAND);
        this.polygon.setOnMouseDragged(this::onMouseDragged);
        this.polygon.setOnMousePressed(this::onMousePressed);
    }

    public void onMousePressed(MouseEvent e){
        e.consume();
        this.mouseDown.set(Global.pointOnCanvas(e.getSceneX(), e.getSceneY()));
    }

    public void onMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = Global.pointOnCanvas(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));   
        this.mouseDown.set(mousePos);
    }

    public int getPointCount() {
        return points.size();
    }

    public void addPoint(Point2D point) {
        this.addPoint(this.points.size(), point);
    }
    public void addPoint(int index, Point2D point) {
        this.addPoint(index, new FLAPoint2D(point));
    }

    public void addPoint(FLAPoint2D point) {
        this.addPoint(this.points.size(), point);
    }

    public void addPoint(int index, FLAPoint2D point) {
        this.points.add(point);
        point.drawOnNode(FrameGroupController.frameGroup); // TODO: Modify this part to depend on parent container

        if (index>0){
            this.lines.add(new FLALine2D(points.get(index-1), points.get((index) % this.getPointCount())));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup); // TODO: Modify this part to depend on parent container
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
        
        pointProperties.add(new DoubleProperty[]{xProperty, yProperty}); // Keeping the properties for garbage collector not to collect them
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void close(){
        if (this.points.size()>=3){
            this.lines.add(new FLALine2D(points.get(this.getPointCount()-1), points.get(0)));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup); // TODO: Modify this part to depend on parent container
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
    public <T extends Group> void drawOnNode(T container) {
        if (!container.getChildren().contains(this.polygon))
            container.getChildren().add(this.polygon);   
        
            for (FLALine2D line : lines) {
            line.drawOnNode(container);
        }
    }

    @Override
    public <T extends Group> void removeFromNode(T container){
        for (FLALine2D line : lines) {
            line.removeFromNode(container);
        }
        container.getChildren().remove(this.polygon);
    }
    
    @Override
    public FLAPolygon2D clone() {
        ArrayList<FLAPoint2D> clonedPoints = new ArrayList<>();
        for (FLAPoint2D point : points) {
            clonedPoints.add(point.clone());
        }
        FLAPolygon2D clonedPolygon = new FLAPolygon2D(clonedPoints);
        clonedPolygon.isClosed = this.isClosed;
        return clonedPolygon;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    @Override
    public String toString() {
        return "FLAPolygon2D [points=" + points + ", lines=" + lines + ", polygon=" + polygon + ", mouseDown=" + mouseDown
                + ", isClosed=" + isClosed + "]";
    }

    @Override
    public void bindComponentStylesTo(FLAStyle style) {
        for (FLAPoint2D point : points) {
            point.bindComponentStylesTo(style);
        }
        for (FLALine2D line : lines) {
            line.bindComponentStylesTo(style);
        }
        this.polygon.fillProperty().bind(style.fillColorProperty());
    }

}
