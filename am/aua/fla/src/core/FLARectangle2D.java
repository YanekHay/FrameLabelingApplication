package core;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FLARectangle2D extends FLAAnnotation2D implements IDraggable, IDrawable {

    private FLAPoint2D p1;
    private FLAPoint2D p2;

    public void setP1(FLAPoint2D p1) {
        this.p1 = p1;
    }

    public void setP2(FLAPoint2D p2) {
        this.p2 = p2;
    }

    public int getXmin() {
        return (int) Math.min(p1.getX(), p2.getX());
    }

    public int getXmax() {
        return (int) Math.max(p1.getX(), p2.getX());
    }

    public int getYmin() {
        return (int) Math.min(p1.getY(), p2.getY());
    }

    public int getYmax() {
        return (int) Math.max(p1.getY(), p2.getY());
    }

    public int getWidth() {
        return getXmax() - getXmin();
    }

    public int getHeight() {
        return getYmax() - getYmin();
    }

    public FLAPoint2D getP1() {
        return p1;
    }

    public FLAPoint2D getP2() {
        return p2;
    }

    @Override
    public void drawOnNode(Pane container) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawOnNode'");
    }

    @Override
    public void drag(double x, double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drag'");
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMouseDragged'");
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
        
        throw new UnsupportedOperationException("Unimplemented method 'onMouseEntered'");
    }

}
