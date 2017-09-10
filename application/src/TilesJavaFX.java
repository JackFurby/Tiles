import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TilesJavaFX extends Application {

    private TilesMainWindow mainWindow;
    private static ConfigFX config = new ConfigFX();

    @Override
    public void start( Stage primaryStage ) throws Exception {

        mainWindow = new TilesMainWindow();
        primaryStage.setTitle( "Tiles (Markdown)" );
        primaryStage.setScene( mainWindow.getScene() );
        primaryStage.show();

        //on application exit
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                if ( Save.changeCheck() ) {
                    primaryStage.close(); //only close current stage incase multiple stages are open
                }
            }
        });
    }
    //launches application
    public static void main( String[] args ) {
        launch( args );
    }
    //toggles application maximized
    public static void toggleMaximize( Stage currentStage ) {
        if (currentStage.isMaximized()) {
            currentStage.setMaximized(false);
        } else {
            currentStage.setMaximized(true);
        }
    }
    //displays error message with a given message
    public static void errorPopup( String message ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle( "Error" );
        alert.setHeaderText("Somthing went wrong");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
