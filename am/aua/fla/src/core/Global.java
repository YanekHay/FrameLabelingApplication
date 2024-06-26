package core;
import static utils.CalculationUtil.clamp;

import UI.class_menu.ClassMenu;
import UI.layer_item.LayerItem;
import click_handlers.FLAPointClickHandler;
import click_handlers.FLAPolygonClickHandler;
import click_handlers.FLARectangleClickHandler;
import click_handlers.FLASelectClickHandler;
import controllers.FrameGroupController;
import core.labeled_shapes.FLALabel;
import core.labeled_shapes.FLALabeledPoint;
import core.labeled_shapes.FLALabeledRectangle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.Configs;

public final class Global {
    public static final DoubleProperty worldScaleMultiplier = new SimpleDoubleProperty(1.0);
    public static final DoubleProperty worldScale = new SimpleDoubleProperty(1.0);
    public static final DoubleProperty worldScaleInverse = new SimpleDoubleProperty(1.0);
    public static final FLASelectClickHandler<StackPane, Group> selectClickHandler = new FLASelectClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
    public static final FLAPointClickHandler<StackPane, Group> pointClickHandler = new FLAPointClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
    public static final FLARectangleClickHandler<StackPane, Group> rectangleClickHandler = new FLARectangleClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
    public static final FLAPolygonClickHandler<StackPane, Group> polygonClickHandler = new FLAPolygonClickHandler<>(FrameGroupController.parent, FrameGroupController.frameGroup);
    
    private static VBox layerContainer;
    public static final ClassMenu classMenu = new ClassMenu();
    public static final ObservableList<String> layerClasses = FXCollections.observableArrayList();
    public static final ObservableList<FLALabel> labelList = FXCollections.observableArrayList();
    public static final ObservableList<LayerItem<FLALabeledPoint>> pointLayerItemList = FXCollections.observableArrayList();
    public static final ObservableList<LayerItem<FLALabeledRectangle>> rectangleLayerItemList = FXCollections.observableArrayList();
        
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

    public static void setLayerContainer(VBox layerContainer){
        Global.layerContainer = layerContainer;
    }
    public static int getLayerContainerChildCount(){
        return Global.layerContainer.getChildren().size();
    }
    public static void addLayer(Node layerItem){
        Global.layerContainer.getChildren().add(layerItem);
    }
}
