package core;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public interface IDrawable {
    public void drawOnNode(Pane container);
    public void drawOnNode(Group container);
}
