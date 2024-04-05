import java.nio.file.Path;

import core.FLAPoint2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utils.Configs;

public class Main extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Configs configs = new Configs(Path.of("am/aua/fla/configs/MainConfig.xml"));
        AnchorPane root = FXMLLoader.load(getClass().getResource("UI/root/Main.fxml"));
        // StackPane to hold the image and text
        Scene scene = new Scene(root);
        
        // Set up the stage
        primaryStage.setTitle("Image Coordinate App");
        primaryStage.setScene(scene);
        primaryStage.show();

        FLAPoint2D point = new FLAPoint2D(100, 100, Color.ROYALBLUE, configs.POINT_RADIUS);
        point.drawOnNode(root);
    }

}
