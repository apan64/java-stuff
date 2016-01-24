/*CS350
 Project #6
 Andrew Pan & Chipper Atkins
 This class calls and runs the Client
 */
import javax.swing.JFrame;
public class CarClientTest {
	 public static void main( String[] args )
	   {
	      CarClient application; // declare client application

	      // if no command line args
	      if ( args.length == 0 )
	         application = new CarClient( "127.0.0.1" ); // connect to localhost
	      else
	         application = new CarClient( args[ 0 ] ); // use args to connect

	      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      application.runClient(); // run client application
	   } // end main
}
