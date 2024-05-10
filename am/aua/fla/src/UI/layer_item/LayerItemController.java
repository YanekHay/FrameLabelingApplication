package UI.layer_item;

import java.net.URL;
import java.util.ResourceBundle;

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
            owner.getLabeledShape().classNameProperty().bindBidirectional(this.classNameChoiceBox.valueProperty());
        });
        this.btnRemove.setOnAction(this::onBtnRemoveAction);
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
