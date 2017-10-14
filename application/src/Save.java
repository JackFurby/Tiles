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
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class Save {

    private Stage stage;
    private List<String> recentSaves = new ArrayList<String>();
    private TilesScene saveScene;
    private Boolean fileChange;
    private Boolean pathSet;
    private File currentFilePath;

    public Save(TilesScene currentScene, String file) {
        saveScene = currentScene;
        setRecentSaves(file);
    }

    public void saveAndExport(String exportType) {
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
            if (fileChooser.getSelectedExtensionFilter().getDescription() == "HTML files (*.html)") {
                try {
                    PrintWriter output = new PrintWriter(file); //sets name and location of file
                    String outputText = saveScene.getRenderedOut(); //gets HTML to save
                    output.println(outputText); //saves HTML
                    Scanner in;
                    in = new Scanner(saveScene.getOutputCssSheet());
                    output.println("<style>"); //start of style
                    while (in.hasNextLine()) {
                        output.println(in.nextLine().toString()); //saves lines of text
                    }
                    output.println("</style>"); //end of style
                    output.close();
                } catch (Exception ex) {
                    saveScene.errorPopup("Error while saving. Error: " + ex.getMessage());
                }
            //export as PDF
        } else if (fileChooser.getSelectedExtensionFilter().getDescription() == "PDF files (*.pdf)") {
                try {
                    String outputText = saveScene.getRenderedOut(); //gets HTML to save
                    OutputStream output = new FileOutputStream(file);
                    Document document = new Document();
                    PdfWriter writer = PdfWriter.getInstance(document, output);
                    document.open();

                    // CSS
                    CSSResolver cssResolver = new StyleAttrCSSResolver();
                    InputStream csspath = Thread.currentThread().getContextClassLoader().getResourceAsStream(saveScene.getCssPath().toString());
                    CssFile cssfile = XMLWorkerHelper.getCSS(csspath);
                    cssResolver.addCss(cssfile);

                    // HTML
                    HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
                    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                    if (getPathSet()) {
                        htmlContext.setResourcesRootPath(getCurrentFilePath().getParent());
                    }

                    // Pipelines
                    Pipeline<?> css = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer)));

                    // XML Worker
                    XMLWorker worker = new XMLWorker(css, true);
                    XMLParser p = new XMLParser(worker);
                    p.parse(new ByteArrayInputStream(outputText.getBytes()));

                    document.close();
                    output.close();
                } catch (Exception e) {
                    saveScene.errorPopup("Error while saving. Error: " + e);
                }
            } else {
                try {
                    PrintWriter output = new PrintWriter(file); //sets name and location of file
                    String[] outputText = saveScene.getInputText(); //gets lines of text to save
                    for (int i = 0; i < outputText.length; i++) {
                        output.println( outputText[i].toString() ); //saves lines of text
                    }
                    output.close();
                    setFileChange(false); //resets fileChange
                    setCurrentFilePath(file);
                    setPathSet(true);
                    setRecentSave(file); // adds document to recentSaves
                } catch (Exception ex) {
                    saveScene.errorPopup("Error while saving. Error: " + ex.getMessage());
                }
            }
        }
    }

    //saves a document that has previously been saved
    public void saveCurrent() {
        if (getPathSet()) { //if file has been previously saved
            try {
                PrintWriter output = new PrintWriter(getCurrentFilePath()); //sets name and location of file
                String[] outputText = saveScene.getInputText(); //gets lines of text to save
                for (int i = 0; i < outputText.length; i++) {
                    output.println(outputText[i].toString()); //saves lines of text
                }
                output.close();
                setFileChange(false); //resets fileChange
                setRecentSave(getCurrentFilePath()); // adds document to recentSaves
            } catch (Exception ex) {
                saveScene.errorPopup("Error while saving. Error: " + ex);
            }
        } else {
            saveAndExport("MD");
        }
    }

    //opens file menu to select a file to open in application
    public List<String> openFileChooserOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        //Set extension filter
        FileChooser.ExtensionFilter mdFileType = new FileChooser.ExtensionFilter("Markdown files (*.md)", "*.md");
        FileChooser.ExtensionFilter txtFileType = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter ownFileType = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(mdFileType, txtFileType, ownFileType);

        //displays open window
        File file = fileChooser.showOpenDialog(stage);

        return fileReader(file);
    }

    // reads txt / md file and returns lines of text
    public List<String> fileReader(File inputFile) {
        List<String> lines = new ArrayList<String>(); //list of lines in file to open
        //opens selected file
        if (inputFile != null) {
            Scanner in;
            try {
                in = new Scanner(inputFile);
                while ( in.hasNextLine() ) {
                    lines.add(in.nextLine().toString());
                }
                setFileChange(false); //resets fileChange
                setCurrentFilePath(inputFile);
                setPathSet(true);
                setRecentSave(inputFile); // adds document to recentSaves
            } catch (Exception error) {
                saveScene.errorPopup("Error while opening file. Error: " + error);
                if (recentSaves.contains(inputFile.toString())) { //removed file from recent saves (file no longer exists)
                    recentSaves.remove(inputFile.toString());
                }
            }
        }
        return lines;
    }

    // returns list recentSaves (list limited to 10 enteries)
    public List<String> getRecentSaves() {
        return recentSaves;
    }

    // returns the file path as a string of a document in recentSaves
    public String getRecentSave(int index) {
        return recentSaves.get(index);
    }

    // opens a recent save
    public List<String> openRecentSave(String filePath) {
        File file = new File(filePath);
        return fileReader(file);
    }

    // sets a new item to recentSaves
    public void setRecentSave(File save) {
        // if save already in list remove old entery and add it into the first slot
        if (recentSaves.contains(save.toString())) {
            recentSaves.remove(save.toString());
            recentSaves.add(0, save.toString());
        } else {
            recentSaves.add(0, save.toString());
        }
        if (recentSaves.size() > 10) {
            recentSaves.remove(-1);
        }
        //saves recentSave to file
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("recentSave.dat"));
            out.writeObject(recentSaves);
            out.close();
        }
        catch (Exception e) {
            saveScene.errorPopup("Error updating recent document:" + e);
        }
    }

    //set recentSave from file
    @SuppressWarnings ("unchecked")
    public void setRecentSaves(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream( fileName ));
            recentSaves = (List<String>)in.readObject();
            in.close();
        }
        catch (FileNotFoundException noFileError) {
            return;
        }
        catch (Exception e) {
            saveScene.errorPopup("Error updating recent document:" + e);
        }
    }

    //displays warning window alerting about unsaved changes
    public Boolean changeWarning() {
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
    public Boolean changeCheck() {
        Boolean checkOption;
        if (getfileChange()) { //if file has been previously saved
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

    // returns the current document file path
    public File getCurrentFilePath() {
        return currentFilePath;
    }

    // Sets current document file path
    public void setCurrentFilePath(File path) {
        currentFilePath = path;
    }

    // returns whether the current document file has been change
    public Boolean getfileChange() {
        return fileChange;
    }

    // Sets fileChange variable
    public void setFileChange(Boolean change) {
        fileChange = change;
    }

    // returns whether the current document has a file path
    public Boolean getPathSet() {
        return pathSet;
    }

    // Sets pathSet variable
    public void setPathSet(Boolean set) {
        pathSet = set;
    }
}
