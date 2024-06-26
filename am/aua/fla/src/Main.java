import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("UI/root/Main.fxml"));
        // StackPane to hold the image and text
        Scene scene = new Scene(root);
        
        // Set up the stage
        primaryStage.setTitle("Image Coordinate App");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set up the image
    }

}
