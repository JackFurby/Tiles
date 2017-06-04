import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import java.io.PrintWriter;
import java.io.File;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.stage.WindowEvent;

import javafx.application.Platform;

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

        //on application exit
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // consume event
                event.consume();
                exitCheck();
            }
        });
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
    //opens fileChooser and saves file
    public static void openFileChooserSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");

        //Set extension filter
        FileChooser.ExtensionFilter mdFileType = new FileChooser.ExtensionFilter("Markdown files (*.md)", "*.md");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(mdFileType, txtFileType, ownFileType);

        //displays save as window
        File file = fileChooser.showSaveDialog(stage);

        //saves file
        if (file != null) {
            try {
                PrintWriter output = new PrintWriter( file ); //sets name and location of file
                String[] outputText = TilesMainWindow.getInputText(); //gets lines of text to save
                for ( int i = 0; i < outputText.length; i++ ) {
                    output.println( outputText[i].toString() ); //saves lines of text
                }
                output.close();
                TilesMainWindow.fileChange = false; //resets fileChange
                TilesMainWindow.currentFilePath = file;
                TilesMainWindow.pathSet = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    //displays warning window alerting about unsaved changes
    public static Boolean changeWarning() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Changes made");
        alert.setHeaderText("Changes have been made since last save");
        alert.setContentText("Do you want to exit without saving?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
    //checks for changes to current document
    public static void exitCheck() {
        if (TilesMainWindow.fileChange) { //if file has been previously saved
            if (changeWarning()){
                Platform.exit();
            }
        } else { //no change has been made to current document
            Platform.exit();
        }
    }
}
