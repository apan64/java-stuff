import java.awt.Color;
import java.awt.Graphics;

public class BuildingBlock {
	public BuildingBlock(int type, int x, int y, int w, int h, Color c) {
		Type=type;
		X=x;
		Y=y;
		Width=w;
		Height=h;
		FillColor=c;
	}
	public BuildingBlock(BuildingBlock src) {	// copy constructor
		Type=src.Type;
		X=src.X;
		Y=src.Y;
		Width=src.Width;
		Height=src.Height;
		FillColor=src.FillColor;
	}

	public int getX() { return X; }
	public void setX(int x) { X=x; }
	public int getY() { return Y; }
	public void setY(int y) { Y=y; }

	public void draw(Graphics g) {
		g.setColor(FillColor);
		switch (Type) {
		case 0:
			g.fillOval(X, Y, Width, Height);
			break;
		case 1:
			g.fillRect(X, Y, Width, Height);
			break;
		}
	}

	public boolean containPoint(int x, int y) {
		switch (Type) {
		case 0:
			{
				double a=Width/2.0;
				double b=Height/2.0;
				double xc=X+a;
				double yc=Y+b;
				return ((x-xc)*(x-xc)/(a*a)+(y-yc)*(y-yc)/(b*b)<=1.0);
			}
		case 1:
			return (x>=X && y>=Y && x<X+Width && y<Y+Height);
		}

		return false;
	}

	private int Type;	// 0: oval; 1: rectangle
	private int X;
	private int Y;
	private int Width;
	private int Height;
	private Color FillColor;
}