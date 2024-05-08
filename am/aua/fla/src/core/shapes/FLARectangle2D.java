package core.shapes;

import core.Global;
import core.styled.FLAStyle;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FLARectangle2D extends FLAShape2D {
    private FLAPoint2D topLeft;
    private FLAPoint2D topRight;
    private FLAPoint2D bottomRight;
    private FLAPoint2D bottomLeft;

    private FLAPoint2D[] points = new FLAPoint2D[4];
    private FLALine2D[] lines = new FLALine2D[4];
    private Rectangle rectangle = new Rectangle();
    
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
    }

    public FLARectangle2D(Point2D topleft) {
        this(topleft.getX(), topleft.getY(), topleft.getX(), topleft.getY());
    }



    public FLARectangle2D(Point2D topleft, Point2D bottomRight) {
        this(topleft.getX(), topleft.getY(), bottomRight.getX(), bottomRight.getY());
    }

    private void addLines(){
        for(int i=0; i<this.points.length; i++){
            this.lines[i] = new FLALine2D(this.points[i], this.points[(i+1)%4]);
        }

        this.lines[0].makeHorizontal();
        this.lines[1].makeVertical();
        this.lines[2].makeHorizontal();
        this.lines[3].makeVertical();
    }

    private void onPointXPropChange(){
        this.rectangle.setX(Math.min(this.topLeft.getX(), this.topRight.getX()));
        this.rectangle.setWidth(Math.abs(this.topRight.getX() - this.topLeft.getX()));

    }

    private void onPointYPropChange(){
        this.rectangle.setY(Math.min(this.topLeft.getY(), this.bottomLeft.getY()));
        this.rectangle.setHeight(Math.abs(this.bottomLeft.getY() - this.topLeft.getY()));
    }

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

    public FLAPoint2D getStartPoint() {
        return this.topLeft;
    }

    public FLAPoint2D getEndPoint() {
        return this.bottomRight;
    }

    public void moveBottomRightTo(Point2D point){
        this.bottomRight.drag(point);
    }
    public double getWidth() {
        return this.rectangle.getWidth();
    }
    public double getHeight() {
        return this.rectangle.getHeight();
    }
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bindComponentStylesTo'");
    } 
}