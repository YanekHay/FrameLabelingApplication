package click_handlers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class FLAClickHandler<P extends Pane, T extends Group>{
    protected P mouseArea;
    protected T drawArea;

    protected FLAClickHandler(P mouseArea, T drawArea) {
        this.setMouseArea(mouseArea);
        this.drawArea = drawArea;
    }
    
    public abstract void mousePress(MouseEvent event);
    
    public void setMouseArea(P mouseArea) {
        this.mouseArea = mouseArea;
    }

    public abstract void select();
}
