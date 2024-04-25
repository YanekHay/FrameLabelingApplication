package core;
import static utils.CalculationUtil.clamp;

import java.util.ArrayList;

import controllers.FrameGroupController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import utils.Configs;

public class Global {
    public static ArrayList<FLAPoint2D> selectedPoints = new ArrayList<FLAPoint2D>();
    public static ArrayList<FLALine2D> selectedLines = new ArrayList<FLALine2D>();

    public static DoubleProperty worldScaleMultiplier = new SimpleDoubleProperty(1.0);
    public static DoubleProperty worldScale = new SimpleDoubleProperty(1.0);
    public static DoubleProperty worldScaleInverse = new SimpleDoubleProperty(1.0);

  
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

}
