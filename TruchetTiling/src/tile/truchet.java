/*CS350
 Project #1
 Andrew Pan
 This class creates the window from which the user sees the tiling.
 */
package tile;
import java.io.*;
import java.util.*;

import javax.swing.JFrame;

public class truchet {
	public static void main( String[] args )
	   {
		  int choice=1;
		  int size = 20;
		  Scanner input;
		  try {
			  input=new Scanner(new File("truchet.txt"));
			  choice=input.nextInt();
			  size = input.nextInt();
			  input.close();
		  }
		  catch (IOException e) {
			   System.err.println(e);
			   System.exit(1);
		  }

	      tilepanel panel = new tilepanel( choice, size );
	      
	      JFrame application = new JFrame("Truchet Tiling");

	      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      application.add( panel );
	      application.setSize( 600, 600 );
	      application.setVisible( true );
	   }
}
