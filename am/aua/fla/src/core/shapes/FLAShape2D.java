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

/**
 * Represents a 2D shape in the Frame Labeling Application.
 */
public abstract class FLAShape2D implements IDraggable, IDrawable, IRemovable, Cloneable{
    protected ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();
    protected ArrayList<Shape> shapeComponents;
    /**
     * Constructs an empty FLAShape2D object.
     */
    public FLAShape2D(){
        shapeComponents = new ArrayList<>();
    }
    /**
     * Constructs a FLAShape2D object with the given shape.
     * @param shape The shape to be added.
     */
    public FLAShape2D(Shape shape){
        this();
        shapeComponents.add(shape);
    }
    /**
     * Constructs a FLAShape2D object with the given shape components.
     * @param shapeComponents The list of shape components to be added.
     */
    public FLAShape2D(ArrayList<Shape> shapeComponents){
        this();
        this.shapeComponents.addAll(shapeComponents);
    }
    /**
     * Adds the shape to the given group.
     * @param node The group to which the shape will be added.
     */
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
    
    /**
     * Binds the shape components to the given style.
     * @param style The style to which the shape components will be bound.
     */
    public abstract void bindComponentStylesTo(FLAStyle style);
}