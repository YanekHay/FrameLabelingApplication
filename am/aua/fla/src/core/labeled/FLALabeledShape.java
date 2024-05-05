package core.labeled;

import core.shapes.FLAShape2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.scene.paint.Color;

public abstract class FLALabeledShape implements ILabeled, Cloneable, IStyled {
    protected FLAShape2D shape;
    protected FLALabel label;
    protected FLAStyle style;

    public FLALabeledShape(FLAShape2D shape, FLALabel label, FLAStyle style) {
        this.setShape(shape);
        this.setLabel(label);
        this.setStyle(style);
    }

    public void setLabel(FLALabel label) {
        this.label = label;
    }

    public FLALabel getLabel() {
        return label.clone();
    }

    public void setShape(FLAShape2D shape) {
        this.shape = shape;
    }

    public FLAShape2D getShape() {
        return this.shape;
    }

    @Override
    public String getClassName() {
        return this.label.getClassName();
    }

    @Override
    public int getClassNumber() {
        return this.label.getClassNumber();
    }

    @Override
    public String getId() {
        return this.label.getId();
    }

    @Override
    public void setClassName(String className) {
        this.label.setClassName(className);
    }

    @Override
    public void setClassNumber(int classNumber) {
        this.label.setClassNumber(classNumber);
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
    
    public abstract void setStyle(FLAStyle style);
    
}
