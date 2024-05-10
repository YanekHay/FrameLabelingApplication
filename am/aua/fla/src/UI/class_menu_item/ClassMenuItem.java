package UI.class_menu_item;

import UI.class_menu.ClassMenu;
import core.Global;
import core.labeled_shapes.FLALabel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ClassMenuItem extends AnchorPane {
    private static final String CLASS_MENU_ITEM_ROOT_PATH = "class_menu_item.fxml";
    
    private int classNumber;
    private SimpleStringProperty className = new SimpleStringProperty();
    private ObjectProperty<Color> classColor = new SimpleObjectProperty<>(Color.valueOf("#ff000035"));
    private ClassMenuItemController controller;
    private FLALabel label;

    HBox root;

    public ClassMenuItem() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CLASS_MENU_ITEM_ROOT_PATH));
            root = loader.load();
            controller = loader.getController();
            controller.setOwner(this);
            this.setClassNumber(Global.classMenu.getController().getClassCount());
            this.className.set("Class " + classNumber);
            this.getChildren().add(root);
            this.label = controller.getLabel();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        Pane parent = (Pane) this.getParent();
        parent.getChildren().remove(this);
        int q = 0;
        for (Node item : parent.getChildren()) {
            if (item instanceof ClassMenuItem) {
                ((ClassMenuItem)item).setClassNumber(q);
                q++;
            }
        }

    }

    public SimpleStringProperty classNameProperty() {
        return this.className;
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
        this.controller.setClassNumber(classNumber);
    }

    public ObjectProperty<Color> colorProperty() {
        return this.classColor;
    }

    public Color getColor() {
        return classColor.get();
    }

    public void setColor(Color color) {
        this.classColor.set(color);
    }

    public FLALabel getLabel() {
        return label;
    }

}
