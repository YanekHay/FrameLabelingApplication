package core.labeled_shapes;

import core.shapes.FLAPoint2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class FLALabeledPoint extends FLAPoint2D implements ILabeled, IStyled, Cloneable{
    private FLALabel label;

    public FLALabeledPoint(double x, double y, FLALabel label){
        super(x, y);
        this.setLabel(label);
    }

    public void setStyle(FLAStyle style) {
        this.label.setStyle(style);
        this.bindComponentStylesTo(style);
    }

    @Override
    public void setLabel(FLALabel label) {
        this.label = label;
        this.bindComponentStylesTo(this.label.getStyle());
    }
    
    @Override
    public int getClassNumber() {
        return this.label.getClassNumber();
    }
    @Override
    public String getClassName() {
        return this.label.getClassName();
    }
    @Override
    public String getId() {
        return this.label.getId();
    }
    
    @Override
    public void setClassNumber(int classNumber) {
        this.label.setClassNumber(classNumber);
    }
    @Override
    public void setClassName(String className) {
        this.label.setClassName(className);
    }
    @Override
    public StringProperty classNameProperty() {
        return this.label.classNameProperty();
    }
    @Override
    public IntegerProperty classNumberProperty() {
        return this.label.classNumberProperty();
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
        this.label.setStrokeWidth(width);
    }
    @Override
    public void setStrokeColor(Color color) {
        this.label.setStrokeColor(color);
    }
    @Override
    public void setFillColor(Color color) {
        this.label.setFillColor(color);
    }



}
