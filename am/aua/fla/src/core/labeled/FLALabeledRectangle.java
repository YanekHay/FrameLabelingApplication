package core.labeled;

import core.shapes.FLARectangle2D;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;
import javafx.geometry.Point2D;

public class FLALabeledRectangle extends FLALabeledShape{
    private FLARectangle2D shape; 

    public FLALabeledRectangle(double xMin, double yMin, double xMax, double yMax, int classNumber, String className, FLAStyle style){
        super(new FLALabel(classNumber, className));
        this.shape = new FLARectangle2D(xMin, yMin, xMax, yMax);
        this.setStyle(style);
    }
    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, int classNumber, String className, FLAStyle style){
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), classNumber, className, style);
    }
    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, int classNumber, String className){
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), classNumber, className, new FLAStyle());
    }

    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, int classNumber){
        this(topLeft, bottomRight, classNumber, "Unknown");
    }

    @Override
    public void setStyle(FLAStyle style) {
        this.style = style;
        this.shape.bindComponentStylesTo(style);
    }

    @Override
    public void setShape(FLAShape2D shape) {
        if (shape instanceof FLARectangle2D){
            this.shape = (FLARectangle2D) shape;
        } else {
            throw new IllegalArgumentException("Invalid shape type: " + shape.getClass().getName() + " Expected: FLAPoint2D");
        }
    }

    @Override
    public FLAShape2D getShape() {
        return this.shape;
    }
    
}
