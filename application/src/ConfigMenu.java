import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

public class ConfigMenu{

    //variables
    private static Scene scene;
    private BorderPane settingsPanel;
    private TabPane tabContainer;
    private Tab editor;

    public ConfigMenu() {

        //create main elements for Preferences
        settingsPanel = new BorderPane();
        tabContainer = new TabPane();
        editor = new Tab();

        //set properties for tabs
        editor.setText("Editor");
        tabContainer.getTabs().add(editor);

        //makes pannel for content and adds it to the scene
        settingsPanel.setCenter(tabContainer);
        settingsPanel.setPrefSize(640, 480); //set size of panel
        scene = new Scene(settingsPanel);
    }

    // returns the scene, used to add content to primaryStage
    public static Scene getScene() {
		return scene;
	}
}
