import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultEditorKit;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;

    private static JMenuBar menuBar;
    private static JMenuItem printItm, newItm, openItm, openRecentItm, exitItm,
        cutItm, copyItm, pasteItm, saveItm, saveAsItm, enterFullScreenItm, pdfExpItm,
        htmlExpItm, viewOneTwo, viewTwoOne, viewOneOne, hidePrevPane, hideEditPane;
    private static JMenu editMenu, fileMenu, viewMenu, helpMenu, exportMenu;


    public JMenuBar Menu() {


        //adds menu to menu bar on macOS
        System.setProperty( "apple.laf.useScreenMenuBar", "true" );
        System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "ImageRotator" );

        //makes object menuBar
        menuBar = new JMenuBar();

        // elements for menu
        fileMenu = new JMenu( "File" );
        editMenu = new JMenu( "Edit" );
        viewMenu = new JMenu( "View" );
        helpMenu = new JMenu( "Help" );
        exportMenu = new JMenu( "Export" );

        //adding elements to menu
        menuBar.add( fileMenu );
        menuBar.add( editMenu );
        menuBar.add( viewMenu );
        menuBar.add( helpMenu );

        //items for elements
        newItm = new JMenuItem();
        openItm = new JMenuItem();
        openRecentItm = new JMenuItem();
        saveItm = new JMenuItem();
        saveAsItm = new JMenuItem();
        exitItm = new JMenuItem();
        printItm = new JMenuItem();
        cutItm = new JMenuItem( new DefaultEditorKit.CutAction() );
        copyItm = new JMenuItem( new DefaultEditorKit.CopyAction() );
        pasteItm = new JMenuItem( new DefaultEditorKit.PasteAction() );
        enterFullScreenItm = new JMenuItem();
        viewOneTwo = new JMenuItem();
        viewTwoOne = new JMenuItem();
        viewOneOne = new JMenuItem();
        hidePrevPane = new JMenuItem();
        hideEditPane = new JMenuItem();
        pdfExpItm = new JMenuItem();
        htmlExpItm = new JMenuItem();

        //set properties for elements
        copyItm.setText( "Copy" );
        copyItm.setMnemonic( KeyEvent.VK_C );
        copyItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_C, ActionEvent.META_MASK ) );
        pasteItm.setText( "Paste" );
        pasteItm.setMnemonic( KeyEvent.VK_V );
        pasteItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_V, ActionEvent.META_MASK ) );
        cutItm.setText( "Cut" );
        cutItm.setMnemonic( KeyEvent.VK_X );
        cutItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_X, ActionEvent.META_MASK ) );
        newItm.setText( "New" );
        newItm.setMnemonic( KeyEvent.VK_N );
        newItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.META_MASK ) );
        openItm.setText( "Open..." );
        openItm.setMnemonic( KeyEvent.VK_O );
        openItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.META_MASK ) );
        exitItm.setText( "Close Window" );
        exitItm.setMnemonic( KeyEvent.VK_W );
        exitItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_W, ActionEvent.META_MASK ) );
        saveItm.setText( "Save" );
        saveItm.setMnemonic( KeyEvent.VK_S );
        saveItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.META_MASK ) );
        saveAsItm.setText( "Save as" );
        saveAsItm.setMnemonic( KeyEvent.VK_S );
        saveAsItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.SHIFT_MASK + ActionEvent.META_MASK ) );
        openRecentItm.setText( "Open Recent" );
        printItm.setText( "Print" );
        printItm.setMnemonic( KeyEvent.VK_P );
        printItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_P, ActionEvent.META_MASK ) );
        enterFullScreenItm.setText( "Toggle Full Screen" );
        enterFullScreenItm.setMnemonic( KeyEvent.VK_F );
        enterFullScreenItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.META_MASK ) );
        pdfExpItm.setText( "PDF" );
        pdfExpItm.setMnemonic( KeyEvent.VK_P );
        pdfExpItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_P, ActionEvent.ALT_MASK + ActionEvent.META_MASK ) );
        htmlExpItm.setText( "HTML" );
        htmlExpItm.setMnemonic( KeyEvent.VK_E );
        htmlExpItm.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_E, ActionEvent.ALT_MASK + ActionEvent.META_MASK ) );
        viewOneTwo.setText( "Left 1:2 Right" );
        viewTwoOne.setText( "Left 2:1 Right" );
        viewOneOne.setText( "Left 1:1 Right" );
        viewOneOne.setMnemonic( KeyEvent.VK_O );
        viewOneOne.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.SHIFT_MASK + ActionEvent.META_MASK ) );
        hidePrevPane.setText( "Hide Preview Pane" );
        hidePrevPane.setMnemonic( KeyEvent.VK_H );
        hidePrevPane.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_H, ActionEvent.SHIFT_MASK + ActionEvent.META_MASK ) );
        hideEditPane.setText( "Hide Editor Pane" );
        hideEditPane.setMnemonic( KeyEvent.VK_E );
        hideEditPane.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_E, ActionEvent.SHIFT_MASK + ActionEvent.META_MASK ) );

        //adding items to fileMenu
        fileMenu.add( newItm );
        fileMenu.add( openItm );
        fileMenu.add( openRecentItm );
        fileMenu.addSeparator();
        fileMenu.add( saveItm );
        fileMenu.add( saveAsItm );
        fileMenu.add( exportMenu );
        fileMenu.addSeparator();
        fileMenu.add( printItm );
        fileMenu.addSeparator();
        fileMenu.add( exitItm );

        //adding items to exportMenu
        exportMenu.add( pdfExpItm );
        exportMenu.add( htmlExpItm );

        //adding items to editMenu
        editMenu.add( cutItm );
        editMenu.add( copyItm );
        editMenu.add( pasteItm );

        //adding items to viewMenu
        viewMenu.add( enterFullScreenItm );
        viewMenu.addSeparator();
        viewMenu.add( viewOneTwo );
        viewMenu.add( viewTwoOne );
        viewMenu.add( viewOneOne );
        viewMenu.add( hidePrevPane );
        viewMenu.add( hideEditPane );

        //ActionListener for menu items
        newItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "newItm" );
            }
        });
        openItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "openItm" );
            }
        });
        openRecentItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "openRecentItm" );
            }
        });
        saveItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "saveItm" );
            }
        });
        saveAsItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "saveAsItm" );
            }
        });
        //exits application
        exitItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.exit( 0 );
            }
        });
        enterFullScreenItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "FullScreenItm" );
            }
        });
        pdfExpItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "PDF output" );
            }
        });
        htmlExpItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "HTML output" );
            }
        });
        //sets SplitPaneRatio to 1 to 2
        viewOneTwo.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                TilesFrame.setSplitPaneRatio( 1.0/3.0 );
            }
        });
        //sets SplitPaneRatio to 2 to 1
        viewTwoOne.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                TilesFrame.setSplitPaneRatio( 1.0/3.0 * 2 );
            }
        });
        //sets SplitPaneRatio to 1 to 1
        viewOneOne.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                TilesFrame.setSplitPaneRatio( 0.5 );
            }
        });
        //sets SplitPaneRatio to 1 to 0
        hidePrevPane.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                TilesFrame.setSplitPaneRatio( 1 );
            }
        });
        //sets SplitPaneRatio to 0 to 1
        hideEditPane.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                TilesFrame.setSplitPaneRatio( 0 );
            }
        });
        printItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "print" );
            }
        });

        return menuBar;
    }
}
