package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;

import core.shapes.FLAPoint2D;

public class FLAPointClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{

    public FLAPointClickHandler(P clickArea, T drawArea) {
        super(clickArea, drawArea);
    }
    
    @Override
    public void click(MouseEvent event) {
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
        this.clickArea.setCursor(Cursor.DEFAULT);
        this.clickArea.setOnMouseClicked(this::click);
    }
    
}
