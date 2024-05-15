package click_handlers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The FLAClickHandler class is an abstract class that represents a click handler for a mouse event.
 * It provides methods for handling mouse press events, selecting and deselecting the click handler.
 *
 * @param <P> the type of the mouse area (extends Pane)
 * @param <T> the type of the draw area (extends Group)
 */
public abstract class FLAClickHandler<P extends Pane, T extends Group> {
    protected P mouseArea;
    protected T drawArea;

    /**
     * Constructs a FLAClickHandler with the specified mouse area and draw area.
     *
     * @param mouseArea the mouse area to associate with the click handler
     * @param drawArea the draw area to associate with the click handler
     */
    protected FLAClickHandler(P mouseArea, T drawArea) {
        this.setMouseArea(mouseArea);
        this.drawArea = drawArea;
    }

    /**
     * Handles the mouse press event.
     *
     * @param event the mouse event
     */
    public abstract void mousePress(MouseEvent event);

    /**
     * Sets the mouse area for the click handler.
     *
     * @param mouseArea the mouse area to set
     */
    public void setMouseArea(P mouseArea) {
        this.mouseArea = mouseArea;
    }

    /**
     * Selects the click handler.
     *
     * @return the selected click handler
     */
    public abstract FLAClickHandler<P, T> select();

    /**
     * Deselects the click handler.
     */
    public abstract void deselect();
}
