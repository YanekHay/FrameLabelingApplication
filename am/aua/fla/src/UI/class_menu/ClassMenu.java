package UI.class_menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClassMenu extends Stage {
    private final String CLASS_MENU_ROOT_PATH = "class_menu.fxml";
    public ClassMenu() {
        super();
        try{
            Scene root = FXMLLoader.load(getClass().getResource(CLASS_MENU_ROOT_PATH));
            this.setTitle("Add/Remove Classes");
            this.setScene(root);
            this.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
