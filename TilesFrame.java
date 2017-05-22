import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;

public class TilesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    Md2html convert2html = new Md2html(); //make conversion form MD to HTML available

    // Instance variables -- GUI components
    private JPanel editViewPanel, mainPanel;
    private JLabel instructionLabel;
    private static JTextArea inputArea, outputArea;
    private JScrollPane inputScroll, outputScroll;
    private static JSplitPane combinedArea;

    // Constructor
    public TilesFrame() {
        super();

        // Set up the frame
        setTitle( "Tiles (Markdown)" );

        // menu for window
        Menu menu = new Menu();
        setJMenuBar( menu.Menu() );

        // Set up the editViewPanel and layout manager
        editViewPanel = new JPanel();
        GridLayout editViewgrid = new GridLayout( 0, 1 );
        editViewPanel.setLayout( editViewgrid );

        //set up components
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        inputScroll = new JScrollPane( inputArea );
        outputScroll = new JScrollPane( outputArea );
        combinedArea = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,inputArea, outputArea );

        //set properties for components
        inputArea.setBackground( Color.black );
        inputArea.setForeground( Color.white );
        inputArea.setCaretColor( Color.white );
        inputArea.setMargin( new Insets( 15,15,15,15 ) );
        inputArea.setWrapStyleWord( true );
        inputArea.setLineWrap( true );
        inputArea.setFont( new Font( "", Font.PLAIN, 16 ) );
        inputArea.setMinimumSize( new Dimension( 0,0 ) ); //allows JSplitPane resize with mouse
        outputArea.setEditable( false );
        outputArea.setMargin( new Insets( 15,15,15,15 ) );
        outputArea.setWrapStyleWord( true );
        outputArea.setLineWrap( true );
        outputArea.setFont( new Font( "", Font.PLAIN, 16 ) );
        outputArea.setMinimumSize(new Dimension(0,0)); //allows JSplitPane resize with mouse
        inputScroll.setBorder( BorderFactory.createEmptyBorder() );
        outputScroll.setBorder( BorderFactory.createEmptyBorder() );
        combinedArea.setOneTouchExpandable(true);
        combinedArea.setResizeWeight(0.5); //JSplitPane bar in center of window
        combinedArea.setBorder( BorderFactory.createEmptyBorder() );

        //add components to editViewPanel
        editViewPanel.add( combinedArea );

        // Set up the mainPanel and layout manager
        mainPanel = new JPanel();
        BorderLayout mainLayout = new BorderLayout();
        mainPanel.setLayout( mainLayout );

        //add components to panel
        mainPanel.add( editViewPanel,BorderLayout.CENTER );

        //add document listener to inputArea. This will update outputArea with inputArea text converted to plain text from md
        inputArea.getDocument().addDocumentListener( new DocumentListener() {
            public void removeUpdate( DocumentEvent e ) {
                outputArea.setText( convert2html.convert( inputArea.getText() ) );
            }

            public void insertUpdate( DocumentEvent e ) {
                outputArea.setText( convert2html.convert( inputArea.getText() ) );
            }

            public void changedUpdate( DocumentEvent e ) {
                outputArea.setText( convert2html.convert( inputArea.getText() ) );
            }
        });

        //add and pack mainPanel
        add( mainPanel );
        pack();
    }

    //changes the postion of DividerLocation with the SplitPane combinedArea
    public static void setSplitPaneRatio( double ratio ) {
        combinedArea.setDividerLocation( ratio );
    }
}
