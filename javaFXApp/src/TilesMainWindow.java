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

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;


public class TilesMainWindow{

    private static Scene scene;

    public TilesMainWindow() {

        //web elements (used to render html)
        final WebView outputArea = new WebView();
        final WebEngine webEngine = outputArea.getEngine();

        //elements for application layout
        RowConstraints row1;
        ColumnConstraints col1;
        GridPane mainPanel;
        BorderPane backPanel;

        //create main elements for application
        mainPanel = new GridPane();
        row1 = new RowConstraints();
        col1 = new ColumnConstraints();

        //set properties for main elements
        row1.setPercentHeight( 100 ); //percentage element takes up
        col1.setPercentWidth( 50 ); //percentage element takes up
        mainPanel.getRowConstraints().addAll( row1 );
        mainPanel.getColumnConstraints().addAll( col1, col1 );

        // menu for window
        MenuFX menu = new MenuFX();
        menu.MenuFX();
        MenuBar menuBar = menu.getMenu();

        //elements for application
        TextArea inputArea;
        WebViewBuilder outputContent;

        //create elements for application
        inputArea = new TextArea();
        outputContent = new WebViewBuilder();

        //set properties for elements
        inputArea.setWrapText( true );
        mainPanel.add( inputArea, 0, 0 );
        mainPanel.add( outputArea, 1, 0 );

        //makes pannel for content and menu and adds it to the scene
        backPanel = new BorderPane();
        backPanel.setCenter(mainPanel);
        backPanel.setTop(menuBar);
        backPanel.setPrefSize( 640, 480 ); //set size of panel
        scene = new Scene(backPanel);




        //String css = TilesMainWindow.class.getResource("appMain.css").toExternalForm();             <-------    some of this may be useful for styling
        //scene.getStylesheets().clear();
        //scene.getStylesheets().add(css);
        //scene.getStylesheets().add(this.getClass().getResource("appMain.css").toExternalForm());
        //scene.getStylesheets().add("css/appMain.css");



        //listener for changes to inputArea
        inputArea.textProperty().addListener( ( observable, oldValue, newValue ) -> {
            Parser parser = Parser.builder().build(); //parser for converting md to html
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            Node document = parser.parse( newValue ); //gets text from inputArea
            webEngine.loadContent(renderer.render( document )); //converts md to html and set text in outputArea to converted text (rendered html)
        });
    }

    //returns the scene, used to add content to primaryStage
    public static Scene getScene() {
		return scene;
	}
}
