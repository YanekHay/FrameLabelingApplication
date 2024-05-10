package core.labeled_shapes;

import java.util.ArrayList;
import java.util.Objects;

import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class FLALabeledPolygon extends FLAPolygon2D implements ILabeled, IStyled, Cloneable{
    private FLALabel label;

    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, FLALabel label, FLAStyle style){
        super(points);
        this.setStyle(style);
        this.setLabel(label);
    }

    public FLALabeledPolygon(FLALabel label, FLAStyle style){
        super();
        this.setStyle(style);
        this.setLabel(label);
    }

    public void setStyle(FLAStyle style) {
        this.label.setStyle(style);
        this.bindComponentStylesTo(style);
    }

    public void setLabel(FLALabel label) {
        this.label = label;
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

    @Override
    public boolean equals(Object that){
        if (!(that instanceof FLALabeledPolygon)){
            return false;
        }
        FLALabeledPolygon thatPolygon = (FLALabeledPolygon) that;
        return super.equals(that) && this.label.equals(thatPolygon.label);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), label);
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
    
    @Override
    public FLALabeledPolygon clone() {
        FLALabeledPolygon copy = (FLALabeledPolygon) super.clone();
        copy.setLabel(this.label.clone());
        return copy;
    }
}
