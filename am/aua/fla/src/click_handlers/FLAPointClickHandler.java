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
import core.labeled_shapes.FLALabeledPolygon;

public class FLAPointClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{

    public FLAPointClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }
    
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

    @Override
    public FLAClickHandler<P, T> select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
        return this;
    }
    
    @Override
    public void deselect() {
        this.mouseArea.setOnMousePressed(null);
        this.mouseArea.setCursor(Cursor.DEFAULT);
    }
}
