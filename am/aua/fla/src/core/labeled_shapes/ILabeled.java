package core.labeled_shapes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface ILabeled {
    /**
     * Returns the class number associated with the labeled shape.
     * 
     * @return the class number
     */
    public int getClassNumber();
    
    /**
     * Returns the class name associated with the labeled shape.
     * 
     * @return the class name
     */
    public String getClassName();
    
    /**
     * Returns the ID of the labeled shape.
     * 
     * @return the ID
     */
    public String getId();
    
    /**
     * Sets the class number for the labeled shape.
     * 
     * @param classNumber the class number to set
     */
    public void setClassNumber(int classNumber);
    
    /**
     * Sets the class name for the labeled shape.
     * 
     * @param className the class name to set
     */
    public void setClassName(String className);
    
    /**
     * Sets the label for the labeled shape.
     * 
     * @param label the label to set
     */
    public void setLabel(FLALabel label);
    
    /**
     * Returns the class number property of the labeled shape.
     * 
     * @return the class number property
     */
    public IntegerProperty classNumberProperty();
    
    /**
     * Returns the class name property of the labeled shape.
     * 
     * @return the class name property
     */
    public StringProperty classNameProperty();
}
