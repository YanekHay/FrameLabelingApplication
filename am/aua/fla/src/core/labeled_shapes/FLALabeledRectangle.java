package core.labeled_shapes;

import core.shapes.FLARectangle2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import java.util.Objects;
import javafx.scene.paint.Color;

public class FLALabeledRectangle extends FLARectangle2D implements ILabeled, IStyled, Cloneable{
    private FLALabel label;
    
    public FLALabeledRectangle(double xMin, double yMin, double xMax, double yMax, FLALabel label){
        super(xMin, yMin, xMax, yMax);
        this.setLabel(label);
    }

    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, FLALabel label){
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), label);
    }
    
    public FLALabeledRectangle(Point2D topLeft, FLALabel label){
        super(topLeft);
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

    public FLALabel getLabel() {
        return this.label;
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
        if (!(that instanceof FLALabeledRectangle)){
            return false;
        }
        FLALabeledRectangle thatRect = (FLALabeledRectangle) that;
        return super.equals(that) && this.label.equals(thatRect.label);
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
    
}
