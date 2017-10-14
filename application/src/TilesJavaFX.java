import javafx.application.Application;
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
