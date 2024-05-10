package core.shapes;
import core.labeled_shapes.IRemovable;
import core.shapes.IDraggable;
import core.shapes.IDrawable;
import core.styled.FLAStyle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class FLAShape2D implements IDraggable, IDrawable, IRemovable, Cloneable{
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

    @Override
    public abstract boolean equals(Object obj);
    
    @Override
    public abstract String toString();
    
    @Override
    public abstract void remove(Group node);
    
    public abstract void bindComponentStylesTo(FLAStyle style);
}