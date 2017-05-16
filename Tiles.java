import javax.swing.JFrame;

//builder for windows
public class Tiles {
    static JFrame frame = new TilesFrame();
    public static void main( String[] args ) {
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        //frame.setSize( 1280,720 );
        //frame.setLocationRelativeTo( null ); //set frame position on screen
    }
}
