package click_handlers;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The FLASelectClickHandler class represents a click handler for selecting objects in a drawing area.
 * It extends the FLAClickHandler class and provides methods for handling mouse events related to selection.
 *
 * @param <P> the type of the mouse area (e.g., Pane)
 * @param <T> the type of the draw area (e.g., Group)
 */
public class FLASelectClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T> {
    
    /**
     * Constructs a FLASelectClickHandler with the specified mouse area and draw area.
     *
     * @param mouseArea the mouse area where the click handler is applied
     * @param drawArea the draw area where the objects are displayed
     */
    public FLASelectClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }
    
    /**
     * Handles the mouse press event.
     *
     * @param event the mouse event
     */
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();

        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        double x = clickPoint.getX();
        double y = clickPoint.getY(); 
        System.out.println("SELECT: click at (" + x + ", " + y + ")");
    }

    /**
     * Sets the click handler to the select mode.
     *
     * @return the click handler in select mode
     */
    @Override
    public FLAClickHandler<P, T> select() {
        this.mouseArea.setCursor(Cursor.DEFAULT);
        this.mouseArea.setOnMousePressed(this::mousePress);
        return this;
    }

    /**
     * Deselects the click handler.
     */
    @Override
    public void deselect() {
        /* Nothing to do */
    }
}
