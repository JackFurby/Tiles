import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import javafx.stage.Stage;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.Extension;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TilesScene {

    //variables
    private Scene scene;
    private RowConstraints row1;
    private ColumnConstraints col1;
    private GridPane mainPanel;
    private BorderPane backPanel;
    private TextArea inputArea;
    private SplitPane inOutArea;
    private File defaultCssFile;
    private String renderedOut;
    private File outputCssSheet;
    private File selectedCssFilePath;

    private Save save;

    //web elements (used to render html)
    private final WebView outputArea = new WebView();
    private WebEngine webEngine = outputArea.getEngine();
    private List<Extension> extensions = Arrays.asList(TablesExtension.create()); //extentions for commonmark-java

    //private ConfigFX config = new ConfigFX();

    public TilesScene(){

        //create main elements for application
        mainPanel = new GridPane();
        row1 = new RowConstraints();
        col1 = new ColumnConstraints();

        //set properties for main elements
        row1.setPercentHeight(100); //percentage element takes up
        col1.setPercentWidth(100); //percentage element takes up
        mainPanel.getRowConstraints().addAll(row1);
        mainPanel.getColumnConstraints().addAll(col1);

        save = new Save(this, "recentSave.dat");
        save.setFileChange(false);
        save.setPathSet(false);

        // menu for scene
        MenuFX menu = new MenuFX(this, save);
        MenuBar menuBar = menu.getMenu();

        //create elements for application
        inputArea = new TextArea();
        inOutArea = new SplitPane();

        //set properties for elements
        inputArea.setWrapText(true);
        inOutArea.getItems().add(inputArea);
        inOutArea.getItems().add(outputArea);
        mainPanel.add(inOutArea, 0, 0);

        //makes pannel for content and menu and adds it to the scene
        backPanel = new BorderPane();
        backPanel.setCenter(mainPanel);
        backPanel.setTop(menuBar);
        backPanel.setPrefSize(1280, 720); //set size of panel
        scene = new Scene(backPanel);

        //setting ID's to elements
        inputArea.setId("inputArea");
        menuBar.setId("menuBar");
        inOutArea.setId("inOutArea");

        //css for application
        scene.getStylesheets().add("css/application/appMain.css"); //application interface
        String defaultCssPath = "css/output/defaultOut.css";
        setCssPath(defaultCssPath);


        //listener for changes to inputArea
        inputArea.textProperty().addListener((observable, oldValue, newValue) -> {
            Parser parser = Parser.builder().extensions(extensions).build(); //parser for converting md to html
            HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
            Node document = parser.parse(newValue); //gets text from inputArea
            //converts md to html and set text in outputArea to converted text (rendered html), base tag is used for images in local directory
            renderedOut = renderer.render(document);
            webEngine.loadContent("<html><head><base href=\'file:///" + save.getCurrentFilePath() + "\'/></head><body>" + renderedOut + "</body></html>");
            save.setFileChange(true); //lets application know a change has been made

            System.out.println(renderer.render(document)); //used for development
        });
    }

    // returns the scene, used to add content to primaryStage
    public Scene getScene() {
		return scene;
	}
    // returns HTML string of current document
    public String getRenderedOut() {
        return renderedOut;
    }
    // returns file for css sheet used for rendering document
    public File getOutputCssSheet() {
        return outputCssSheet;
    }
    //gets css file path for output document
    public File getCssPath() {
        return selectedCssFilePath;
    }
    //sets a css sheet for the output document
    public void setCssPath(String filePath) {
        selectedCssFilePath = new File(filePath);

        webEngine.setUserStyleSheetLocation(getClass().getResource(filePath).toString()); //outputArea

        try {
            outputCssSheet = new File(filePath);
        } catch (Exception ex) {
            System.out.println(ex);
            //errorPopup("Error while setting CSS sheet. Error: " + ex.getMessage());
        }
    }
    //returns an array of lines in inputArea
    public String[] getInputText() {
        String[] lines = inputArea.getText().split("\\n");
        return lines;
    }
    //sets text in inputarea (will replace whatever is currently there)
    public void setInputArea(List<String> lines) {
        inputArea.clear();
        for (int i=0; i < lines.size(); i++) {
            inputArea.appendText(lines.get(i) + "\n");
        }
        save.setFileChange(false);
    }
    public void setSplitPaneDevider(double ratio) {
        inOutArea.setDividerPositions(ratio);
    }
    public void MainWinCopy() { //                                                           <---- need to add copy to inOutArea
        inputArea.copy();
    }
    public void MainWinPaste() { //                                                          <---- need to add paste to inOutArea
        inputArea.paste();
    }
    public void MainWinCut() { //                                                            <---- need to add cut to inOutArea
        inputArea.cut();
    }
    public void clearInputArea() {
        inputArea.clear();
        save.setFileChange(false);
    }

    //displays error message with a given message
    public void errorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Somthing went wrong");
        alert.setContentText(message);
        alert.showAndWait();
    }

    //opens config window
    public void openPreferiences() {
        ConfigMenu configMenu = new ConfigMenu();
        Stage prefStage = new Stage();
        prefStage.setTitle("Tiles preferences");
        prefStage.setScene(configMenu.getScene());
        prefStage.show();
    }
}
