package UI.layer_item;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;

import core.Global;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class LayerItemController implements Initializable{
    LayerItem owner;

    @FXML Label lblNumber;
    @FXML Label lblShapeType;
    @FXML ChoiceBox<String> classNameChoiceBox;
    @FXML Button btnRemove;

    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            classNameChoiceBox.setItems(Global.layerClasses);
            classNameChoiceBox.getSelectionModel().select(Global.layerClasses.indexOf(owner.getLabeledShape().getClassName()));
            this.lblNumber.setText(Global.getLayerContainerChildCount() + "");
            this.lblShapeType.setText(owner.getLabeledShape().getClass().getSimpleName());
        });
        this.btnRemove.setOnAction(this::onBtnRemoveAction);
        classNameChoiceBox.setOnAction(this::onClassNameChoiceBoxAction);
    }


    public void onClassNameChoiceBoxAction(ActionEvent e){
        int newVal = classNameChoiceBox.getSelectionModel().getSelectedIndex();
        if (newVal == -1) return;
        this.owner.getLabeledShape().setLabel(Global.labelList.get(newVal));
    }
    
    public void setOwner(LayerItem owner) {
        this.owner = owner;
    }

    private void onBtnRemoveAction(ActionEvent e) {
        owner.remove();
    }

    public void setLayerNumber(int number){
        lblNumber.setText(Integer.toString(number));
    }
}
