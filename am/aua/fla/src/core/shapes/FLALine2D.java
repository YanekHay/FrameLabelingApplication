package core.shapes;

import controllers.FrameGroupController;
import core.Global;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class FLALine2D extends FLAShape2D {
    private Line line;
    private FLAPoint2D startPoint;
    private FLAPoint2D endPoint;
    
    public FLALine2D(FLAPoint2D stP, FLAPoint2D endP) {
        super();
        line = new Line(stP.getX(), stP.getY(), endP.getX(), endP.getY());
        this.setStartPoint(stP);
        this.setEndPoint(endP);
        this.line.strokeWidthProperty().bind(Global.worldScaleInverse.multiply(3));
        this.line.setCursor(Cursor.MOVE);
        this.line.setOnMouseDragged(this::onMouseDragged);
        this.line.setOnMousePressed(this::onMousePressed);
        
    }

    public void setStartPoint(FLAPoint2D startPoint) {
        this.startPoint = startPoint;
        this.line.startXProperty().bind(this.startPoint.xProperty());
        this.line.startYProperty().bind(this.startPoint.yProperty());
    }

    public void setEndPoint(FLAPoint2D endPoint) {
        this.endPoint = endPoint;
        this.line.endXProperty().bind(this.endPoint.xProperty());
        this.line.endYProperty().bind(this.endPoint.yProperty());
    }   

    @Override
    public void dragByDelta(double dx, double dy) {
        this.startPoint.dragByDelta(dx, dy);
        this.endPoint.dragByDelta(dx, dy);
    }

    @Override
    public void dragByDelta(Point2D dPoint) {
        this.dragByDelta(dPoint.getX(), dPoint.getY());
    }

    @Override
    public void drag(double x, double y) {
        this.startPoint.drag(x, y);
        this.endPoint.drag(x, y);
    }

    @Override
    public void drag(Point2D point) {
        this.drag(point.getX(), point.getY());
    }


    @Override
    public void onMouseDragged(MouseEvent e) {
        e.consume();
        Point2D mousePos = FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY());
        this.dragByDelta(this.mouseDown.get().subtract(mousePos));      
        this.mouseDown.set(mousePos);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        e.consume();
        this.mouseDown.set(FrameGroupController.frameGroup.sceneToLocal(e.getSceneX(), e.getSceneY()));
    }

    @Override
    public void drawOnNode(Pane container) {
        if (!container.getChildren().contains(this.line))
            container.getChildren().add(this.line);
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
        this.line.toBack();
    }

    @Override
    public void drawOnNode(Group container) {
        if (!container.getChildren().contains(this.line))
            container.getChildren().add(this.line);
        startPoint.drawOnNode(container);
        endPoint.drawOnNode(container);
    }

    @Override
    public FLAShape2D clone() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clone'");
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

}
