package UI.class_menu;
import java.net.URL;
import java.util.ResourceBundle;

import UI.class_menu_item.ClassMenuItem;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ClassMenuController implements Initializable{
    @FXML VBox classListContainer;
    @FXML Button btnAddClass;

    protected ClassMenu owner = null;

    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            btnAddClass.setOnAction(this::addClass);
        });
    }

    public int getClassCount() {
        return this.classListContainer.getChildren().size()-1; // -1 because the last child is the HBox for titles
    }

    public void setOwner(ClassMenu owner) {
        this.owner = owner;
    }

    public void addClass(ActionEvent event) {
        ClassMenuItem classMenuItem = new ClassMenuItem();
        // classMenuItem.setContainer(this.owner);
        classListContainer.getChildren().add(classMenuItem);
    }

}