import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class CPiece {
	public CPiece(int x0, int y0, int x1, int y1, int x2, int y2) {	// for triangle
		Type=3;
		Xs=new double[3];
		Ys=new double[3];
		Xs[0]=x0; Ys[0]=y0;
		Xs[1]=x1; Ys[1]=y1;
		Xs[2]=x2; Ys[2]=y2;
	}

	public CPiece(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {	// for quadrilateral
		Type=4;
		Xs=new double[4];
		Ys=new double[4];
		Xs[0]=x0; Ys[0]=y0;
		Xs[1]=x1; Ys[1]=y1;
		Xs[2]=x2; Ys[2]=y2;
		Xs[3]=x3; Ys[3]=y3;
	}

	private boolean isLeft(int x, int y, int id) {	// Is Point (x, y) located to the left when walking from id to id+1?
		return (y-Ys[id])*(Xs[(id+1)%Type]-Xs[id])<(x-Xs[id])*(Ys[(id+1)%Type]-Ys[id]);	// note difference btw coordinate systems 
	}

	public boolean isInside(int x, int y) {
		for (int i=0; i<Type; i++) {
			if (!isLeft(x, y, i)) return false;
		}

		return true;
	}

	public void translate(int dx, int dy) {
		for (int i=0; i<Type; i++) {
			Xs[i] += dx;
			Ys[i] += dy;
		}
	}

	public void rotate(int deg) {
		double theta = Math.PI/180*deg;	// in radians
		for (int i=1; i<Type; i++) {
			double tmp_x;
			tmp_x = Xs[0] + (Xs[i] - Xs[0]) * Math.cos(theta) - (Ys[i] - Ys[0]) * Math.sin(theta);
			Ys[i] = Ys[0] + (Xs[i] - Xs[0]) * Math.sin(theta) + (Ys[i] - Ys[0]) * Math.cos(theta);
			Xs[i] = tmp_x;
		}
	}

	public void draw(Graphics g) {
		Polygon p = new Polygon();
		for (int i=0; i<Type; i++) {
			p.addPoint((int)(Xs[i]+0.5), (int)(Ys[i]+0.5));
		}

		g.setColor(Color.red);
		g.fillPolygon(p);
		g.setColor(Color.black);
		g.drawPolygon(p);
	}

	private int Type;	// 3: triangle; 4: quadrilateral
	private double[] Xs;
	private double[] Ys;
}
