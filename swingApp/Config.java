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

    //loads config file on call
    public Config() {
        loadConfigFile();
    }

    //loads settings from config file to a hashmap, if no  file calles newConfigFile()
    public void loadConfigFile() {
        try {
            Scanner in = new Scanner( new File( "config.txt" ) );
            while ( in.hasNextLine() ) {
                String[] line = new StringBuffer( in.nextLine() ).toString().split( ":" );
                configItems.put( line[0].trim(), line[1].trim() );
            }
        } catch ( FileNotFoundException fileNameError ) {
            newConfigFile();
        } catch ( Exception outError) {
            JPanel warningPanel = new JPanel();
            JOptionPane.showMessageDialog( warningPanel,outError,"No config file found!",JOptionPane.ERROR_MESSAGE );
        }
    }

    //makes new config file then calles loadConfigFile() to put settings in hashmap
    public void newConfigFile() {
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
            JOptionPane.showMessageDialog( warningPanel,outError,"Error making config file",JOptionPane.ERROR_MESSAGE );
        }
        loadConfigFile(); //loads new config file
    }

    //saves allsettings to config file (overwrites old config file)
    public void saveConfig() {
        //saving config file
        try {
            PrintWriter output = new PrintWriter( "config.txt" );
            output.println( "width:640" );                                      //<--add items to go in config file
            output.println( "height:480" );
            Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            output.println( "x:" + p.x );
            output.println( "y:" + p.y );
            output.close();
        } catch ( Exception outError ) {
            JPanel warningPanel = new JPanel();
            JOptionPane.showMessageDialog( warningPanel,outError,"Error saving config file",JOptionPane.ERROR_MESSAGE );
        }
    }
    public static int getWidth() {
        return Integer.parseInt( configItems.get( "width" ) );
    }
    public static int getHeight() {
        return Integer.parseInt( configItems.get( "height" ) );
    }
    public static int getX() {
        return Integer.parseInt( configItems.get( "x" ) );
    }
    public static int getY() {
        return Integer.parseInt( configItems.get( "y" ) );
    }
}