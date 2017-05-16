import javax.swing.JFrame;

//builder for windows
public class Tiles {
    static JFrame frame = new TilesFrame();
    public static void main( String[] args ) {
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        
        //set proberties for mainPanel
        Config config = new Config();
        frame.setSize( config.getWidth(),config.getHeight() );
        frame.setLocation( config.getX() - ( config.getWidth() / 2 ),config.getY() - ( config.getHeight() / 2 ) ); //sets window position based on x and y coordinates in config os x and y are center of window
    }
}
