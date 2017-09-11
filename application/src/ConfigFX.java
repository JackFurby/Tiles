import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ConfigFX {

    private static Map<String, String> configItems = new HashMap<String, String>();

    //loads config file on call
    public ConfigFX() {
        loadConfigFile();
    }

    //loads settings from config file to a hashmap, if no  file calles newConfigFile()
    public static void loadConfigFile() {
        try {
            Scanner in = new Scanner( new File( "config.txt" ) );
            while ( in.hasNextLine() ) {
                String[] line = new StringBuffer( in.nextLine() ).toString().split( "|||" );
                configItems.put( line[0].trim(), line[1].trim() );
            }
        } catch ( FileNotFoundException fileNameError ) {
            newConfigFile();
        } catch ( Exception outError) {
            TilesJavaFX.errorPopup( outError.toString() );
        }
    }

    //makes new config file then calles loadConfigFile() to put settings in hashmap
    public static void newConfigFile() {
        try {
            PrintWriter output = new PrintWriter( "config.txt" );
            output.close();
        } catch ( Exception outError ) {
            TilesJavaFX.errorPopup( outError.toString() );
        }
        loadConfigFile(); //loads new config file
    }

    //saves all settings to config file (overwrites old config file)
    public static void saveConfig() {
        //saving config file
        try {
            PrintWriter output = new PrintWriter( "config.txt" );
            output.close();
        } catch ( Exception outError ) {
            TilesJavaFX.errorPopup( outError.toString() );
        }
        loadConfigFile(); //loads new config file
    }

    //returns item in configItems
    public static String getConfigItem(String configKey) {
        return configItems.get(configKey);
    }
}
