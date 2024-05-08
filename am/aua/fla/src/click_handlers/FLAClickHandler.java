package click_handlers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class FLAClickHandler<P extends Pane, T extends Group>{
    protected P clickArea;
    protected T drawArea;

    protected FLAClickHandler(P clickArea, T drawArea) {
        this.setClickArea(clickArea);
        this.drawArea = drawArea;
    }
    
    public abstract void click(MouseEvent event);
    
    public void setClickArea(P clickArea) {
        this.clickArea = clickArea;
    }

    public abstract void select();
}
