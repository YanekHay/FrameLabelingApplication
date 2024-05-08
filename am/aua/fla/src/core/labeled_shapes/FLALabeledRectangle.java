package core.labeled_shapes;

import core.shapes.FLARectangle2D;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.geometry.Point2D;

import javafx.scene.paint.Color;

public class FLALabeledRectangle extends FLARectangle2D implements ILabeled, IStyled, Cloneable{
    private FLAStyle style;
    private FLALabel label;
    
    public FLALabeledRectangle(double xMin, double yMin, double xMax, double yMax, FLALabel label, FLAStyle style){
        super(xMin, yMin, xMax, yMax);
        this.setStyle(style);
        this.setLabel(label);
    }

    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, FLALabel label, FLAStyle style){
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), label, style);
    }
    
    public FLALabeledRectangle(Point2D topLeft, FLALabel label, FLAStyle style){
        super(topLeft);
        this.setStyle(style);
        this.setLabel(label);
    }

    public void setStyle(FLAStyle style) {
        this.style = style;
        this.bindComponentStylesTo(style);
    }

    public void setLabel(FLALabel label) {
        this.label = label;
    }

    public int getClassNumber() {
        return this.label.getClassNumber();
    }
    public String getClassName() {
        return this.label.getClassName();
    }
    public String getId() {
        return this.label.getId();
    }
    public FLALabel getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object that){
        if (!(that instanceof FLALabeledRectangle)){
            return false;
        }
        FLALabeledRectangle thatRect = (FLALabeledRectangle) that;
        return super.equals(that) && this.label.equals(thatRect.label);
    }

    @Override
    public void setStrokeWidth(double width) {
        this.style.setStrokeWidth(width);
    }
    @Override
    public void setStrokeColor(Color color) {
        this.style.setStrokeColor(color);
    }
    @Override
    public void setFillColor(Color color) {
        this.style.setFillColor(color);
    }
    
}
