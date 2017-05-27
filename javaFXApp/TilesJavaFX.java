import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TilesJavaFX extends Application {

    @Override
    public void start( Stage primaryStage ) {
        TilesMainWindow mainWindow = new TilesMainWindow();

		primaryStage.setTitle( "Tiles (Markdown)" );
		primaryStage.setScene( mainWindow.getScene() );
		primaryStage.show();
    }
 public static void main( String[] args ) {
        launch( args );
    }
}
