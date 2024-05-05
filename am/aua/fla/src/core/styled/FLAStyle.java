package core.styled;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class FLAStyle implements IStyled{
    private DoubleProperty strokeWidthProperty = new SimpleDoubleProperty();
    private ObjectProperty<Color> strokeColorProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Color> fillColorProperty = new SimpleObjectProperty<>();

    public FLAStyle() {
        this.setStrokeWidth(1);
        this.setStrokeColor(Color.BLACK);
        this.setFillColor(Color.TRANSPARENT);
    }

    @Override
    public void setStrokeWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.strokeWidthProperty.set(width);
    }

    @Override
    public void setStrokeColor(Color color) {
        this.strokeColorProperty.set(color);
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColorProperty.set(color);
    }

    public DoubleProperty strokeWidthProperty() {
        return this.strokeWidthProperty;
    }

    public ObjectProperty<Color> strokeColorProperty() {
        return this.strokeColorProperty;
    }

    public ObjectProperty<Color> fillColorProperty() {
        return this.fillColorProperty;
    }
}
