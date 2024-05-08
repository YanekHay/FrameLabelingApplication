package click_handlers;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FLASelectClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T> {
    
    public FLASelectClickHandler(P clickArea, T drawArea) {
        super(clickArea, drawArea);
    }
    
    @Override
    public void click(MouseEvent event) {
        event.consume();

        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        double x = clickPoint.getX();
        double y = clickPoint.getY(); 
        System.out.println("SELECT: click at (" + x + ", " + y + ")");
    }

    @Override
    public void select() {
        this.clickArea.setCursor(Cursor.DEFAULT);
        this.clickArea.setOnMouseClicked(this::click);
    }
}
