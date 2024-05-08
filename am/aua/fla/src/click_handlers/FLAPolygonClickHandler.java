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
        this.polygon.drawOnNode(this.drawArea);
    }
    
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();

        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        double x = clickPoint.getX();
        double y = clickPoint.getY(); 
        if (event.isPrimaryButtonDown()){
            this.polygon.addPoint(clickPoint);
        }
        else if (event.isSecondaryButtonDown()){
            this.polygon.close();
            this.polygon = new FLAPolygon2D();
        }
        System.out.println("POINT: click at (" + x + ", " + y + ")");
    }
    // @Override
    // public void mouseMove(MouseEvent event) {
    //     event.consume();
    //     Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
    //     double x = clickPoint.getX();
    //     double y = clickPoint.getY(); 
    //     FLAPoint2D pt = new FLAPoint2D(x, y);
    //     pt.drawOnNode(this.drawArea);
    //     System.out.println("POINT: click at (" + x + ", " + y + ")");
    // }

    @Override
    public void select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
    }
    
}
