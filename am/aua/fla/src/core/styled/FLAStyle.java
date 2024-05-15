package core.styled;

import core.Global;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import utils.Configs;

/**
 * Represents the style properties for a frame label in the Frame Labeling Application.
 */
public class FLAStyle implements IStyled, Cloneable{
    private DoubleProperty strokeWidth = new SimpleDoubleProperty(1);
    private ObjectProperty<Color> strokeColor = new SimpleObjectProperty<>();
    private ObjectProperty<Color> fillColor= new SimpleObjectProperty<>();

    /**
     * Constructs a new FLAStyle object with default style properties.
     */
    public FLAStyle() {
        this.setStrokeWidth(1);
        this.setStrokeColor(Color.BLACK);
        this.setFillColor(Color.TRANSPARENT);
    }

    /**
     * Constructs a new FLAStyle object with the specified style properties.
     * @param strokeWidth The width of the stroke.
     * @param strokeColor The color of the stroke.
     * @param fillColor The color of the fill.
     */
    public FLAStyle(double strokeWidth, Color strokeColor, Color fillColor) {
        this.setStrokeWidth(strokeWidth);
        this.setStrokeColor(strokeColor);
        this.setFillColor(fillColor);
    }

    /**
     * Constructs a new FLAStyle object with the specified fill color property.
     * @param fillColor The fill color property.
     */
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

    /**
     * Constructs a new FLAStyle object with the specified style properties.
     * @param strokeWidth The width of the stroke property.
     * @param strokeColor The color of the stroke property.
     * @param fillColor The color of the fill property.
     */
    public FLAStyle(DoubleProperty strokeWidth, ObjectProperty<Color> strokeColor, ObjectProperty<Color> fillColor) {
        this.strokeWidth.bind(strokeWidth);
        this.strokeColor.bind(strokeColor);
        this.fillColor.bind(fillColor);
    }

    /**
     * Sets the width of the stroke.
     * @param width The width of the stroke.
     * @throws IllegalArgumentException if the width is negative.
     */
    @Override
    public void setStrokeWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.strokeWidth.set(width);
    }

    /**
     * Sets the color of the stroke.
     * @param color The color of the stroke.
     */
    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor.set(color);
    }

    /**
     * Sets the color of the fill.
     * @param color The color of the fill.
     */
    @Override
    public void setFillColor(Color color) {
        this.fillColor.set(color);
    }

    /**
     * Returns the property for the stroke width.
     * @return The property for the stroke width.
     */
    public DoubleProperty strokeWidthProperty() {
        return this.strokeWidth;
    }

    /**
     * Returns the property for the stroke color.
     * @return The property for the stroke color.
     */
    public ObjectProperty<Color> strokeColorProperty() {
        return this.strokeColor;
    }

    /**
     * Returns the property for the fill color.
     * @return The property for the fill color.
     */
    public ObjectProperty<Color> fillColorProperty() {
        return this.fillColor;
    }

    /**
     * Binds the style properties to the properties of another FLAStyle object.
     * @param style The FLAStyle object to bind to.
     */
    public void bind(FLAStyle style) {
        this.strokeWidth.bind(style.strokeWidth);
        this.strokeColor.bind(style.strokeColor);
        this.fillColor.bind(style.fillColor);
    }
    
    /**
     * Creates and returns a copy of this FLAStyle object.
     * @return A copy of this FLAStyle object.
     */
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
