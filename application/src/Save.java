import java.util.Scanner;
import java.io.File;
import javafx.stage.Stage;
import java.io.PrintWriter;
import javafx.stage.FileChooser;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.nio.file.spi.FileTypeDetector;
import java.nio.file.Files;

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

public class Save {

    private static Stage stage;

    public static void saveAndExport(String exportType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");

        //Set extension filter
        FileChooser.ExtensionFilter pdfFileType = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter htmlFileType = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter mdFileType = new FileChooser.ExtensionFilter("Markdown files (*.md)", "*.md");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");

        //sets file type to save / export as
        if (exportType == "HTML") {
            fileChooser.getExtensionFilters().addAll(htmlFileType, pdfFileType, mdFileType, txtFileType, ownFileType);
        } else if (exportType == "PDF") {
            fileChooser.getExtensionFilters().addAll(pdfFileType, htmlFileType, mdFileType, txtFileType, ownFileType);
        } else if (exportType == "MD") {
            fileChooser.getExtensionFilters().addAll(mdFileType, txtFileType, htmlFileType, pdfFileType, ownFileType);
        } else {
            fileChooser.getExtensionFilters().addAll(txtFileType, mdFileType, htmlFileType, pdfFileType, ownFileType);
        }

        //displays save as window
        File file = fileChooser.showSaveDialog(stage);

        //saves file
        if (file != null) {
            //saving as HTML
            if (fileChooser.getSelectedExtensionFilter().getDescription() == "HTML files (*.html)") {         //<---look for a better way to do this
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
                    output.close();
                } catch (Exception ex) {
                    TilesJavaFX.errorPopup("Error while saving. Error: " + ex.getMessage());
                }
            //export as PDF
        } else if (fileChooser.getSelectedExtensionFilter().getDescription() == "PDF files (*.pdf)") {
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
                    TilesJavaFX.errorPopup("Error while saving. Error: " + e);
                }
            } else {
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
                    TilesJavaFX.errorPopup("Error while saving. Error: " + ex.getMessage());
                }
            }
        }
    }

    //saves a document that has previously been saved
    public static void saveCurrent() {
        if (TilesMainWindow.getPathSet()) { //if file has been previously saved
            try {
                PrintWriter output = new PrintWriter( TilesMainWindow.getCurrentFilePath() ); //sets name and location of file
                String[] outputText = TilesMainWindow.getInputText(); //gets lines of text to save
                for ( int i = 0; i < outputText.length; i++ ) {
                    output.println( outputText[i].toString() ); //saves lines of text
                }
                output.close();
                TilesMainWindow.setFileChange(false); //resets fileChange
            } catch ( Exception ex ) {
                TilesJavaFX.errorPopup("Error while saving. Error: " + ex);
            }
        } else {
            saveAndExport("MD");
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
                TilesJavaFX.errorPopup("Error while opening file. Error: " + error);
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