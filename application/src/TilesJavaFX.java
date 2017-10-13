import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TilesJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        TilesScene mainScene = new TilesScene();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(mainScene.getScene());
        primaryStage.show();

    }
 public static void main(String[] args) {
        launch(args);
    }
}
