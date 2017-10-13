import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;

public class ConfigMenu{

    //variables
    private Scene scene;
    private BorderPane settingsPanel;
    private TabPane tabContainer;
    private Tab editor, output;
    private RadioButton defaultOutCssSelector, customOutCssSelector, defaultInCssSelector, customInCssSelector;
    private final ToggleGroup outputCssSelectorGroup, inputCssSelectorGroup;
    private GridPane editorTabGrid, outputTabGrid;
    private TextArea outputCssIn, inputCssIn;

    public ConfigMenu() {

        //create main elements for Preferences
        settingsPanel = new BorderPane();
        tabContainer = new TabPane();
        editor = new Tab();
        output = new Tab();

        //set properties for tabs
        editor.setText("Editor");
        output.setText("Output");
        tabContainer.getTabs().add(editor);
        tabContainer.getTabs().add(output);

        //create main elements for output tab
        outputTabGrid = new GridPane();
        outputCssSelectorGroup = new ToggleGroup();
        defaultOutCssSelector = new RadioButton();
        customOutCssSelector = new RadioButton();
        outputCssIn = new TextArea();

        //set properties to elements for output tab
        defaultOutCssSelector.setText("Default CSS");
        customOutCssSelector.setText("Custom CSS");
        defaultOutCssSelector.setToggleGroup(outputCssSelectorGroup);
        customOutCssSelector.setToggleGroup(outputCssSelectorGroup);
        outputTabGrid.add( defaultOutCssSelector, 0, 0 );
        outputTabGrid.add( customOutCssSelector, 0, 1 );
        outputTabGrid.add( outputCssIn, 0, 2);
        outputCssIn.setPrefRowCount(1);
        outputCssIn.setPrefWidth(300);
        outputCssIn.setPromptText("Enter CSS file name");
        output.setContent(outputTabGrid);

        //create main elements for editor tab
        editorTabGrid = new GridPane();
        inputCssSelectorGroup = new ToggleGroup();
        defaultInCssSelector = new RadioButton();
        customInCssSelector = new RadioButton();
        inputCssIn = new TextArea();

        //set properties to elements for editor tab
        defaultInCssSelector.setText("Default CSS");
        customInCssSelector.setText("Custom CSS");
        defaultInCssSelector.setToggleGroup(inputCssSelectorGroup);
        customInCssSelector.setToggleGroup(inputCssSelectorGroup);
        editorTabGrid.add( defaultInCssSelector, 0, 0 );
        editorTabGrid.add( customInCssSelector, 0, 1 );
        editorTabGrid.add( inputCssIn, 0, 2);
        inputCssIn.setPrefRowCount(1);
        inputCssIn.setPrefWidth(300);
        inputCssIn.setPromptText("Enter CSS file name");
        editor.setContent(editorTabGrid);

        //makes pannel for content and adds it to the scene
        settingsPanel.setCenter(tabContainer);
        settingsPanel.setPrefSize(640, 480); //set size of panel
        scene = new Scene(settingsPanel);
    }

    // returns the scene, used to add content to primaryStage
    public Scene getScene() {
		return scene;
	}
}
