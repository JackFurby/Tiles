import javax.swing.*;

//builder for windows
public class Tiles {
    public static void main( String[] args ) {
        JFrame frame = new TilesFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.setSize( 1280,720 );
        frame.setLocationRelativeTo( null ); //set frame position on screen
    }
}
