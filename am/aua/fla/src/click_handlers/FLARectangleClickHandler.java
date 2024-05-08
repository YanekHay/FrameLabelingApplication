package click_handlers;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.Group;

import javafx.geometry.Point2D;
import core.shapes.FLARectangle2D;

public class FLARectangleClickHandler<P extends Pane, T extends Group> extends FLAClickHandler<P, T>{
    FLARectangle2D rectangle = null;
    
    public FLARectangleClickHandler(P mouseArea, T drawArea) {
        super(mouseArea, drawArea);
        this.mouseArea.addEventHandler(MouseEvent.MOUSE_MOVED, this::mouseMove);
        this.mouseArea.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseMove);
        this.mouseArea.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseReleased);
    }
    
    @Override
    public void mousePress(MouseEvent event) {
        event.consume();
        if (!event.isPrimaryButtonDown()){
            return;
        }
        Point2D clickPoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        double x = clickPoint.getX();
        double y = clickPoint.getY(); 
        if (this.rectangle == null){
            startDrawing(clickPoint);
        }
        else {
            rectangle.moveBottomRightTo(clickPoint);
            this.finishDrawing();
        }
        System.out.println("POINT: click at (" + x + ", " + y + ")");
    }

    public void mouseMove(MouseEvent event) {
        event.consume();
        Point2D mousePoint = this.drawArea.parentToLocal(event.getX(), event.getY());
        if (this.rectangle != null){
            this.rectangle.moveBottomRightTo(mousePoint);
        }
    }

    public void mouseReleased(MouseEvent event) {
        event.consume();
        if (this.rectangle != null && !this.rectangle.getStartPoint().equals(this.rectangle.getEndPoint())){
            this.finishDrawing();
        }
    }

    private void startDrawing(Point2D startPoint){
        rectangle = new FLARectangle2D(startPoint);
        rectangle.drawOnNode(this.drawArea);
        rectangle.getEndPoint().setConsumeMousePressed(false);
    }

    private void finishDrawing(){
        rectangle.getEndPoint().setConsumeMousePressed(true);
        rectangle = null;
    }

    @Override
    public void select() {
        this.mouseArea.setCursor(Cursor.CROSSHAIR);
        this.mouseArea.setOnMousePressed(this::mousePress);
    }
    
}
