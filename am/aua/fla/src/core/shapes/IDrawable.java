package core.shapes;

import javafx.scene.Group;

public interface IDrawable {
    public <T extends Group> void drawOnNode(T container);
    public <T extends Group> void removeFromNode(T container);
}
