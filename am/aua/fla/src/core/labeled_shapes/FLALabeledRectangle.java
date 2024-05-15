package core.labeled_shapes;

import core.shapes.FLARectangle2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import java.util.Objects;
import javafx.scene.paint.Color;

/**
 * Represents a labeled rectangle shape in the Frame Labeling Application.
 * Extends the FLARectangle2D class and implements the ILabeled and IStyled interfaces.
 */
public class FLALabeledRectangle extends FLARectangle2D implements ILabeled, IStyled, Cloneable{
    private FLALabel label;
    
    /**
     * Constructs a FLALabeledRectangle with the specified coordinates and label.
     * 
     * @param xMin The minimum x-coordinate of the rectangle.
     * @param yMin The minimum y-coordinate of the rectangle.
     * @param xMax The maximum x-coordinate of the rectangle.
     * @param yMax The maximum y-coordinate of the rectangle.
     * @param label The label associated with the rectangle.
     */
    public FLALabeledRectangle(double xMin, double yMin, double xMax, double yMax, FLALabel label){
        super(xMin, yMin, xMax, yMax);
        this.setLabel(label);
    }

    /**
     * Constructs a FLALabeledRectangle with the specified top left and bottom right points and label.
     * 
     * @param topLeft The top left point of the rectangle.
     * @param bottomRight The bottom right point of the rectangle.
     * @param label The label associated with the rectangle.
     */
    public FLALabeledRectangle(Point2D topLeft, Point2D bottomRight, FLALabel label){
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), label);
    }
    
    /**
     * Constructs a FLALabeledRectangle with the specified top left point and label.
     * 
     * @param topLeft The top left point of the rectangle.
     * @param label The label associated with the rectangle.
     */
    public FLALabeledRectangle(Point2D topLeft, FLALabel label){
        super(topLeft);
        this.setLabel(label);
    }

    /**
     * Sets the style of the labeled rectangle.
     * 
     * @param style The style to be set.
     */
    public void setStyle(FLAStyle style) {
        this.label.setStyle(style);
        this.bindComponentStylesTo(style);
    }

    /**
     * Sets the label of the rectangle.
     * 
     * @param label The label to be set.
     */
    @Override
    public void setLabel(FLALabel label) {
        this.label = label;
        this.bindComponentStylesTo(this.label.getStyle());
    }

    /**
     * Returns the label associated with the rectangle.
     * 
     * @return The label associated with the rectangle.
     */
    public FLALabel getLabel() {
        return this.label;
    }
   
    /**
     * Returns the class number of the label.
     * 
     * @return The class number of the label.
     */
    @Override
    public int getClassNumber() {
        return this.label.getClassNumber();
    }
    
    /**
     * Returns the class name of the label.
     * 
     * @return The class name of the label.
     */
    @Override
    public String getClassName() {
        return this.label.getClassName();
    }
    
    /**
     * Returns the ID of the label.
     * 
     * @return The ID of the label.
     */
    @Override
    public String getId() {
        return this.label.getId();
    }
    
    /**
     * Sets the class number of the label.
     * 
     * @param classNumber The class number to be set.
     */
    @Override
    public void setClassNumber(int classNumber) {
        this.label.setClassNumber(classNumber);
    }
    
    /**
     * Sets the class name of the label.
     * 
     * @param className The class name to be set.
     */
    @Override
    public void setClassName(String className) {
        this.label.setClassName(className);
    }
    
    /**
     * Returns the property for the class name of the label.
     * 
     * @return The property for the class name of the label.
     */
    @Override
    public StringProperty classNameProperty() {
        return this.label.classNameProperty();
    }
    
    /**
     * Returns the property for the class number of the label.
     * 
     * @return The property for the class number of the label.
     */
    @Override
    public IntegerProperty classNumberProperty() {
        return this.label.classNumberProperty();
    }

    /**
     * Checks if the labeled rectangle is equal to the specified object.
     * 
     * @param that The object to compare with.
     * @return true if the labeled rectangle is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object that){
        if (!(that instanceof FLALabeledRectangle)){
            return false;
        }
        FLALabeledRectangle thatRect = (FLALabeledRectangle) that;
        return super.equals(that) && this.label.equals(thatRect.label);
    }

    /**
     * Returns the hash code value for the labeled rectangle.
     * 
     * @return The hash code value for the labeled rectangle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), label);
    }

    /**
     * Sets the stroke width of the labeled rectangle.
     * 
     * @param width The stroke width to be set.
     */
    @Override
    public void setStrokeWidth(double width) {
        this.label.setStrokeWidth(width);
    }
    
    /**
     * Sets the stroke color of the labeled rectangle.
     * 
     * @param color The stroke color to be set.
     */
    @Override
    public void setStrokeColor(Color color) {
        this.label.setStrokeColor(color);
    }
    
    /**
     * Sets the fill color of the labeled rectangle.
     * 
     * @param color The fill color to be set.
     */
    @Override
    public void setFillColor(Color color) {
        this.label.setFillColor(color);
    }
    
}
