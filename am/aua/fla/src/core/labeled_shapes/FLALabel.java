package core.labeled_shapes;
import java.util.UUID;

import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class FLALabel implements ILabeled, IStyled, Cloneable{
    private IntegerProperty classNumber = new SimpleIntegerProperty();
    private StringProperty className = new SimpleStringProperty();
    private UUID id;
    private FLAStyle style;

    public FLALabel(StringProperty classNumber, StringProperty className, FLAStyle style) {
        classNumber.addListener(e->{
            this.classNumber.set(Integer.parseInt(classNumber.get()));
        });
        this.className = className;
        this.id = UUID.randomUUID();
        this.style = style;
    }
    public FLALabel(IntegerProperty classNumber, StringProperty className, FLAStyle style) {
        this.classNumber = classNumber;
        this.className = className;
        this.id = UUID.randomUUID();
        this.style = style;
    }
    
    public FLALabel(int classNumber, String className, FLAStyle style) {
        this.classNumber.set(classNumber);
        this.className.set(className);
        this.id = UUID.randomUUID();
        this.style = style;
    }

    public FLALabel(int classNumber) {
        this(classNumber, "unknown", null);
    }

    @Override
    public int getClassNumber() {
        return this.classNumber.get();
    }

    @Override
    public String getClassName() {
        return className.get();
    }

    @Override
    public String getId() {
        return id.toString();
    }

    public UUID getUUID() {
        return this.id;
    }
    @Override
    public void setClassNumber(int classNumber) {
        this.classNumber.set(classNumber);
    }

    @Override
    public void setClassName(String className) {
        this.className.set(className);
    }

    @Override
    public IntegerProperty classNumberProperty() {
        return classNumber;
    }

    @Override
    public StringProperty classNameProperty() {
        return className;
    }

    @Override
    public FLALabel clone() {
        return new FLALabel(this.getClassNumber(), this.getClassName(), this.style.clone());
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

    public void setStyle(FLAStyle style) {
        this.style = style;
    }

    public FLAStyle getStyle() {
        return this.style;
    }

    public void bindStyle(FLAStyle style) {
        this.style.bind(style);
    }

    public void bind(FLALabel label) {
        this.classNumber.bind(label.classNumber);
        this.className.bind(label.className);
        this.style.bind(label.style);
    }

    public String toString(){
        return this.getClassName() + " " + this.getClassNumber() + " " + this.getId();
    }
    @Override
    public void setLabel(FLALabel label) {
        this.bind(label);
    }
}
