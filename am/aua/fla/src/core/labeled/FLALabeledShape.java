package core.labeled;

import core.IDrawable;
import core.shapes.FLAShape2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class FLALabeledShape implements IDrawable, ILabeled, Cloneable, IStyled {
    protected FLALabel label;
    protected FLAStyle style;

    protected FLALabeledShape(FLALabel label) {
        this.setLabel(label);
    }

    public void setLabel(FLALabel label) {
        this.label = label;
    }

    public FLALabel getLabel() {
        return label.clone();
    }

    public abstract void setShape(FLAShape2D shape);
    public abstract FLAShape2D getShape();

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
    
    public void drawOnNode(Pane container) {
        this.getShape().drawOnNode(container);
    }
    public void drawOnNode(Group container) {
        this.getShape().drawOnNode(container);
    }

    public abstract void setStyle(FLAStyle style);
    
}
