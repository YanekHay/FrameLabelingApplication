/**
 * This class represents a click handler for points in the Frame Labeling Application.
 * It extends the FLAClickHandler class and handles mouse events related to points.
 */
package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import UI.layer_item.LayerItem;
import controllers.ToolBarController;
import core.Global;
import core.labeled_shapes.FLALabeledPoint;

public class FLAPointClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{

    /**
     * Constructs a FLAPointClickHandler with the specified mouse area and draw area.
     * @param mouseArea the mouse area where the click handler is applied
     * @param drawArea the draw area where the points are displayed
     */
    public FLAPointClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }
    
    /**
     * Handles the mouse press event.
     * Creates a new FLALabeledPoint at the clicked coordinates and adds it to the draw area.
     * @param event the mouse event
     */
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();
        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        double x = clickPoint.getX();
        double y = clickPoint.getY(); 
        if (event.isPrimaryButtonDown()){
            FLALabeledPoint pt = new FLALabeledPoint(x, y, ToolBarController.getCurrentLabel());
            LayerItem<FLALabeledPoint> layerItem = new LayerItem<>(pt);
            Global.addLayer(layerItem);
            pt.drawOnNode(this.drawArea);
        }
    }

    /**
     * Sets the cursor to CROSSHAIR and registers the mouse press event handler.
     * @return the FLAPointClickHandler instance
     */
    @Override
    public FLAClickHandler<P, T> select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
        return this;
    }
    
    /**
     * Removes the mouse press event handler and sets the cursor to DEFAULT.
     */
    @Override
    public void deselect() {
        this.mouseArea.setOnMousePressed(null);
        this.mouseArea.setCursor(Cursor.DEFAULT);
    }
}
