import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.awt.GraphicsEnvironment;

public class ConfigFX {

    private static Map<String, String> configItems = new HashMap<String, String>();

    //loads config file on call
    public ConfigFX() {
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
            TilesJavaFX.errorPopup( outError.toString() );
        }
    }
    //makes new config file then calles loadConfigFile() to put settings in hashmap
    public void newConfigFile() {
        try {
            PrintWriter output = new PrintWriter( "config.txt" );
            output.println( "width:640" );
            output.println( "height:480" );
            output.close();
        } catch ( Exception outError ) {
            TilesJavaFX.errorPopup( outError.toString() );
        }
        loadConfigFile(); //loads new config file
    }
    //saves all settings to config file (overwrites old config file)
    public void saveConfig() {
        //saving config file
        try {
            PrintWriter output = new PrintWriter( "config.txt" );
            output.println( "width:" + getWidth() );
            output.println( "height:" + getHeight() );
            output.close();
        } catch ( Exception outError ) {
            TilesJavaFX.errorPopup( outError.toString() );
        }
    }
    public static double getWidth() {
        return Double.parseDouble( configItems.get( "width" ) );
    }
    public static double getHeight() {
        return Double.parseDouble( configItems.get( "height" ) );
    }
    public static void setWidth( String newWidth ) {
        configItems.put( "width", newWidth );
    }
    public static void setHeight( String newHeight ) {
        configItems.put( "height", newHeight );
    }
}
