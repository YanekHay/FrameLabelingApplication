package UI.class_menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassMenu extends Stage {
    private static final String CLASS_MENU_ROOT_PATH = "class_menu.fxml";
    
    public ClassMenu() {
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CLASS_MENU_ROOT_PATH));
            Scene root = loader.load();
            ClassMenuController controller = loader.getController();
            controller.setParent(this);
            
            this.setTitle("Add/Remove Classes");
            this.onCloseRequestProperty().set(e -> {
                e.consume();
                this.hide();
            });
            
            this.setScene(root);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.setResizable(false);
    }

    public static int getClassCount() {
        return ClassMenuController.getClassCount();
    }
}
