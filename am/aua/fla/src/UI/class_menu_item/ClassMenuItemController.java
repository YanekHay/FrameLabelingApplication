package UI.class_menu_item;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClassMenuItemController implements Initializable{
    private StringProperty className = new SimpleStringProperty("");
    private IntegerProperty classNumber = new SimpleIntegerProperty(0);
    private StringProperty classNumberStringProperty = new SimpleStringProperty("0");
    private StringProperty classColor = new SimpleStringProperty("#000000");
    
    @FXML private TextField classNameNode;
    @FXML private Label classNumberNode;
    @FXML private ColorPicker colorPickerNode;
    private ClassMenuItem parent;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            this.classNameNode.textProperty().bindBidirectional(className);
            this.classNumberStringProperty.bind(this.classNumber.asString());
            this.classNumberNode.textProperty().bindBidirectional(classNumberStringProperty);
            this.colorPickerNode.valueProperty().addListener((observable, oldValue, newValue) -> {
                this.classColor.set(newValue.toString());
            });
        });
        this.classNameNode.textProperty().addListener(e->{
            System.out.println(this.classNameNode.getText());
            System.out.println(this.className.get());
        });
    }

    public void setParent(ClassMenuItem parent) {
        this.parent = parent;
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public int getClassNumber() {
        return classNumber.get();
    }

    public void setClassNumber(int classNumber) {
        this.classNumber.set(classNumber);
    }
}
