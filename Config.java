import javax.swing.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

public class Config {

    private static Map<String, String> configItems = new HashMap<String, String>();

    public Config() {

        //load config file
        try {
            Scanner in = new Scanner( new File( "config.txt" ) );
            while ( in.hasNextLine() ) {
                String[] line = new StringBuffer( in.nextLine() ).toString().split( ":" );
                configItems.put( line[0].trim(), line[1].trim() );
            }
        } catch ( FileNotFoundException fileNameError ) { //no config file found, make a new one
            try { //items to go into config file
                PrintWriter output = new PrintWriter( "config.txt" );
                output.println( "width:640" );
                output.println( "height:480" );
                Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
                output.println( "x:" + p.x );
                output.println( "y:" + p.y );
                output.close();
            } catch ( Exception outError ) {
                JPanel warningPanel = new JPanel();
                JOptionPane.showMessageDialog( warningPanel,outError,"No config file found!",JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    public void saveConfig() {
        //saving config file
        try {

            PrintWriter output = new PrintWriter( "config.txt" );                                  //<--add items to go in config file
            output.close();
        } catch ( Exception outError ) {
            JPanel warningPanel = new JPanel();
            JOptionPane.showMessageDialog( warningPanel,outError,"Error saving config file",JOptionPane.ERROR_MESSAGE );
        }
    }
}
