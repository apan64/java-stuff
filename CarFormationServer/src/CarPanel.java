/*CS350
 Project #6
 Andrew Pan & Chipper Atkins
 This class controls the actual drawing of the cars as well as 
 listening for user inputs via mouse motion and clicking.
 */

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CarPanel extends JPanel 
	implements MouseListener, MouseMotionListener {
	private ArrayList<MyCar> originals;
	private ArrayList<MyCar> duplicates;
	private MyCar carmove;
	private int m_nOffsetX;
	private int m_nOffsetY;
	private Image backBuffer;
	private Graphics gBackBuffer;
	private CarClient ownerC;
	private CarServer ownerS;
	private boolean client, server;
	boolean isInitialized;
	
	public CarPanel()
	{
		isInitialized = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		client = false;
		server = false;
	}
	public CarPanel(CarClient c)
	{
		isInitialized = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		ownerC = c;
		client = true;
		server = false;
	}
	public CarPanel(CarServer s)
	{
		isInitialized = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		ownerS = s;
		client = false;
		server = true;
	}
	public boolean getMove()
	{
		return (carmove != null);
	}
	public ArrayList<MyCar> getO()
	{
		return originals;
	}
	public ArrayList<MyCar> getD()
	{
		return duplicates;
	}
	public void setO(ArrayList<MyCar> q)
	{
		originals = q;
		repaint();
	}
	public void setD(ArrayList<MyCar> q)
	{
		duplicates = q;
		repaint();
		init();
	}
	void init()
	{
		duplicates = new ArrayList<MyCar>();
		originals = new ArrayList<MyCar>();
		int w = (getSize().height - 2 * 10 - 5 * 20)/6;
		int h = (getSize().height - 2 * 50 - 5 * 20)/6;
		for(int x = 0; x < 6; x++)
		{
			originals.add(new MyCar(x + 1, 10 + x*(w + 20), 10, w, h, w/4));
		}
		carmove = null;
		backBuffer = createImage(getSize().width, getSize().height);
		gBackBuffer  = backBuffer.getGraphics();
	}
	public void paintComponent(Graphics g)
	{
		if(!isInitialized)
		{
			isInitialized = true;
			init();
		}
		gBackBuffer.setColor(Color.white);
		gBackBuffer.clearRect(0,  0,  getSize().width,  getSize().height);
		for(int i = 0; i < originals.size(); i++)
		{
			originals.get(i).draw(gBackBuffer);
		}
		for(int i = 0; i < duplicates.size(); i++)
		{
			duplicates.get(i).draw(gBackBuffer);
		}
		g.drawImage(backBuffer,  0,  0, null);
	}
	public void mouseClicked( MouseEvent e )
    {
		if (e.isMetaDown()) {	// right button
			for (int i=duplicates.size()-1; i>=0; i--) {
				if (duplicates.get(i).hitTest(e.getX(), e.getY())) {
					duplicates.remove(i);
					repaint();
					break;
				}
			}
		}
		if(client) ownerC.sendData(duplicates);
		else if(server) ownerS.sendData(duplicates);
    }
	public void mousePressed( MouseEvent e )
    {
    	if (e.isMetaDown()) return;	// ignore right button
        
		// First, check the originals, from top down (i.e. back to front)
		for (int i=duplicates.size()-1; i>=0; i--) {
			MyCar p=duplicates.get(i);
			if (p.hitTest(e.getX(), e.getY())) {
				duplicates.remove(i);
				duplicates.add(p);	// move to the end, i.e. the top
				carmove=p;
				m_nOffsetX=e.getX()-carmove.getX();
				m_nOffsetY=e.getY()-carmove.getY();
				repaint();
				return;
			}
		}
		// Second, check the originals 
		for (int i=originals.size()-1; i>=0; i--) {
			MyCar p=originals.get(i);
			if (p.hitTest(e.getX(), e.getY())) {
				MyCar p2=new MyCar(p); // make a copy of p
				duplicates.add(p2);	// add to the end
				carmove=p2;	// p2 is selected, to be moved
				m_nOffsetX=e.getX()-carmove.getX();
				m_nOffsetY=e.getY()-carmove.getY();
				repaint();
				return;
			}
		}
		if(client) ownerC.sendData(duplicates);
		else if(server) ownerS.sendData(duplicates);
    }
	@Override
	public void mouseDragged(MouseEvent e) {
        if (e.isMetaDown()) return;	// ignore right button
    	
		if (carmove!=null) {
			carmove.setX(e.getX()-m_nOffsetX);
			carmove.setY(e.getY()-m_nOffsetY);
			repaint();
		}
		if(client) ownerC.sendData(duplicates);
		else if(server) ownerS.sendData(duplicates);
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		carmove = null;
		if(client) ownerC.sendData(duplicates);
		else if(server) ownerS.sendData(duplicates);
	}
}
