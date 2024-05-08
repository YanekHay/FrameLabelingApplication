package core.labeled_shapes;

import core.shapes.FLAPoint2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.scene.paint.Color;

public class FLALabeledPoint extends FLAPoint2D implements ILabeled, IStyled, Cloneable{
    private FLALabel label;
    private FLAStyle style;

    public FLALabeledPoint(double x, double y, FLALabel label, FLAStyle style){
        super(x, y);
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
        if (!(that instanceof FLALabeledPoint)){
            return false;
        }
        FLALabeledPoint thatPolygon = (FLALabeledPoint) that;
        return super.equals(that) && this.label.equals(thatPolygon.label);
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
