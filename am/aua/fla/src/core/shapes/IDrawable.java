package core.shapes;

import javafx.scene.Group;

/**
 * The IDrawable interface represents an object that can be drawn on a JavaFX container.
 */
public interface IDrawable {
    
    /**
     * Draws the object on the specified container.
     * 
     * @param container the JavaFX container to draw the object on
     * @param <T> the type of the container, must extend Group
     */
    public <T extends Group> void drawOnNode(T container);
    
    /**
     * Removes the object from the specified container.
     * 
     * @param container the JavaFX container to remove the object from
     * @param <T> the type of the container, must extend Group
     */
    public <T extends Group> void removeFromNode(T container);
}
