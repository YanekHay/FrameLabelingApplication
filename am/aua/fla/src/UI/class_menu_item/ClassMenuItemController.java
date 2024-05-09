package UI.class_menu_item;

import java.net.URL;
import java.util.ResourceBundle;

import UI.class_menu.ClassMenu;
import UI.class_menu.ClassMenuController;
import core.Global;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public class ClassMenuItemController implements Initializable{

    
    @FXML private TextField classNameNode;
    @FXML private TextField classNumberNode;
    @FXML private ColorPicker colorPickerNode;
    @FXML private ToggleButton btnEdit;
    @FXML private Button btnRemove;

    private ClassMenuItem owner;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            this.classNameNode.textProperty().bindBidirectional(owner.classNameProperty());
            this.classNumberNode.setText(Integer.toString(owner.getClassNumber()));
            this.colorPickerNode.valueProperty().bindBidirectional(owner.colorProperty());
        });
        this.classNameNode.textProperty().addListener(e->{
            // System.out.println(this.classNameNode.getText());
            // System.out.println(this.className.get());
        });

        this.btnRemove.setOnAction(this::onBtnRemoveAction);
        this.btnEdit.setOnAction(this::onBtnEditAction);
    }

    public void setOwner(ClassMenuItem owner) {
        this.owner = owner;
    }


    public void onBtnRemoveAction(ActionEvent e){
        this.owner.remove();
    }

    public void onBtnEditAction(ActionEvent e){
        if (this.btnEdit.isSelected()) {
            this.classNameNode.setEditable(true);
        } else {
            this.classNameNode.setEditable(false);
        }
    }

    public void setClassName(String className) {
        this.owner.classNameProperty().set(className);
    }

    public void setClassNumber(int classNumber) {
        this.classNumberNode.setText(Integer.toString(classNumber));
    }
}
