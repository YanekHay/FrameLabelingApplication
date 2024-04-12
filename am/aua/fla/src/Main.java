import java.nio.file.Path;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
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

        // Set up the image
    }

}
