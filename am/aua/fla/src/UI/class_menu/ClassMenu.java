package UI.class_menu;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassMenu extends Stage {
    private static final String CLASS_MENU_ROOT_PATH = "class_menu.fxml";
    private ClassMenuController controller;
    
    public ClassMenu() {
        super();
        FXMLLoader loader = null;
        Scene root = null;
        try{
            loader = new FXMLLoader(getClass().getResource(CLASS_MENU_ROOT_PATH));
            root = loader.load();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if (loader == null) {
            return;
        }

        this.controller = loader.getController();
        this.setTitle("Add/Remove Classes");
        this.onCloseRequestProperty().set(e -> {
            e.consume();
            this.hide();
        });
        System.out.println("CONTROLLER " + this.controller);
        this.setScene(root);
        if (this.controller!=null){
            this.controller.setOwner(this);
        }
        

        this.setResizable(false);
        
       
    }

    public ClassMenuController getController() {
        return controller;
    }
}
