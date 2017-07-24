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
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TilesJavaFX extends Application {

    private TilesMainWindow mainWindow;
    private static Stage stage;
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
                if ( changeCheck() ) {
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
    //opens fileChooser and saves file
    public static void openFileChooserExport( Boolean exportType) { //true means save as html
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");

        //Set extension filter
        FileChooser.ExtensionFilter pdfFileType = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter htmlFileType = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        if (exportType) { //sets export type on fileChoser open
            fileChooser.getExtensionFilters().addAll(htmlFileType, pdfFileType, txtFileType, ownFileType);
        } else {
            fileChooser.getExtensionFilters().addAll(pdfFileType, htmlFileType, txtFileType, ownFileType);
        }
        //displays save as window
        File file = fileChooser.showSaveDialog(stage);

        //saves file
        if (file != null) {
            if (fileChooser.getSelectedExtensionFilter().getDescription() == "HTML files (*.html)") { //saving as HTML         <---look for a better way to do this
                try {
                    PrintWriter output = new PrintWriter( file ); //sets name and location of file
                    String outputText = TilesMainWindow.renderedOut; //gets HTML to save
                    output.println( outputText ); //saves HTML
                    Scanner in;
                    in = new Scanner( TilesMainWindow.outputCssSheet );
                    output.println("<style>"); //start of style
                    while ( in.hasNextLine() ) {
                        output.println( in.nextLine().toString() ); //saves lines of text
                    }
                    output.println("</style>"); //end of style
                    TilesMainWindow.fileChange = false; //resets fileChange
                    TilesMainWindow.currentFilePath = file;
                    TilesMainWindow.pathSet = true;
                    output.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else {                                                              //<------ add exporting PDF here
            }
        }
    }
    //opens fileChooser and exports file
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
    //opens file menu to select a file to open in application
    public static List<String> openFileChooserOpen() {
        List<String> lines = new ArrayList<String>(); //list of lines in file to open
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        //Set extension filter
        FileChooser.ExtensionFilter mdFileType = new FileChooser.ExtensionFilter("Markdown files (*.md)", "*.md");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(mdFileType, txtFileType, ownFileType);

        //displays open window
        File file = fileChooser.showOpenDialog(stage);

        //opens selected file
        if (file != null) {
            Scanner in;
            try {
                in = new Scanner( file );
                while ( in.hasNextLine() ) {
                    lines.add(in.nextLine().toString());
                }
                TilesMainWindow.fileChange = false; //resets fileChange
                TilesMainWindow.currentFilePath = file;
                TilesMainWindow.pathSet = true;
            } catch ( Exception error ) {
                System.out.println( error );
            }
        }
        return lines;
    }
    //displays warning window alerting about unsaved changes
    public static Boolean changeWarning() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Changes made");
        alert.setHeaderText("Changes have been made since last save");
        alert.setContentText("Do you want to continue without saving?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
    //displays error message with a given message
    public static void errorPopup( String message ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle( "Error" );
        alert.setHeaderText("Somthing went wrong");
        alert.setContentText("Look up" + message + "for more information");
        alert.showAndWait();
    }
    //checks for changes to current document
    public static Boolean changeCheck() {
        Boolean checkOption;
        if (TilesMainWindow.fileChange) { //if file has been previously saved
            if (changeWarning()) {
                checkOption = true;
            } else {
                checkOption = false;
            }
        } else { //no change has been made to current document
            checkOption = true;
        }
        return checkOption;
    }
}
