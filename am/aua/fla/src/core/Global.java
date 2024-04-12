package core;
import static utils.CalculationUtil.clamp;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import utils.Configs;

public class Global {
    public static ArrayList<FLAPoint2D> points = new ArrayList<FLAPoint2D>(5);
    public static ArrayList<FLALine2D> lines = new ArrayList<FLALine2D>(5);
    private static double scaleMultiplier = 1.0;
    private static double worldScale = 1.0;
    
    public static double getWorldScale() {
        return worldScale;
    }
    public static double getWorldScaleInverse() {
        return 1.0/worldScale;
    }
    public static void setWorldScale(double worldScale) {
        Global.worldScale = clamp(worldScale, Configs.MIN_WORLD_SCALE, Configs.MAX_WORLD_SCALE);
    }

    public static double getScaleMultiplier() {
        return scaleMultiplier;
    }
    public static double getScaleMultiplierInverse() {
        return 1.0 / scaleMultiplier;
    }
    public static void setScaleMultiplier(double scaleMultiplier) {
        Global.scaleMultiplier = scaleMultiplier;
    }
}
