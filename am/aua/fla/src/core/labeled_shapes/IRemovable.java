package core.labeled_shapes;

import javafx.scene.Group;

/**
 * The IRemovable interface represents a removable object in a graphical user interface.
 * Classes that implement this interface can be removed from a JavaFX Group node.
 */
public interface IRemovable {
    /**
     * Removes the object from the specified JavaFX Group node.
     *
     * @param node the JavaFX Group node from which the object should be removed
     */
    public void remove(Group node);
}