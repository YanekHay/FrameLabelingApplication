package UI.class_menu;
import java.net.URL;
import java.util.ResourceBundle;

import UI.class_menu_item.ClassMenuItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ClassMenuController implements Initializable{
    private static int classCount = 0;
    @FXML VBox classListContainer;
    @FXML Button btnAddClass;
    protected ClassMenu parent;

    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            btnAddClass.setOnAction(this::addClass);
        });
        
    }

    public static int getClassCount() {
        return classCount;
    }

    public void setParent(ClassMenu parent) {
        this.parent = parent;
    }

    public void addClass(ActionEvent event) {
        ClassMenuItem classMenuItem = new ClassMenuItem();
        classListContainer.getChildren().add(classMenuItem);
        classCount++;
    }


}