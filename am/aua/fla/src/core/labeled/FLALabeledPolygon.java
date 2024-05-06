package core.labeled;

import java.util.ArrayList;

import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;

public class FLALabeledPolygon extends FLALabeledShape{
    private FLAPolygon2D shape; 

    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, int classNumber, String className, FLAStyle style){
        super(new FLALabel(classNumber, className));
        this.shape = new FLAPolygon2D(points);
        this.setStyle(style);
    }

    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, int classNumber, String className){
        this(points, classNumber, className, new FLAStyle());
    }

    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, int classNumber){
        this(points, classNumber, "Unknown");
    }

    @Override
    public void setStyle(FLAStyle style) {
        this.style = style;
        this.shape.bindComponentStylesTo(style);
    }

    @Override
    public void setShape(FLAShape2D shape) {
        if (shape instanceof FLAPolygon2D){
            this.shape = (FLAPolygon2D) shape;
        } else {
            throw new IllegalArgumentException("Invalid shape type: " + shape.getClass().getName() + " Expected: " + this.getClass().getName());
        }
    }

    @Override
    public FLAShape2D getShape() {
        return this.shape;
    }
    
}
