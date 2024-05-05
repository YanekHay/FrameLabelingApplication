package core;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public interface IDraggable {
    public void drag(double x, double y);
    public void drag(Point2D point);
    public void dragByDelta(double dx, double dy);
    public void dragByDelta(Point2D dP);
    public void onMouseDragged(MouseEvent e);
    public void onMousePressed(MouseEvent e);
    public void setMouseDown(Point2D mouseDown);
}
