package UI.class_menu_item;

import UI.class_menu.ClassMenuController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ClassMenuItem extends AnchorPane {
    private static final String CLASS_MENU_ITEM_ROOT_PATH = "class_menu_item.fxml";
    // ClassMenuItemController controller;
    HBox root;

    public ClassMenuItem() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CLASS_MENU_ITEM_ROOT_PATH));
            root = loader.load();
            this.getChildren().add(root);
            ClassMenuItemController controller = loader.getController();
            controller.setParent(this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
