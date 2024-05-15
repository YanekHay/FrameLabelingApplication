package core.labeled_shapes;
import java.util.UUID;

import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

/**
 * Represents a labeled shape in the Frame Labeling Application.
 */
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

    /**
     * Constructs a FLALabel object with the specified class number.
     * The class name is set to "unknown" and the style is set to null.
     * @param classNumber The class number of the labeled shape.
     */
    public FLALabel(int classNumber) {
        this(classNumber, "unknown", null);
    }

    /**
     * Gets the class number of the labeled shape.
     * @return The class number.
     */
    @Override
    public int getClassNumber() {
        return this.classNumber.get();
    }

    /**
     * Gets the class name of the labeled shape.
     * @return The class name.
     */
    @Override
    public String getClassName() {
        return className.get();
    }

    /**
     * Gets the unique identifier of the labeled shape.
     * @return The unique identifier.
     */
    @Override
    public String getId() {
        return id.toString();
    }

    /**
     * Gets the UUID of the labeled shape.
     * @return The UUID.
     */
    public UUID getUUID() {
        return this.id;
    }

    /**
     * Sets the class number of the labeled shape.
     * @param classNumber The class number to set.
     */
    @Override
    public void setClassNumber(int classNumber) {
        this.classNumber.set(classNumber);
    }

    /**
     * Sets the class name of the labeled shape.
     * @param className The class name to set.
     */
    @Override
    public void setClassName(String className) {
        this.className.set(className);
    }

    /**
     * Gets the class number property of the labeled shape.
     * @return The class number property.
     */
    @Override
    public IntegerProperty classNumberProperty() {
        return classNumber;
    }

    /**
     * Gets the class name property of the labeled shape.
     * @return The class name property.
     */
    @Override
    public StringProperty classNameProperty() {
        return className;
    }

    /**
     * Creates a clone of the FLALabel object.
     * @return The cloned FLALabel object.
     */
    @Override
    public FLALabel clone() {
        return new FLALabel(this.getClassNumber(), this.getClassName(), this.style.clone());
    }

    /**
     * Sets the stroke width of the labeled shape.
     * @param width The stroke width to set.
     */
    @Override
    public void setStrokeWidth(double width) {
        this.style.setStrokeWidth(width);
    }

    /**
     * Sets the stroke color of the labeled shape.
     * @param color The stroke color to set.
     */
    @Override
    public void setStrokeColor(Color color) {
        this.style.setStrokeColor(color);
    }

    /**
     * Sets the fill color of the labeled shape.
     * @param color The fill color to set.
     */
    @Override
    public void setFillColor(Color color) {
        this.style.setFillColor(color);
    }

    /**
     * Sets the style of the labeled shape.
     * @param style The style to set.
     */
    public void setStyle(FLAStyle style) {
        this.style = style;
    }

    /**
     * Gets the style of the labeled shape.
     * @return The style.
     */
    public FLAStyle getStyle() {
        return this.style;
    }

    /**
     * Binds the style of the labeled shape to another style.
     * @param style The style to bind.
     */
    public void bindStyle(FLAStyle style) {
        this.style.bind(style);
    }

    /**
     * Binds the properties of the labeled shape to another labeled shape.
     * @param label The labeled shape to bind.
     */
    public void bind(FLALabel label) {
        this.classNumber.bind(label.classNumber);
        this.className.bind(label.className);
        this.style.bind(label.style);
    }

    /**
     * Returns a string representation of the labeled shape.
     * @return The string representation.
     */
    public String toString(){
        return this.getClassName() + " " + this.getClassNumber() + " " + this.getId();
    }

    /**
     * Sets the label of the labeled shape by binding it to another labeled shape.
     * @param label The labeled shape to bind.
     */
    @Override
    public void setLabel(FLALabel label) {
        this.bind(label);
    }
}
