/*CS350
 Project #2
 Andrew Pan
 This class creates the frame on which the program is displayed and interacted with.
 */
package Cars;
import javax.swing.JFrame;

public class CarFormation {
	public static void main( String[] args )
	   { 
	      JFrame application = new JFrame( "Car Formation" );
	      
	      CarPanel carPanel = new CarPanel();
	      application.add(carPanel);
	  
	      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      application.setSize( 800, 540 ); // set frame size
	      application.setVisible( true ); // display frame
	   }
}
