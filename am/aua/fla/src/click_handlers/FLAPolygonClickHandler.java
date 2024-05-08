package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.shapes.FLARectangle2D;

public class FLAPolygonClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{
    FLAPolygon2D polygon = new FLAPolygon2D();
    
    public FLAPolygonClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
    }
    
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();

        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        if (event.isPrimaryButtonDown()){
            this.polygon.addPoint(clickPoint);
        }
        else if (event.isSecondaryButtonDown()){
            this.polygon.close();
            this.newPolygon();
        }
    }

    private void newPolygon(){
        this.polygon = new FLAPolygon2D();
        this.polygon.drawOnNode(this.drawArea);
    }

    @Override
    public FLAClickHandler<P, T> select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
        this.polygon.drawOnNode(this.drawArea);
        return this;
    }

    @Override
    public void deselect() {
        this.mouseArea.setOnMousePressed(null);
        this.mouseArea.setCursor(Cursor.DEFAULT);
        if (this.polygon.getPointCount() >= 3){
            this.polygon.close();
            this.polygon = new FLAPolygon2D();
        }
        else{
            this.polygon.removeFromNode(drawArea);
            this.newPolygon();
        }
    }
    
}
