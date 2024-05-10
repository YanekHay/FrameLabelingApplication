package UI.class_menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
        this.setScene(root);
        if (this.controller!=null){
            this.controller.setOwner(this);
        }
        
        this.setResizable(false);      
    }

    public ClassMenuController getController() {
        return controller;
    }

    public VBox getClassListContainer() {
        return this.controller.classListContainer;
    }
    
}
