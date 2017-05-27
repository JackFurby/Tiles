import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TilesMainWindow {

    private static Scene scene;

    public TilesMainWindow(){

        //elements for application layout
        RowConstraints row1;
        ColumnConstraints col1;
        GridPane mainPanel;

        //create layout elements
        mainPanel = new GridPane(); //panel
        row1 = new RowConstraints();
        col1 = new ColumnConstraints();

        //set properties for layout elements
        row1.setPercentHeight( 100 ); //percentage element takes up
        col1.setPercentWidth( 50 ); //percentage element takes up
        mainPanel.getRowConstraints().addAll( row1 );
        mainPanel.getColumnConstraints().addAll( col1, col1 );
		mainPanel.setPrefSize( 640, 480 ); //set size of panel

        //elements for application
        TextArea inputArea, outputArea;

        //create elements for application
        inputArea = new TextArea();
        inputArea.setWrapText( true );
        outputArea = new TextArea();

        //set properties for elements
        mainPanel.add( inputArea, 0, 0 );
        mainPanel.add( outputArea, 1, 0 );

        //add window to scene
		scene = new Scene(mainPanel);

        //listener for changes to inputArea
        inputArea.textProperty().addListener( ( observable, oldValue, newValue ) -> {
            Parser parser = Parser.builder().build(); //parser for converting md to html
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            Node document = parser.parse( newValue ); //gets text from inputArea

            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(renderer.render( document ));

            outputArea.setText( renderer.render( document ) ); //converts md to html and set text in outputArea to converted text
        });
    }

    public static Scene getScene() {
		return scene;
	}
}
