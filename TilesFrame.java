import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import static javax.swing.ScrollPaneConstants.*;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class TilesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // Instance variables -- GUI components
    private JPanel editViewPanel, mainPanel;
    private JLabel instructionLabel;
    private static JTextArea inputArea;
    private static JEditorPane outputArea;
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
        outputArea = new JEditorPane();
        inputScroll = new JScrollPane( inputArea );
        outputScroll = new JScrollPane( outputArea );
        combinedArea = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, inputScroll, outputScroll );

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
        outputArea.setMinimumSize( new Dimension( 0,0 ) ); //allows JSplitPane resize with mouse
        outputArea.setContentType( "text/html" );
        HTMLEditorKit kit = new HTMLEditorKit();
        outputArea.setEditorKit(kit);
        inputScroll.setBorder( BorderFactory.createEmptyBorder() );
        inputScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        outputScroll.setBorder( BorderFactory.createEmptyBorder() );
        //outputScroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        combinedArea.setOneTouchExpandable( true );
        combinedArea.setBorder( BorderFactory.createEmptyBorder() );
        combinedArea.setResizeWeight( 0.4 ); //JSplitPane bar in center of window

        //css styling for outputArea
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule( "body {font-family: Helvetica, Arial, Sans-Serif;font-size: 14px;line-height: 1.6;padding: 10px;color: #000000;min-width: 0;overflow-wrap: break-word;word-wrap: break-word;}" );
        //styleSheet.addRule( "p {-webkit-flex-wrap: wrap;}" );
        styleSheet.addRule( "a {color: #393939;text-decoration: none;}" );
        styleSheet.addRule( "h1, h2, h3, h4, h5, h6 {font-weight: bold;margin: 20px 0 10px;}" );
        styleSheet.addRule( "h1 {font-size: 28px;text-decoration: underline;}" );
        styleSheet.addRule( "h2 {font-size: 24px;}" );
        styleSheet.addRule( "h3 {font-size: 18px;}" );
        styleSheet.addRule( "h4 {font-size: 16px;}" );
        styleSheet.addRule( "h5 {font-size: 14px;}" );
        styleSheet.addRule( "h6 {font-size: 12px;}" );

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
            Parser parser = Parser.builder().build(); //parser for converting md to html
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            public void removeUpdate( DocumentEvent e ) {
                Node document = parser.parse( inputArea.getText() ); //gets text from inputArea
                outputArea.setText( renderer.render( document ) ); //converts md to html and set text in outputArea to converted text
            }

            public void insertUpdate( DocumentEvent e ) {
                Node document = parser.parse( inputArea.getText() ); //gets text from inputArea
                outputArea.setText( renderer.render( document ) ); //converts md to html and set text in outputArea to converted text
            }

            public void changedUpdate( DocumentEvent e ) {
                Node document = parser.parse( inputArea.getText() ); //gets text from inputArea
                outputArea.setText( renderer.render( document ) ); //converts md to html and set text in outputArea to converted text
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
