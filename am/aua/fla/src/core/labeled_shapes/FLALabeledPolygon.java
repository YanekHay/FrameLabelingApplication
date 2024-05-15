package core.labeled_shapes;

import java.util.ArrayList;
import java.util.Objects;

import controllers.FrameGroupController;
import core.shapes.FLALine2D;
import core.shapes.FLAPoint2D;
import core.shapes.FLAPolygon2D;
import core.styled.FLAStyle;
import core.styled.IStyled;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

/**
 * Represents a labeled polygon in the Frame Labeling Application.
 * Extends the FLAPolygon2D class and implements the ILabeled and IStyled interfaces.
 */
public class FLALabeledPolygon extends FLAPolygon2D implements ILabeled, IStyled, Cloneable {
    private FLALabel label;

    /**
     * Constructs a new FLALabeledPolygon object with no points and a null label.
     */
    public FLALabeledPolygon() {
        super();
        this.setLabel(null);
    }

    /**
     * Constructs a new FLALabeledPolygon object with the given points and label.
     * 
     * @param points The list of points that define the polygon.
     * @param label The label associated with the polygon.
     */
    public FLALabeledPolygon(ArrayList<FLAPoint2D> points, FLALabel label) {
        super(points);
        this.setLabel(label);
    }

    /**
     * Constructs a new FLALabeledPolygon object with no points and the given label.
     * 
     * @param label The label associated with the polygon.
     */
    public FLALabeledPolygon(FLALabel label) {
        super();
        this.setLabel(label);
    }

    /**
     * Sets the style of the labeled polygon.
     * 
     * @param style The style to be set.
     */
    public void setStyle(FLAStyle style) {
        this.label.setStyle(style);
        this.bindComponentStylesTo(style);
    }

    /**
     * Sets the label of the labeled polygon.
     * 
     * @param label The label to be set.
     */
    public void setLabel(FLALabel label) {
        if (label == null)
            return;
        this.label = label;
        this.bindComponentStylesTo(this.label.getStyle());
    }

    /**
     * Closes the polygon by adding a line segment between the last point and the first point.
     * Also updates the style of the labeled polygon.
     */
    @Override
    public void close() {
        if (this.points.size() >= 3) {
            this.lines.add(new FLALine2D(points.get(this.getPointCount() - 1), points.get(0)));
            this.lines.getLast().drawOnNode(FrameGroupController.frameGroup); // TODO: Modify this part to depend on parent container
            this.isClosed = true;
        }
        this.bindComponentStylesTo(this.label.getStyle());
    }

    /**
     * Returns the class number of the labeled polygon.
     * 
     * @return The class number.
     */
    @Override
    public int getClassNumber() {
        return this.label.getClassNumber();
    }

    /**
     * Returns the class name of the labeled polygon.
     * 
     * @return The class name.
     */
    @Override
    public String getClassName() {
        return this.label.getClassName();
    }

    /**
     * Returns the ID of the labeled polygon.
     * 
     * @return The ID.
     */
    @Override
    public String getId() {
        return this.label.getId();
    }

    /**
     * Sets the class number of the labeled polygon.
     * 
     * @param classNumber The class number to be set.
     */
    @Override
    public void setClassNumber(int classNumber) {
        this.label.setClassNumber(classNumber);
    }

    /**
     * Sets the class name of the labeled polygon.
     * 
     * @param className The class name to be set.
     */
    @Override
    public void setClassName(String className) {
        this.label.setClassName(className);
    }

    /**
     * Returns the property for the class name of the labeled polygon.
     * 
     * @return The class name property.
     */
    @Override
    public StringProperty classNameProperty() {
        return this.label.classNameProperty();
    }

    /**
     * Returns the property for the class number of the labeled polygon.
     * 
     * @return The class number property.
     */
    @Override
    public IntegerProperty classNumberProperty() {
        return this.label.classNumberProperty();
    }

    /**
     * Checks if the labeled polygon is equal to another object.
     * 
     * @param that The object to compare with.
     * @return True if the labeled polygon is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof FLALabeledPolygon)) {
            return false;
        }
        FLALabeledPolygon thatPolygon = (FLALabeledPolygon) that;
        return super.equals(that) && this.label.equals(thatPolygon.label);
    }

    /**
     * Returns the hash code of the labeled polygon.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), label);
    }

    /**
     * Sets the stroke width of the labeled polygon.
     * 
     * @param width The stroke width to be set.
     */
    @Override
    public void setStrokeWidth(double width) {
        this.label.setStrokeWidth(width);
    }

    /**
     * Sets the stroke color of the labeled polygon.
     * 
     * @param color The stroke color to be set.
     */
    @Override
    public void setStrokeColor(Color color) {
        this.label.setStrokeColor(color);
    }

    /**
     * Sets the fill color of the labeled polygon.
     * 
     * @param color The fill color to be set.
     */
    @Override
    public void setFillColor(Color color) {
        this.label.setFillColor(color);
    }

    /**
     * Creates a deep copy of the labeled polygon.
     * 
     * @return The cloned labeled polygon.
     */
    @Override
    public FLALabeledPolygon clone() {
        FLALabeledPolygon copy = (FLALabeledPolygon) super.clone();
        copy.setLabel(this.label.clone());
        return copy;
    }
}
