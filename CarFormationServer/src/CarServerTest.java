/*CS350
 Project #6
 Andrew Pan & Chipper Atkins
 This class calls and runs the Server
 */
import javax.swing.JFrame;

public class CarServerTest
{
   public static void main( String[] args )
   {
      CarServer application = new CarServer(); // create server
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.runServer(); // run server application
   } // end main
} 