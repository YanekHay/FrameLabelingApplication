package core.styled;
import javafx.scene.paint.Color;

/**
 * The IStyled interface represents an object that can be styled with stroke width, stroke color, and fill color.
 */
public interface IStyled {
    /**
     * Sets the stroke width of the object.
     * 
     * @param width the width of the stroke
     */
    public void setStrokeWidth(double width);

    /**
     * Sets the stroke color of the object.
     * 
     * @param color the color of the stroke
     */
    public void setStrokeColor(Color color);

    /**
     * Sets the fill color of the object.
     * 
     * @param color the color of the fill
     */
    public void setFillColor(Color color);
}
