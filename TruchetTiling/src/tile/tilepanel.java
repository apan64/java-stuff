/*CS350
 Project #1
 Andrew Pan
 This class draws the tiles using multiple cases and a random integer, 
 either 0 or 1, to decide the orientation of the tile.
 */
package tile;
import java.awt.*;
import javax.swing.JPanel;

public class tilepanel extends JPanel {
	private int tile, size;
	public tilepanel(int x, int y)
	{
		tile = x;
		size = y;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int x = 0; x < (600/size); x++)
		{
			for(int y = 0; y < (600/size); y++)
			{
				int ran = (int)(Math.random()*2);  //Gives a random integer [0,1]
				switch(tile)
				{
					case 1:
						if(ran == 0)
						{
							g.drawArc(x*size, y*size, size, size, 0, -90);
							g.drawArc(x*size + size,  y*size + size,  size,  size,  90,  90);
						}
						else
						{
							g.drawArc(x*size + size, y*size, size, size, 180, 90);
							g.drawArc(x*size,  y*size + size,  size,  size,  0,  90);
						}
						break;
					case 2:
						if(ran == 0)
						{
							g.drawLine(x*size, y*size, x*size + size/4, y*size + size/2);
							g.drawLine(x*size + size, y*size, x*size + size*3/4, y*size + size/2);
							g.drawLine(x*size, y*size + size, x*size + size/4, y*size + size/2);
							g.drawLine(x*size + size, y * size + size, x*size + size*3/4, y*size + size/2);
							g.drawLine(x*size + size/4, y*size + size/2, x*size + size*3/4, y*size + size/2);
						}
						else
						{
							g.drawLine(x*size, y*size, x*size + size/2, y*size + size/4);
							g.drawLine(x*size + size, y*size, x*size + size/2, y*size + size/4);
							g.drawLine(x*size, y*size + size, x*size + size/2, y*size + size*3/4);
							g.drawLine(x*size + size, y * size + size, x*size + size/2, y*size + size*3/4);
							g.drawLine(x*size + size/2, y*size + size/4, x*size + size/2, y*size + size*3/4);
						}
						break;
					case 3:
						if(ran == 0)
						{
							g.drawLine(x*size, y*size, x*size + size, y*size + size);
						}
						else
						{
							g.drawLine(x*size + size, y*size, x*size, y*size + size);
						}
						break;
					default:
						if(ran == 0)
						{
							g.drawLine(x*size, y*size, x*size + size, y*size + size);
						}
						else
						{
							g.drawLine(x*size + size, y*size, x*size, y*size + size);
						}
						break;
				}
			}
			
		}
	}
}
