import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TilesJavaFX extends Application {

    private TilesMainWindow mainWindow;
    private static Stage stage;

    @Override
    public void start( Stage primaryStage ) throws Exception {

        mainWindow = new TilesMainWindow();
        this.stage = primaryStage;

        primaryStage.setTitle( "Tiles (Markdown)" );
        primaryStage.setScene( mainWindow.getScene() );
        primaryStage.show();

    }
    //launches application
    public static void main( String[] args ) {
        launch( args );
    }
    //toggles application maximized
    public static void toggleMaximize() {

        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}
