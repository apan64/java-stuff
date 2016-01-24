import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import java.util.*;

public class BuildingPanel extends JPanel  
	implements MouseListener, MouseMotionListener
{	
	// state representation
	private ArrayList<BuildingBlock> originals;  
	private ArrayList<BuildingBlock> duplicates;  

	private BuildingBlock BlockToBeMoved;
	private int m_nOffsetX;	// difference between cursor and top-left corner
	private int m_nOffsetY;

	// double buffering
	private Image backBuffer;
	private Graphics gBackBuffer;
	
	boolean isInitialized;
	
    // init and register mouse event handler
    public BuildingPanel()
    {
    	isInitialized=false;
        // handle mouse and mouse motion events
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    // Set up the initial state after the panel is created
    void init()
    {
		// Initial state
		duplicates = new ArrayList<BuildingBlock>();
		originals = new ArrayList<BuildingBlock>();
		Color[] colors = {Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow};
		int count=colors.length;
		int dx=10;
		int dy=10;
		int gap=20;
		int length=(getSize().height-2*dy-(count-1)*gap)/count;
		for (int i=0; i<count; i++) { 
			originals.add(new BuildingBlock((i<count/2)?0:1, dx, dy+i*(length+gap), length, length, colors[i]));
		}
		BlockToBeMoved=null; // no shape selected

		// create the back buffer
		backBuffer = createImage(getSize().width, getSize().height);
		gBackBuffer = backBuffer.getGraphics();
    }
    
    // State Presentation
    public void paintComponent( Graphics g )
    {
        // super.paintComponent( g ); // clears drawing area

    	if (!isInitialized) {
    		isInitialized=true;
    		init();
    	}
		// State Presentation, using double buffers
		// First, clear the back buffer
		gBackBuffer.setColor(Color.white);
		gBackBuffer.clearRect(0, 0, getSize().width, getSize().height);
		// draw the originals to back buffer
		for (int i=0; i<originals.size(); i++) {
			originals.get(i).draw(gBackBuffer);
		}
		// draw the duplicates to back buffer
		for (int i=0; i<duplicates.size(); i++) {
			duplicates.get(i).draw(gBackBuffer);
		}
		// copy from back buffer to front
		g.drawImage(backBuffer, 0, 0, null);
    } // end method paintComponent
    
    // MouseListener event handlers
    public void mouseClicked( MouseEvent e )
    {
		if (e.isMetaDown()) {	// right button
			for (int i=duplicates.size()-1; i>=0; i--) {
				if (duplicates.get(i).containPoint(e.getX(), e.getY())) {
					duplicates.remove(i);
					repaint();
					break;
				}
			}
		}
    }

    public void mousePressed( MouseEvent e )
    {
    	if (e.isMetaDown()) return;	// ignore right button
        
		// First, check the originals, from top down (i.e. back to front)
		for (int i=duplicates.size()-1; i>=0; i--) {
			BuildingBlock p=duplicates.get(i);
			if (p.containPoint(e.getX(), e.getY())) {
				duplicates.remove(i);
				duplicates.add(p);	// move to the end, i.e. the top
				BlockToBeMoved=p;
				m_nOffsetX=e.getX()-BlockToBeMoved.getX();
				m_nOffsetY=e.getY()-BlockToBeMoved.getY();
				repaint();
				return;
			}
		}
		// Second, check the orginals 
		for (int i=originals.size()-1; i>=0; i--) {
			BuildingBlock p=originals.get(i);
			if (p.containPoint(e.getX(), e.getY())) {
				BuildingBlock p2=new BuildingBlock(p); // make a copy of p
				duplicates.add(p2);	// add to the end
				BlockToBeMoved=p2;	// p2 is selected, to be moved
				m_nOffsetX=e.getX()-BlockToBeMoved.getX();
				m_nOffsetY=e.getY()-BlockToBeMoved.getY();
				repaint();
				return;
			}
		}
    }

    public void mouseReleased( MouseEvent e )
    {
		BlockToBeMoved=null; // no shape selected
   }

    public void mouseEntered( MouseEvent e )
    {
    }

    public void mouseExited( MouseEvent e )
    {
    }

    public void mouseMoved( MouseEvent e )
    {
    }
    
    public void mouseDragged( MouseEvent e )
    {
        if (e.isMetaDown()) return;	// ignore right button
    	
		if (BlockToBeMoved!=null) {
			BlockToBeMoved.setX(e.getX()-m_nOffsetX);
			BlockToBeMoved.setY(e.getY()-m_nOffsetY);
			repaint();
		}

    } // end method mouseDragged
} // end class BuildingBlockPanel
