package core.labeled;

import core.shapes.FLAPoint2D;
import core.styled.FLAStyle;

public class FLALabeledPoint extends FLALabeledShape{

    FLALabeledPoint(double x, double y, int classNumber){
        super(new FLAPoint2D(x, y), new FLALabel(classNumber), null);
    }

    @Override
    public void setStyle(FLAStyle style) {
        this.shape.bindComponentStylesTo(style);
    }
    
}
