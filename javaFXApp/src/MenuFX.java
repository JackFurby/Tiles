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

public class MenuFX {

    private static final long serialVersionUID = 1L;

    private static MenuBar menuBar;
    private static MenuItem printItm, newItm, openItm, openRecentItm, exitItm,
        cutItm, copyItm, pasteItm, saveItm, saveAsItm, enterFullScreenItm, pdfExpItm,
        htmlExpItm, viewOneTwo, viewTwoOne, viewOneOne, hidePrevPane, hideEditPane;
    private static Menu editMenu, fileMenu, viewMenu, helpMenu, exportMenu;


    public void MenuFX() {


        //adds menu to menu bar on macOS
        System.setProperty( "apple.laf.useScreenMenuBar", "true" );
        System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "ImageRotator" );

        //makes object menuBar
        menuBar = new MenuBar();

        // elements for menu
        fileMenu = new Menu( "File" );
        editMenu = new Menu( "Edit" );
        viewMenu = new Menu( "View" );
        helpMenu = new Menu( "Help" );
        exportMenu = new Menu( "Export" );

        //items for elements
        newItm = new MenuItem();
        openItm = new MenuItem();
        openRecentItm = new MenuItem();
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
        openRecentItm.setText( "Open Recent" );
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

        //adding items to fileMenu
        fileMenu.getItems().add( newItm );
        fileMenu.getItems().add( openItm );
        fileMenu.getItems().add( openRecentItm );
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
                TilesMainWindow.MainWinCut();
            }
        });
        //copys text from mainWindow
        copyItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.MainWinCopy();
            }
        });
        //pastes text to mainWindow
        pasteItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.MainWinPaste();
            }
        });
        newItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesJavaFX.newWindow();
            }
        });
        //opens a text or md file
        openItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                List<String> fileLines;
                if (TilesJavaFX.changeCheck()) { //if no changes were made to current file or user wants to continue without saving
                    fileLines = TilesJavaFX.openFileChooserOpen();
                    TilesMainWindow.setInputArea(fileLines);
                }
            }
        });
        openRecentItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println( "openRecentItm" );
            }
        });
        //saves a file that has previously been saved
        saveItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (TilesMainWindow.pathSet) { //if file has been previously saved
                    try {
                        PrintWriter output = new PrintWriter( TilesMainWindow.currentFilePath ); //sets name and location of file
                        String[] outputText = TilesMainWindow.getInputText(); //gets lines of text to save
                        for ( int i = 0; i < outputText.length; i++ ) {
                            output.println( outputText[i].toString() ); //saves lines of text
                        }
                        output.close();
                        TilesMainWindow.fileChange = false; //resets fileChange
                    } catch ( Exception ex ) {
                        System.out.println( ex );
                    }
                } else {
                    TilesJavaFX.openFileChooserSaveAs();
                }
            }
        });
        //saves text in inputArea to a md file
        saveAsItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesJavaFX.openFileChooserSaveAs();
            }
        });
        //exits application
        exitItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesJavaFX.changeCheck();
            }
        });
        //toggles application between fullscreen and windowed mode
        enterFullScreenItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesJavaFX.toggleMaximize();
            }
        });
        pdfExpItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println( "pdfExpItm" );
            }
        });
        htmlExpItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println( "htmlExpItm" );
            }
        });
        //sets SplitPaneRatio to 1 to 2
        viewOneTwo.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.setSplitPaneDevider( 1.0/3.0 );
            }
        });
        //sets SplitPaneRatio to 2 to 1
        viewTwoOne.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.setSplitPaneDevider( 1.0/3.0 * 2 );
            }
        });
        //sets SplitPaneRatio to 1 to 1
        viewOneOne.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.setSplitPaneDevider( 0.5 );
            }
        });
        //sets SplitPaneRatio to 1 to 0
        hidePrevPane.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.setSplitPaneDevider( 1 );
            }
        });
        //sets SplitPaneRatio to 0 to 1
        hideEditPane.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TilesMainWindow.setSplitPaneDevider( 0 );
            }
        });
        printItm.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println( "printItm" );
            }
        });

        //adding elements to menu
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
    }
    //returns menu
    public MenuBar getMenu() {
        return menuBar;
    }
}
