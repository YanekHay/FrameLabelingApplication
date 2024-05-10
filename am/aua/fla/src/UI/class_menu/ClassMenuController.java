package UI.class_menu;
import java.net.URL;
import java.util.ResourceBundle;

import UI.class_menu_item.ClassMenuItem;
import core.Global;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ClassMenuController implements Initializable{
    @FXML VBox classListContainer;
    @FXML Button btnAddClass;

    protected ClassMenu owner = null;

    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            btnAddClass.setOnAction(this::addClass);
            classListContainer.getChildren().addListener((ListChangeListener<Node>) e->{
                Global.layerClasses.clear();
                Global.labelList.clear();
                e.getList().forEach(node -> {
                    if (node instanceof ClassMenuItem) {
                        ClassMenuItem classMenuItem = (ClassMenuItem) node;
                        Global.labelList.add(classMenuItem.getLabel());
                        Global.layerClasses.add(classMenuItem.getClassName());
                    }
                });
            });
        });
    }

    public int getClassCount() {
        System.out.println(this.classListContainer.getChildren().size());
        return this.classListContainer.getChildren().size()-1; // -1 because the last child is the HBox for titles

    }

    public void setOwner(ClassMenu owner) {
        this.owner = owner;
    }

    public void addClass(ActionEvent event) {
        ClassMenuItem classMenuItem = new ClassMenuItem();
        classListContainer.getChildren().add(classMenuItem);
        classMenuItem.classNameProperty().addListener(
            (observable, oldValue, newValue) -> {
                Global.layerClasses.clear();
                Global.labelList.clear();
                classListContainer.getChildren().forEach(node -> {
                    if (node instanceof ClassMenuItem) {
                        ClassMenuItem item = (ClassMenuItem) node;
                        Global.layerClasses.add(item.getClassName());
                        Global.labelList.add(item.getLabel());
                    }
                });
            }
        );
        // Global.labelMap.put(classMenuItem.getLabel().getUUID(), classMenuItem.getLabel());
    }



}