import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class Menu extends JFrame implements ActionListener {

    private static JMenuBar menuBar;
    private static JMenuItem newItm, openItm, openRecentItm, exitItm, cutItm, copyItm, pasteItm, exportItm, saveItm, saveAsItm;
    private static JMenu editMenu, fileMenu, viewMenu, helpMenu;

    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


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
        cutItm = new JMenuItem( "Cut" );
        copyItm = new JMenuItem( "Copy" );
        pasteItm = new JMenuItem( "Paste" );
        exportItm = new JMenuItem( "Export" );

        //adding items to fileMenu
        fileMenu.add( newItm );
        fileMenu.add( openItm );
        fileMenu.add( openRecentItm );
        fileMenu.add( saveItm );
        fileMenu.add( saveAsItm );
        fileMenu.add( exportItm );
        fileMenu.add( exitItm );

        //ActionListener for fileMenu items
        newItm.addActionListener( this );
        openItm.addActionListener( this );
        openRecentItm.addActionListener( this );
        saveItm.addActionListener( this );
        saveAsItm.addActionListener( this );
        exportItm.addActionListener( this );
        exitItm.addActionListener( this );

        //adding items to editMenu
        editMenu.add( cutItm );
        editMenu.add( copyItm );
        editMenu.add( pasteItm );

        //ActionListener for editMenu items
        cutItm.addActionListener( this );
        copyItm.addActionListener( this );
        pasteItm.addActionListener( this );


        return menuBar;
    }

    //
    //events for the window
    public void actionPerformed( ActionEvent e ) {

        //panels for use within ActionEvent
        JPanel warningPanel = new JPanel();

        //varables for use within ActionEvent
        String clipBoardText;


        //
        if( e.getSource() == newItm )  {
            System.out.println( "newItm" );
        }
        //
        if( e.getSource() == openItm )  {
            System.out.println( "openItm" );
        }
        //
        if( e.getSource() == openRecentItm )  {
            System.out.println( "openRecentItm" );
        }
        //
        if( e.getSource() == exportItm )  {
            System.out.println( "exportItm" );
        }
        //
        if( e.getSource() == exitItm )  {
            System.out.println( "exitItm" );
        }
        //
        if( e.getSource() == cutItm )  {
            System.out.println( "cutItm" );
        }
        //copys text in input and output area and places it in clipboard
        if( e.getSource() == copyItm )  {
            StringSelection copyText = new StringSelection(TilesFrame.getSelectedText()); //gets selected text from input and output area
            clipboard.setContents(copyText, null); //sets text to clipboard, text is a Transferable object, null is the owner
        }
        //adds text in clipboard to inputArea at current CaretPosition
        if( e.getSource() == pasteItm )  {
            Transferable pasteObj = clipboard.getContents(this); //gets item in clipboard
            try {
                clipBoardText = (String)pasteObj.getTransferData(DataFlavor.stringFlavor); //if pasteObj can be converted to text it is added to clipBoardText
            } catch (Exception expt){
                JOptionPane.showMessageDialog(warningPanel,expt,"Paste error",JOptionPane.ERROR_MESSAGE); // else error is displayed
                clipBoardText = "";
            }
            TilesFrame.insertText(clipBoardText);
        }
    }
}
