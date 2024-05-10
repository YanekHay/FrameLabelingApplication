package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import UI.layer_item.LayerItem;
import controllers.MainController;
import controllers.ToolBarController;
import core.Global;
import core.labeled_shapes.FLALabeledPolygon;
import core.labeled_shapes.FLALabeledRectangle;
import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.shapes.FLARectangle2D;

public class FLAPolygonClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{
    FLALabeledPolygon polygon=null;
    
    public FLAPolygonClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }
    
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();
        if (this.polygon == null){
            this.newPolygon();
        }
        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        if (event.isPrimaryButtonDown()){
            this.polygon.addPoint(clickPoint);
        }
        else if (event.isSecondaryButtonDown()){
            this.finishDrawing();
            this.newPolygon();
        }
    }

    private void finishDrawing(){
        this.polygon.close();
        LayerItem<FLALabeledPolygon> layerItem = new LayerItem<>(this.polygon);
        Global.addLayer(layerItem);
    }
    private void newPolygon(){
        this.polygon = new FLALabeledPolygon(ToolBarController.getCurrentLabel());
        this.polygon.drawOnNode(this.drawArea);
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
        if (this.polygon.getPointCount() >= 3){
            this.finishDrawing();
            this.polygon = new FLALabeledPolygon(ToolBarController.getCurrentLabel());
        }
        else{
            this.polygon.removeFromNode(drawArea);
            this.newPolygon();
        }
    }
    
}
