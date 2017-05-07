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
        newItm = new JMenuItem();
        openItm = new JMenuItem();
        openRecentItm = new JMenuItem();
        saveItm = new JMenuItem();
        saveAsItm = new JMenuItem();
        exitItm = new JMenuItem();
        cutItm = new JMenuItem( new DefaultEditorKit.CutAction() );
        copyItm = new JMenuItem( new DefaultEditorKit.CopyAction() );
        pasteItm = new JMenuItem( new DefaultEditorKit.PasteAction() );
        exportItm = new JMenuItem();

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
        exportItm.setText( "Export" );

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
