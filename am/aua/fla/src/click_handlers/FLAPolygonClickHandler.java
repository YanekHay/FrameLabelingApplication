package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import UI.layer_item.LayerItem;
import controllers.ToolBarController;
import core.Global;
import core.labeled_shapes.FLALabeledPolygon;

/**
 * This class represents a click handler for FLALabeledPolygon objects.
 * It handles mouse events and performs actions based on the event type.
 * It allows the user to draw polygons by clicking on a pane.
 *
 * @param <P> The type of the mouse area pane.
 * @param <T> The type of the draw area group.
 */
public class FLAPolygonClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T> {
    FLALabeledPolygon polygon = null;

    /**
     * Constructs a new FLAPolygonClickHandler with the specified mouse area and draw area.
     *
     * @param mouseArea The pane where the mouse events occur.
     * @param drawArea  The group where the polygons are drawn.
     */
    public FLAPolygonClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }

    /**
     * Handles the mouse press event.
     * If a polygon is not currently being drawn, a new polygon is created.
     * If the primary button is pressed, a point is added to the polygon.
     * If the secondary button is pressed, the current polygon is finished and a new one is created.
     *
     * @param event The mouse event.
     */
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();
        if (this.polygon == null) {
            this.newPolygon();
        }
        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        if (event.isPrimaryButtonDown()) {
            this.polygon.addPoint(clickPoint);
        } else if (event.isSecondaryButtonDown()) {
            this.finishDrawing();
            this.newPolygon();
        }
    }

    /**
     * Finishes the current polygon by closing it and adding it to the draw area.
     * Creates a new layer item for the polygon and adds it to the global layer list.
     */
    private void finishDrawing() {
        this.polygon.close();
        LayerItem<FLALabeledPolygon> layerItem = new LayerItem<>(this.polygon);
        Global.addLayer(layerItem);
    }

    /**
     * Creates a new polygon with the current label and draws it on the draw area.
     */
    private void newPolygon() {
        this.polygon = new FLALabeledPolygon(ToolBarController.getCurrentLabel());
        this.polygon.drawOnNode(this.drawArea);
    }

    /**
     * Selects the click handler by setting the cursor and registering the mouse press event.
     *
     * @return The selected click handler.
     */
    @Override
    public FLAClickHandler<P, T> select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
        return this;
    }

    /**
     * Deselects the click handler by unregistering the mouse press event and resetting the cursor.
     * If the current polygon has at least 3 points, it is finished and a new one is created.
     * Otherwise, the current polygon is removed from the draw area and a new one is created.
     */
    @Override
    public void deselect() {
        this.mouseArea.setOnMousePressed(null);
        this.mouseArea.setCursor(Cursor.DEFAULT);
        if (this.polygon.getPointCount() >= 3) {
            this.finishDrawing();
            this.polygon = new FLALabeledPolygon(ToolBarController.getCurrentLabel());
        } else {
            this.polygon.removeFromNode(drawArea);
            this.newPolygon();
        }
    }

}
