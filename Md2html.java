import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Md2html {

    private static Map<String, String[]> md2htmlDict = new HashMap<String, String[]>();

    public Md2html() {
        try {
            Scanner in = new Scanner( new File( "md2tags.txt" ) );
            while ( in.hasNextLine() ) {
                String[] line = new StringBuffer( in.nextLine() ).toString().split( ":" );
                String regexIn = line[0].trim();
                String[] tag = new StringBuffer( line[1] ).toString().split( "," );
                md2htmlDict.put( regexIn, tag );
            }
        } catch ( Exception e ) {
            System.out.println( e ); //used for command line
        }
    }

    public String convert( String text ) {
        String[] lines = text.split("\\r?\\n");
        List<String> convertedText = new ArrayList<String>();
        for ( int lineNum = 0; lineNum < lines.length; lineNum++ ) {
            convertedText.add(lines[lineNum]);
        }
        return String.join( "\n", convertedText );
    }
}


//does not work
/*
for (String key : md2htmlDict.keySet()) {
    if ( lines[lineNum].matches( key ) ) {
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(lines[lineNum]);
        String start = matcher.group(1);
        String textObject = matcher.group(2);
        String end = matcher.group(3);
        System.out.println(start);
        System.out.println(textObject);
        System.out.println(end);
    }
}
*/
