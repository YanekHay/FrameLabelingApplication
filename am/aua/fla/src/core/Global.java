package core;
import static utils.CalculationUtil.clamp;

import java.util.ArrayList;
import java.util.List;

import click_handlers.FLAPointClickHandler;
import click_handlers.FLASelectClickHandler;
import controllers.FrameGroupController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import utils.Configs;

public final class Global {
    public static final DoubleProperty worldScaleMultiplier = new SimpleDoubleProperty(1.0);
    public static final DoubleProperty worldScale = new SimpleDoubleProperty(1.0);
    public static final DoubleProperty worldScaleInverse = new SimpleDoubleProperty(1.0);
    public static final FLASelectClickHandler<StackPane, Group> selectClickHandler = new FLASelectClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
    public static final FLAPointClickHandler<StackPane, Group> pointClickHandler = new FLAPointClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
  
    public static void setWorldScale(double worldScale) {
        Global.worldScale.set(clamp(worldScale, Configs.MIN_WORLD_SCALE, Configs.MAX_WORLD_SCALE));
        Global.worldScaleInverse.set(1.0/worldScale);
    }

    public static void setScaleMultiplier(double worldScaleMultiplier) {
        Global.worldScaleMultiplier.set(clamp(Math.pow(Configs.ZOOM_FACTOR, worldScaleMultiplier), Configs.MIN_WORLD_SCALE, Configs.MAX_WORLD_SCALE));
        setWorldScale(getWorldScale() * Global.getWorldScaleMultiplier());
    }

    public static double getWorldScale() {
        return worldScale.get();
    }
    public static double getWorldScaleMultiplier() {
        return worldScaleMultiplier.get();
    }

    public static Point2D pointOnCanvas(double x, double y){
        return FrameGroupController.frameGroup.sceneToLocal(x, y);
    }
}
