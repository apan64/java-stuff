import javax.swing.JFrame;

public class TestBuildingBlocks
{
   public static void main( String[] args )
   { 
      JFrame application = new JFrame( "Building Blocks" );
      
      BuildingPanel buildingPanel = new BuildingPanel();
      application.add(buildingPanel);
  
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.setSize( 800, 540 ); // set frame size
      application.setVisible( true ); // display frame
   }
}