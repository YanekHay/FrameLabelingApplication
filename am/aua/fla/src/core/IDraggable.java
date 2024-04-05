package core;

import javafx.scene.input.MouseEvent;

public interface IDraggable {
    public void drag(double x, double y);
    //public void onMouseClicked(MouseEvent e);
    public void onMouseDragged(MouseEvent e);
    public void onMouseEntered(MouseEvent e);

}