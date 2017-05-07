import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultEditorKit;

public class Menu extends JFrame {

    private static JMenuBar menuBar;
    private static JMenuItem newItm, openItm, openRecentItm, exitItm, cutItm, copyItm, pasteItm, exportItm, saveItm, saveAsItm;
    private static JMenu editMenu, fileMenu, viewMenu, helpMenu;


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

        //adding elements to menu
        menuBar.add( fileMenu );
        menuBar.add( editMenu );
        menuBar.add( viewMenu );
        menuBar.add( helpMenu );

        //items for elements
        newItm = new JMenuItem( "New" );
        openItm = new JMenuItem( "Open..." );
        openRecentItm = new JMenuItem( "Open Recent" );
        saveItm = new JMenuItem( "Save" );
        saveAsItm = new JMenuItem( "Save as" );
        exitItm = new JMenuItem( "Exit" );
        cutItm = new JMenuItem( new DefaultEditorKit.CutAction() );
        copyItm = new JMenuItem( new DefaultEditorKit.CopyAction() );
        pasteItm = new JMenuItem( new DefaultEditorKit.PasteAction() );
        exportItm = new JMenuItem( "Export" );

        //set properties for elements
        copyItm.setText( "Copy" );
        copyItm.setMnemonic( KeyEvent.VK_C );
        pasteItm.setText( "Paste" );
        pasteItm.setMnemonic( KeyEvent.VK_P );
        cutItm.setText( "Cut" );
        cutItm.setMnemonic( KeyEvent.VK_UP );

        //adding items to fileMenu
        fileMenu.add( newItm );
        fileMenu.add( openItm );
        fileMenu.add( openRecentItm );
        fileMenu.add( saveItm );
        fileMenu.add( saveAsItm );
        fileMenu.add( exportItm );
        fileMenu.add( exitItm );

        //adding items to editMenu
        editMenu.add( cutItm );
        editMenu.add( copyItm );
        editMenu.add( pasteItm );

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
        exportItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "exportItm" );
            }
        });
        exitItm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.exit( 0 );
            }
        });


        return menuBar;
    }
}
