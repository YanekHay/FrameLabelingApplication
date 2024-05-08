package core.labeled_shapes;

import java.util.ArrayList;

import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.scene.paint.Color;

public class FLALabeledPolygon extends FLAPolygon2D implements ILabeled, IStyled, Cloneable{
    private FLAStyle style;
    private FLALabel label;

    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, FLALabel label, FLAStyle style){
        super(points);
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
        if (!(that instanceof FLALabeledPolygon)){
            return false;
        }
        FLALabeledPolygon thatPolygon = (FLALabeledPolygon) that;
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
