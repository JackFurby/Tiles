import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebViewBuilder;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import java.io.File;
import java.util.List;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class TilesMainWindow{

    //variables
    private static Scene scene;
    private RowConstraints row1;
    private ColumnConstraints col1;
    private GridPane mainPanel;
    private BorderPane backPanel;
    private static TextArea inputArea;
    private static SplitPane inOutArea;
    private File defaultCssFile;
    private static String renderedOut;
    private static File outputCssSheet;
    private static Boolean fileChange;
    private static Boolean pathSet;
    private static File currentFilePath;

    public TilesMainWindow() {

        //set starting values for variables
        fileChange = false;
        pathSet = false;

        //web elements (used to render html)
        final WebView outputArea = new WebView();
        final WebEngine webEngine = outputArea.getEngine();

        //create main elements for application
        mainPanel = new GridPane();
        row1 = new RowConstraints();
        col1 = new ColumnConstraints();

        //set properties for main elements
        row1.setPercentHeight( 100 ); //percentage element takes up
        col1.setPercentWidth( 100 ); //percentage element takes up
        mainPanel.getRowConstraints().addAll( row1 );
        mainPanel.getColumnConstraints().addAll( col1 );

        // menu for window
        MenuFX menu = new MenuFX();
        menu.MenuFX();
        MenuBar menuBar = menu.getMenu();
        Save.setRecentSaves("recentSave.dat"); //set recentSave

        //create elements for application
        inputArea = new TextArea();
        inOutArea = new SplitPane();

        //set properties for elements
        inputArea.setWrapText( true );
        inOutArea.getItems().add(inputArea);
        inOutArea.getItems().add(outputArea);
        mainPanel.add( inOutArea, 0, 0 );

        //makes pannel for content and menu and adds it to the scene
        backPanel = new BorderPane();
        backPanel.setCenter(mainPanel);
        backPanel.setTop(menuBar);
        backPanel.setPrefSize( 640, 480 ); //set size of panel
        scene = new Scene(backPanel);

        //setting ID's to elements
        inputArea.setId("inputArea");
        menuBar.setId("menuBar");
        inOutArea.setId("inOutArea");

        //css for application
        scene.getStylesheets().add("css/application/appMain.css"); //application interface
        String defaultCssFilePath = "css/output/defaultOut.css";
        webEngine.setUserStyleSheetLocation(getClass().getResource(defaultCssFilePath).toString()); //outputArea

        try {
            outputCssSheet = new File(defaultCssFilePath);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        //listener for changes to inputArea
        inputArea.textProperty().addListener( ( observable, oldValue, newValue ) -> {
            Parser parser = Parser.builder().build(); //parser for converting md to html
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            Node document = parser.parse( newValue ); //gets text from inputArea
            //converts md to html and set text in outputArea to converted text (rendered html), base tag is used for images in local directory
            renderedOut = renderer.render( document );
            webEngine.loadContent("<html><head><base href=\'file:///" + currentFilePath + "\'/></head><body>" + renderedOut + "</body></html>");
            fileChange = true; //lets application know a change has been made

            //System.out.println(renderer.render( document )); //used for development
        });
    }

    // returns the scene, used to add content to primaryStage
    public static Scene getScene() {
		return scene;
	}
    // returns HTML string of current document
    public static String getRenderedOut() {
        return renderedOut;
    }
    // returns file for css sheet used for rendering document
    public static File getOutputCssSheet() {
        return outputCssSheet;
    }
    // returns whether the current document file has been change
    public static Boolean getfileChange() {
        return fileChange;
    }
    // Sets fileChange variable
    public static void setFileChange(Boolean change) {
        fileChange = change;
    }
    // returns whether the current document has a file path
    public static Boolean getPathSet() {
        return pathSet;
    }
    // Sets pathSet variable
    public static void setPathSet(Boolean set) {
        pathSet = set;
    }
    // returns the current document file path
    public static File getCurrentFilePath() {
        return currentFilePath;
    }
    // Sets current document file path
    public static void setCurrentFilePath(File path) {
        currentFilePath = path;
    }
    //returns an array of lines in inputArea
    public static String[] getInputText() {
        String[] lines = inputArea.getText().split("\\n");
        return lines;
    }
    //sets text in inputarea (will replace whatever is currently there)
    public static void setInputArea(  List<String> lines ) {
        inputArea.clear();
        for (int i=0; i < lines.size(); i++) {
            inputArea.appendText(lines.get(i) + "\n");
        }
        setFileChange(false);
    }
    public static void setSplitPaneDevider( double ratio ) {
        inOutArea.setDividerPositions( ratio );
    }
    public static void MainWinCopy() { //                                                           <---- need to add copy to inOutArea
        inputArea.copy();
    }
    public static void MainWinPaste() { //                                                          <---- need to add paste to inOutArea
        inputArea.paste();
    }
    public static void MainWinCut() { //                                                            <---- need to add cut to inOutArea
        inputArea.cut();
    }
    public static void clearInputArea() {
        inputArea.clear();
        setFileChange(false);
    }
}
