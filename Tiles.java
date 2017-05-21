import javax.swing.JFrame;

//builder for windows
public class Tiles {
    static JFrame frame = new TilesFrame();
    public static void main( String[] args ) {
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );

        //set proberties for mainPanel
        Config config = new Config();
        frame.setSize( Config.getWidth(),Config.getHeight() );
        frame.setLocation( Config.getX() - ( Config.getWidth() / 2 ),Config.getY() - ( Config.getHeight() / 2 ) ); //sets window position based on x and y coordinates in config os x and y are center of window
    }
}
