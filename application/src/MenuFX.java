import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.PrintWriter;
import java.io.File;
import java.util.List;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MenuFX {

    private static final long serialVersionUID = 1L;

    private TilesScene menuScene;
    private Save save;

    private MenuBar menuBar;
    private MenuItem printItm, newItm, openItm, exitItm,
        cutItm, copyItm, pasteItm, saveItm, saveAsItm, enterFullScreenItm, pdfExpItm,
        htmlExpItm, viewOneTwo, viewTwoOne, viewOneOne, hidePrevPane, hideEditPane,
        preferencesItm;
    private Menu editMenu, fileMenu, viewMenu, helpMenu, exportMenu, openRecentItm;


    public MenuFX(TilesScene currentScene, Save currentSave) {

        menuScene = currentScene;
        save = currentSave;

        //adds menu to menu bar on macOS
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ImageRotator");

        //makes object menuBar
        menuBar = new MenuBar();

        // elements for menu
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        viewMenu = new Menu("View");
        helpMenu = new Menu("Help");
        exportMenu = new Menu("Export");
        openRecentItm = new Menu("Recent documents");

        //items for elements
        newItm = new MenuItem();
        openItm = new MenuItem();
        saveItm = new MenuItem();
        saveAsItm = new MenuItem();
        exitItm = new MenuItem();
        printItm = new MenuItem();
        cutItm = new MenuItem();
        copyItm = new MenuItem();
        pasteItm = new MenuItem();
        enterFullScreenItm = new MenuItem();
        viewOneTwo = new MenuItem();
        viewTwoOne = new MenuItem();
        viewOneOne = new MenuItem();
        hidePrevPane = new MenuItem();
        hideEditPane = new MenuItem();
        pdfExpItm = new MenuItem();
        htmlExpItm = new MenuItem();
        preferencesItm = new MenuItem();

        //set properties for elements
        copyItm.setText( "Copy" );
        copyItm.setMnemonicParsing( true );
        copyItm.setAccelerator( new KeyCodeCombination( KeyCode.C, KeyCombination.META_DOWN ) );
        pasteItm.setText( "Paste" );
        pasteItm.setMnemonicParsing( true );
        pasteItm.setAccelerator( new KeyCodeCombination( KeyCode.V, KeyCombination.META_DOWN ) );
        cutItm.setText( "Cut" );
        cutItm.setMnemonicParsing( true );
        cutItm.setAccelerator( new KeyCodeCombination( KeyCode.X, KeyCombination.META_DOWN ) );
        newItm.setText( "New" );
        newItm.setMnemonicParsing( true );
        newItm.setAccelerator( new KeyCodeCombination( KeyCode.N, KeyCombination.META_DOWN ) );
        openItm.setText( "Open..." );
        openItm.setMnemonicParsing( true );
        openItm.setAccelerator( new KeyCodeCombination( KeyCode.O, KeyCombination.META_DOWN ) );
        exitItm.setText( "Close Window" );
        exitItm.setMnemonicParsing( true );
        exitItm.setAccelerator( new KeyCodeCombination( KeyCode.W, KeyCombination.META_DOWN ) );
        saveItm.setText( "Save" );
        saveItm.setMnemonicParsing( true );
        saveItm.setAccelerator( new KeyCodeCombination( KeyCode.S, KeyCombination.META_DOWN ) );
        saveAsItm.setText( "Save as" );
        saveAsItm.setMnemonicParsing( true );
        saveAsItm.setAccelerator( new KeyCodeCombination( KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.META_DOWN ) );
        printItm.setText( "Print" );
        printItm.setMnemonicParsing( true );
        printItm.setAccelerator( new KeyCodeCombination( KeyCode.P, KeyCombination.META_DOWN ) );
        enterFullScreenItm.setText( "Toggle Full Screen" );
        enterFullScreenItm.setMnemonicParsing( true );
        enterFullScreenItm.setAccelerator( new KeyCodeCombination( KeyCode.F, KeyCombination.CONTROL_DOWN, KeyCombination.META_DOWN ) );
        pdfExpItm.setText( "PDF" );
        pdfExpItm.setMnemonicParsing( true );
        pdfExpItm.setAccelerator( new KeyCodeCombination( KeyCode.P, KeyCombination.ALT_DOWN, KeyCombination.META_DOWN ) );
        htmlExpItm.setText( "HTML" );
        htmlExpItm.setMnemonicParsing( true );
        htmlExpItm.setAccelerator( new KeyCodeCombination( KeyCode.E, KeyCombination.ALT_DOWN , KeyCombination.META_DOWN ) );
        viewOneTwo.setText( "Left 1:2 Right" );
        viewTwoOne.setText( "Left 2:1 Right" );
        viewOneOne.setText( "Left 1:1 Right" );
        viewOneOne.setMnemonicParsing( true );
        viewOneOne.setAccelerator( new KeyCodeCombination( KeyCode.O, KeyCombination.SHIFT_DOWN, KeyCombination.META_DOWN ) );
        hidePrevPane.setText( "Hide Preview Pane" );
        hidePrevPane.setMnemonicParsing( true );
        hidePrevPane.setAccelerator( new KeyCodeCombination( KeyCode.H, KeyCombination.SHIFT_DOWN, KeyCombination.META_DOWN ) );
        hideEditPane.setText( "Hide Editor Pane" );
        hideEditPane.setMnemonicParsing( true );
        hideEditPane.setAccelerator( new KeyCodeCombination( KeyCode.E, KeyCombination.SHIFT_DOWN, KeyCombination.META_DOWN ) );
        preferencesItm.setText( "Preferences" );
        preferencesItm.setMnemonicParsing( true );
        preferencesItm.setAccelerator( new KeyCodeCombination( KeyCode.P, KeyCombination.CONTROL_DOWN ) );

        //adding items to fileMenu
        fileMenu.getItems().add( newItm );
        fileMenu.getItems().add( openItm );
        fileMenu.getItems().add( openRecentItm );
        fileMenu.getItems().add( new SeparatorMenuItem() );
        fileMenu.getItems().add( preferencesItm );
        fileMenu.getItems().add( new SeparatorMenuItem() );
        fileMenu.getItems().add( saveItm );
        fileMenu.getItems().add( saveAsItm );
        fileMenu.getItems().add( exportMenu );
        fileMenu.getItems().add( new SeparatorMenuItem() );
        fileMenu.getItems().add( printItm );
        fileMenu.getItems().add( new SeparatorMenuItem() );
        fileMenu.getItems().add( exitItm );

        //adding items to exportMenu
        exportMenu.getItems().add( pdfExpItm );
        exportMenu.getItems().add( htmlExpItm );

        //adding items to editMenu
        editMenu.getItems().add( cutItm );
        editMenu.getItems().add( copyItm );
        editMenu.getItems().add( pasteItm );

        //adding items to viewMenu
        viewMenu.getItems().add( enterFullScreenItm );
        viewMenu.getItems().add( new SeparatorMenuItem() );
        viewMenu.getItems().add( viewOneTwo );
        viewMenu.getItems().add( viewTwoOne );
        viewMenu.getItems().add( viewOneOne );
        viewMenu.getItems().add( hidePrevPane );
        viewMenu.getItems().add( hideEditPane );

        //ActionListener for menu items

        //cuts text from mainWindow
        cutItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.MainWinCut();
            }
        });
        //copys text from mainWindow
        copyItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.MainWinCopy();
            }
        });
        //pastes text to mainWindow
        pasteItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.MainWinPaste();
            }
        });
        //clears application input area and displays save warning if unsaved changes are present
        newItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (save.changeCheck()) { //if no changes were made to current file or user wants to continue without saving
                    menuScene.clearInputArea();
                }
            }
        });
        //opens a text or md file
        openItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                List<String> fileLines;
                if (save.changeCheck()) { //if no changes were made to current file or user wants to continue without saving
                    fileLines = save.openFileChooserOpen();
                    if (fileLines.isEmpty() == false) {
                        menuScene.setInputArea(fileLines);
                    }
                }
            }
        });
        //saves a file that has previously been saved
        saveItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                save.saveCurrent();
            }
        });
        //saves text in inputArea to a md file
        saveAsItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                save.saveAndExport("MD");
            }
        });
        //exits application
        exitItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (save.changeCheck()) {
                    Platform.exit();
                }
            }
        });
        //toggles application between fullscreen and windowed mode
        enterFullScreenItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage thisStage = ((Stage)menuScene.getScene().getWindow());
                if (thisStage.isMaximized()) {
                    thisStage.setMaximized(false);
                } else {
                    thisStage.setMaximized(true);
                }
            }
        });
        //exports current open item as PDF
        pdfExpItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                save.saveAndExport("PDF");
            }
        });
        //exports current open item as HTML
        htmlExpItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                save.saveAndExport("HTML");

            }
        });
        //sets SplitPaneRatio to 1 to 2
        viewOneTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.setSplitPaneDevider(1.0/3.0);
            }
        });
        //sets SplitPaneRatio to 2 to 1
        viewTwoOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.setSplitPaneDevider(1.0/3.0 * 2);
            }
        });
        //sets SplitPaneRatio to 1 to 1
        viewOneOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.setSplitPaneDevider(0.5);
            }
        });
        //sets SplitPaneRatio to 1 to 0
        hidePrevPane.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.setSplitPaneDevider(1);
            }
        });
        //sets SplitPaneRatio to 0 to 1
        hideEditPane.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.setSplitPaneDevider(0);
            }
        });
        printItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("printItm");
            }
        });
        preferencesItm.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                menuScene.openPreferiences();
            }
        });
        // refreshes openRecentItm menu on click
        fileMenu.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
                        setOpenRecentItm();
                }
            }
        });

        //adding elements to menu
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
    }

    //returns menu
    public MenuBar getMenu() {
        return menuBar;
    }

    // refreshes menu items in openRecentItm
    public void setOpenRecentItm() {
        openRecentItm.getItems().clear(); // clear menu items from openRecentItm (removes duplicates)
        //makes a menu item for every entery in recentSaves
        for (int i = 0; i < save.getRecentSaves().size(); i++) {
            String menuItmPath = save.getRecentSave(i);
            String menuItmText = menuItmPath;
            MenuItem newDoc = new MenuItem();
            if (menuItmPath.length() <= 35) {
                menuItmText = menuItmPath;
            } else if (menuItmPath.length() > 35) {
                menuItmText = "..." + menuItmPath.substring(menuItmPath.length() - 35);
            }
            newDoc.setText(menuItmText);
            openRecentItm.getItems().add(newDoc);

            newDoc.setOnAction( new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    List<String> fileLines;
                    if (save.changeCheck()) { //if no changes were made to current file or user wants to continue without saving
                        fileLines = save.openRecentSave(menuItmPath);
                        if (fileLines.isEmpty() == false) {
                            menuScene.setInputArea(fileLines);
                        }
                    }
                }
            });
        }
    }
}
