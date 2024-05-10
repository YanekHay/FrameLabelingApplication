package core.labeled_shapes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface ILabeled {
    public int getClassNumber();
    public String getClassName();
    public String getId();
    public void setClassNumber(int classNumber);
    public void setClassName(String className);
    public IntegerProperty classNumberProperty();
    public StringProperty classNameProperty();
}
