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

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

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
    //opens fileChooser and exports file
    public static void openFileChooserExport( String exportType) { //true means save as html, false means PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");

        //Set extension filter
        FileChooser.ExtensionFilter pdfFileType = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter htmlFileType = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        if (exportType == "HTML") { //sets export type on fileChoser open
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
                    String outputText = TilesMainWindow.getRenderedOut(); //gets HTML to save
                    output.println( outputText ); //saves HTML
                    Scanner in;
                    in = new Scanner( TilesMainWindow.getOutputCssSheet() );
                    output.println("<style>"); //start of style
                    while ( in.hasNextLine() ) {
                        output.println( in.nextLine().toString() ); //saves lines of text
                    }
                    output.println("</style>"); //end of style
                    TilesMainWindow.setFileChange(false); //resets fileChange
                    TilesMainWindow.setCurrentFilePath(file);
                    TilesMainWindow.setPathSet(true);
                    output.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else { //export PDF
                try {
                    String outputText = TilesMainWindow.getRenderedOut(); //gets HTML to save
                    OutputStream output = new FileOutputStream(file);
                    Document document = new Document();
                    PdfWriter writer = PdfWriter.getInstance(document, output);
                    document.open();

                    // CSS
                    CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

                    // HTML
                    HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
                    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                    if (TilesMainWindow.getPathSet()) {
                        htmlContext.setResourcesRootPath(TilesMainWindow.getCurrentFilePath().getParent());
                    }


                    // Pipelines
                    PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
                    HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
                    CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

                    // XML Worker
                    XMLWorker worker = new XMLWorker(css, true);
                    XMLParser p = new XMLParser(worker);
                    p.parse(new ByteArrayInputStream(outputText.getBytes()));

                    document.close();
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
                TilesMainWindow.setFileChange(false); //resets fileChange
                TilesMainWindow.setCurrentFilePath(file);
                TilesMainWindow.setPathSet(true);
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
                TilesMainWindow.setFileChange(false); //resets fileChange
                TilesMainWindow.setCurrentFilePath(file);
                TilesMainWindow.setPathSet(true);
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
        if (TilesMainWindow.getfileChange()) { //if file has been previously saved
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
