package core;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public interface IDraggable {
    public double getX();
    public double getY();
    public void drag(double x, double y);
    public void drag(Point2D point);
    public void onMouseDragged(MouseEvent e);
    public void onMouseEntered(MouseEvent e);
    public void onMouseExited(MouseEvent e);
    public void onMousePressed(MouseEvent e);
    public void onMouseDragEntered(MouseEvent e);
    public void onMouseDragExited(MouseEvent e);
    public void setOnMouseDragged(EventHandler<MouseEvent> eventHandler);
    public void setOnMouseEntered(EventHandler<MouseEvent> eventHandler);

}
