package core.labeled;

import core.shapes.FLAPoint2D;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;

public class FLALabeledPoint extends FLALabeledShape{
    private FLAPoint2D shape; 

    public FLALabeledPoint(double x, double y, int classNumber, String className, FLAStyle style){
        super(new FLALabel(classNumber, className));
        this.shape = new FLAPoint2D(x, y);
        this.setStyle(style);
    }

    public FLALabeledPoint(double x, double y, int classNumber, String className){
        this(x, y, classNumber, className, new FLAStyle());
    }

    public FLALabeledPoint(double x, double y, int classNumber){
        this(x, y, classNumber, "Unknown");
    }

    @Override
    public void setStyle(FLAStyle style) {
        this.style = style;
        this.shape.bindComponentStylesTo(style);
    }

    @Override
    public void setShape(FLAShape2D shape) {
        if (shape instanceof FLAPoint2D){
            this.shape = (FLAPoint2D) shape;
        } else {
            throw new IllegalArgumentException("Invalid shape type: " + shape.getClass().getName() + " Expected: FLAPoint2D");
        }
    }

    @Override
    public FLAShape2D getShape() {
        return this.shape;
    }
    
}
