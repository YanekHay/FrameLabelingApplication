package UI.layer_item;

import core.labeled_shapes.ILabeled;
import core.styled.IStyled;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LayerItem<T extends ILabeled & IStyled> extends AnchorPane{
    private static final String LAYER_ITEM_ROOT_PATH = "class_menu_item.fxml";
    Parent container;
    LayerItemController controller;
    T labeledShape;
    
    public LayerItem(T labeledShape){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LAYER_ITEM_ROOT_PATH));
            HBox root = loader.load();
            
            controller = loader.getController();
            controller.setOwner(this);
            this.labeledShape = labeledShape;
            this.getChildren().add(root);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(){
        VBox parent = (VBox)this.getParent();
        parent.getChildren().remove(this);
        int q=0;
        for(Node item : parent.getChildren()){
            if ((item instanceof LayerItem)){
                ((LayerItem)item).controller.setLayerNumber(q);
                q++;
            }
        }
    }

    public T getLabeledShape(){
        return this.labeledShape;
    }
}
