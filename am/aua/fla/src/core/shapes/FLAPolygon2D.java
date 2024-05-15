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

/**
 * Represents a 2D polygon shape in the Frame Labeling Application.
 */
public class FLAPolygon2D extends FLAShape2D {
    protected ArrayList<FLAPoint2D> points = new ArrayList<>();
    protected ArrayList<FLALine2D> lines = new ArrayList<>();
    protected Polygon polygon = new Polygon();
    protected boolean isClosed = false;
    private ArrayList<DoubleProperty[]> pointProperties = new ArrayList<>();
    
    /**
     * Constructs a FLAPolygon2D object with the given points.
     * 
     * @param points The list of points that define the polygon.
     */
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

    /**
     * Constructs an empty FLAPolygon2D object.
     */
    public FLAPolygon2D() {
        this.polygon.setFill(Color.rgb(100,100,200,0.4));
        this.polygon.setCursor(Cursor.HAND);
        this.polygon.setOnMouseDragged(this::onMouseDragged);
        this.polygon.setOnMousePressed(this::onMousePressed);
    }

    /**
     * Handles the mouse pressed event.
     * 
     * @param e The mouse event.
     */
    public void onMousePressed(MouseEvent e){
        e.consume();
        this.mouseDown.set(Global.pointOnCanvas(e.getSceneX(), e.getSceneY()));
    }

    /**
     * Handles the mouse dragged event.
     * 
     * @param e The mouse event.
     */
    public void onMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = Global.pointOnCanvas(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));   
        this.mouseDown.set(mousePos);
    }

    /**
     * Returns the number of points in the polygon.
     * 
     * @return The number of points in the polygon.
     */
    public int getPointCount() {
        return points.size();
    }

    /**
     * Adds a point to the polygon.
     * 
     * @param point The point to add.
     */
    public void addPoint(Point2D point) {
        this.addPoint(this.points.size(), point);
    }

    /**
     * Adds a point to the polygon at the specified index.
     * 
     * @param index The index at which to add the point.
     * @param point The point to add.
     */
    public void addPoint(int index, Point2D point) {
        this.addPoint(index, new FLAPoint2D(point));
    }

    /**
     * Adds a FLAPoint2D object to the polygon.
     * 
     * @param point The FLAPoint2D object to add.
     */
    public void addPoint(FLAPoint2D point) {
        this.addPoint(this.points.size(), point);
    }

    /**
     * Adds a FLAPoint2D object to the polygon at the specified index.
     * 
     * @param index The index at which to add the point.
     * @param point The FLAPoint2D object to add.
     */
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

    /**
     * Checks if the polygon is closed.
     * 
     * @return true if the polygon is closed, false otherwise.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Closes the polygon.
     */
    public void close(){
        if (this.points.size()>=3){
            this.lines.add(new FLALine2D(points.get(this.getPointCount()-1), points.get(0)));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup); // TODO: Modify this part to depend on parent container
            this.isClosed = true;
        }
    }

    /**
     * Drags the polygon by the specified delta values.
     * 
     * @param dx The delta value for the x-coordinate.
     * @param dy The delta value for the y-coordinate.
     */
    @Override
    public void dragByDelta(double dx, double dy) {
        for (FLAPoint2D point : points) {
            point.dragByDelta(dx, dy);
        }
    }

    /**
     * Drags the polygon by the specified delta point.
     * 
     * @param point The delta point.
     */
    @Override
    public void dragByDelta(Point2D point) {
        this.dragByDelta(point.getX(), point.getY());
    }

    /**
     * Drags the polygon by the specified delta values relative to its center.
     * 
     * @param dx The delta value for the x-coordinate.
     * @param dy The delta value for the y-coordinate.
     */
    @Override
    public void drag(double dx, double dy) {
        double centerX = this.polygon.getLayoutX();
        double centerY = this.polygon.getLayoutY();
        for (FLAPoint2D point : points) {
            point.dragByDelta(point.getX() - centerX, point.getY() - centerY);
        }
    }

    /**
     * Drags the polygon by the specified delta point relative to its center.
     * 
     * @param point The delta point.
     */
    @Override
    public void drag(Point2D point) {
        this.drag(point.getX(), point.getY());
    }

    /**
     * Draws the polygon on the specified container node.
     * 
     * @param container The container node to draw on.
     */
    @Override
    public <T extends Group> void drawOnNode(T container) {
        if (!container.getChildren().contains(this.polygon))
            container.getChildren().add(this.polygon);   
        
            for (FLALine2D line : lines) {
            line.drawOnNode(container);
        }
    }

    /**
     * Removes the polygon from the specified container node.
     * 
     * @param container The container node to remove from.
     */
    @Override
    public <T extends Group> void removeFromNode(T container){
        for (FLALine2D line : lines) {
            line.removeFromNode(container);
        }
        container.getChildren().remove(this.polygon);
    }
    
    /**
     * Creates a clone of the FLAPolygon2D object.
     * 
     * @return A clone of the FLAPolygon2D object.
     */
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

    /**
     * Checks if the FLAPolygon2D object is equal to another object.
     * 
     * @param obj The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    /**
     * Returns a string representation of the FLAPolygon2D object.
     * 
     * @return A string representation of the FLAPolygon2D object.
     */
    @Override
    public String toString() {
        return "FLAPolygon2D [points=" + points + ", lines=" + lines + ", polygon=" + polygon + ", mouseDown=" + mouseDown
                + ", isClosed=" + isClosed + "]";
    }

    /**
     * Binds the component styles to the specified FLAStyle object.
     * 
     * @param style The FLAStyle object to bind to.
     */
    @Override
    public void bindComponentStylesTo(FLAStyle style) {
        for (FLALine2D line : lines) {
            line.bindComponentStylesTo(style);
        }
        this.polygon.fillProperty().bind(style.fillColorProperty());
    }

    /**
     * Removes the FLAPolygon2D object from the specified node.
     * 
     * @param node The node to remove from.
     */
    @Override
    public void remove(Group node){
        for (FLAPoint2D pt : this.points){
            pt.removeFromNode(node);
        }
        for (FLALine2D line: this.lines){
            line.remove(node);
        }
        node.getChildren().remove(this.polygon);
    }

}
