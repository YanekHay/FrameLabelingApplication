package core.shapes;
import core.IDraggable;
import core.IDrawable;
import core.styled.FLAStyle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class FLAShape2D implements IDraggable, IDrawable, Cloneable{
    protected ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    protected ArrayList<Shape> shapeComponents;

    public FLAShape2D(){
        shapeComponents = new ArrayList<>();
    }

    public FLAShape2D(Shape shape){
        this();
        shapeComponents.add(shape);
    }

    public FLAShape2D(ArrayList<Shape> shapeComponents){
        this();
        this.shapeComponents.addAll(shapeComponents);
    }

    public Point2D getMouseDown() {
        return mouseDown.get();
    }

    @Override
    public void setMouseDown(Point2D mouseDown) {
        this.mouseDown.set(mouseDown);
    }

    public void bindComponentStylesTo(FLAStyle style){
        for (Shape shapeComponent : shapeComponents){
            shapeComponent.strokeWidthProperty().bind(style.strokeWidthProperty());
            shapeComponent.fillProperty().bind(style.fillColorProperty());
            shapeComponent.strokeProperty().bind(style.strokeColorProperty());
        }
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}