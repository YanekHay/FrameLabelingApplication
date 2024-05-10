package core.styled;

import core.Global;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import utils.Configs;

public class FLAStyle implements IStyled, Cloneable{
    private DoubleProperty strokeWidth = new SimpleDoubleProperty(1);
    private ObjectProperty<Color> strokeColor = new SimpleObjectProperty<>();
    private ObjectProperty<Color> fillColor= new SimpleObjectProperty<>();

    public FLAStyle() {
        this.setStrokeWidth(1);
        this.setStrokeColor(Color.BLACK);
        this.setFillColor(Color.TRANSPARENT);
    }

    public FLAStyle(double strokeWidth, Color strokeColor, Color fillColor) {
        this.setStrokeWidth(strokeWidth);
        this.setStrokeColor(strokeColor);
        this.setFillColor(fillColor);
    }

    public FLAStyle(ObjectProperty<Color> fillColor) {
        Global.worldScaleInverse.addListener((obs, oldVal, newVal)->{
            this.strokeWidth.set(Configs.LINE_THICKNESS * newVal.doubleValue());
        });
        this.fillColor.bind(fillColor);
        this.fillColor.addListener(e->{
            Color darkerColor = this.fillColor.get().darker();
            this.strokeColor.set(Color.rgb((int)(darkerColor.getRed()*255), (int)(darkerColor.getGreen()*255), (int)(darkerColor.getBlue()*255)));
        });
    }

    public FLAStyle(DoubleProperty strokeWidth, ObjectProperty<Color> strokeColor, ObjectProperty<Color> fillColor) {
        this.strokeWidth.bind(strokeWidth);
        this.strokeColor.bind(strokeColor);
        this.fillColor.bind(fillColor);
    }

    @Override
    public void setStrokeWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.strokeWidth.set(width);
    }

    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor.set(color);
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor.set(color);
    }

    public DoubleProperty strokeWidthProperty() {
        return this.strokeWidth;
    }

    public ObjectProperty<Color> strokeColorProperty() {
        return this.strokeColor;
    }

    public ObjectProperty<Color> fillColorProperty() {
        return this.fillColor;
    }

    public void bind(FLAStyle style) {
        this.strokeWidth.bind(style.strokeWidth);
        this.strokeColor.bind(style.strokeColor);
        this.fillColor.bind(style.fillColor);
    }
    
    @Override
    public FLAStyle clone(){
        try {
            FLAStyle copy = (FLAStyle) super.clone();
            copy.strokeWidth = new SimpleDoubleProperty(this.strokeWidth.get());
            copy.strokeColor = new SimpleObjectProperty<>(this.strokeColor.get());
            copy.fillColor = new SimpleObjectProperty<>(this.fillColor.get());
            return copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
