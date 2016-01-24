/*CS350
 Project #6
 Andrew Pan
 This class is for the cars that are drawn and interacted with in the panel.
 */

import java.awt.Color;
import java.awt.Graphics;

public class MyCar {
	private boolean source;
	private int  color, x, y, width, height, wheel;
	public MyCar(MyCar c)
	{
		source = false;
		color = c.color;
		x = c.x;
		y = c.y;
		width = c.width;
		height = c.height;
		wheel = c.wheel;
	}
	public MyCar(int c, int l, int t, int w, int h, int wh)
	{
		source = true;
		color = c;
		x = l;
		y = t;
		width = w;
		height = h;
		wheel = wh;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void draw(Graphics g)
	{
		switch(color)
		{
		case 1:
			g.setColor(Color.red);
			break;
		case 2:
			g.setColor(Color.green);
			break;
		case 3:
			g.setColor(Color.blue);
			break;
		case 4:
			g.setColor(Color.magenta);
			break;
		case 5:
			g.setColor(Color.cyan);
			break;
		case 6:
			g.setColor(Color.yellow);
		}
		g.fillRect(x, y + width/3, width, height);
		g.fillArc(x+width/3, y, 2*width/3, 2*width/3, 0, 180);
		g.setColor(Color.black);
		g.fillOval(x, y+height+width/3-wheel, 2*wheel, 2*wheel);
		g.fillOval(x+width-2*wheel, y+height+width/3-wheel, 2*wheel, 2*wheel);
	}
	public boolean hitTest(int xpos, int ypos)
	{
		boolean hitx = ((xpos > x) && (xpos < x + width));
		boolean hity = ((ypos > y + width/3) && (ypos < y + height));
		if(hitx && hity)
		{
			return true;
		}
		double r = ((double)width)/3;
		double xc = x + width*2/3;
		double yc = y + width/3;
		if(Math.sqrt(Math.pow(xpos - xc, 2) + Math.pow(ypos - yc, 2))<=r)
		{
			return true;
		}
		r = (double)wheel;
		xc = x + wheel;
		yc = y + height + width/3;
		if(Math.sqrt(Math.pow(xpos - xc, 2) + Math.pow(ypos - yc, 2))<=r)
		{
			return true;
		}
		xc = x + width - wheel;
		if(Math.sqrt(Math.pow(xpos - xc, 2) + Math.pow(ypos - yc, 2))<=r)
		{
			return true;
		}
		return false;
	}
	public void move(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	public void setX(int xn)
	{
		x = xn;
	}
	public void setY(int yn)
	{
		y = yn;
	}
}
