package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import core.shapes.FLAPoint2D;

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
        FLAPoint2D pt = new FLAPoint2D(x, y);
        pt.drawOnNode(this.drawArea);
        System.out.println("POINT: click at (" + x + ", " + y + ")");
    }

    @Override
    public void select() {
        this.mouseArea.setCursor(Cursor.DEFAULT);
        this.mouseArea.setOnMousePressed(this::mousePress);
    }
    
}
