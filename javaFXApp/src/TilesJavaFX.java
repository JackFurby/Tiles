import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import java.io.PrintWriter;
import java.io.File;

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
    //opens fileChooser and saves file
    public static void openFileChooserSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");

        //Set extension filter
        FileChooser.ExtensionFilter mdFileType = new FileChooser.ExtensionFilter("MARKDOWN files (*.md)", "*.md");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(mdFileType, txtFileType);
        //fileChooser.getExtensionFilters().add(txtFileType);

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
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
