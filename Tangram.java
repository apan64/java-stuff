import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Tangram extends JApplet  
// public class Tangram extends JPanel  
	implements KeyListener, MouseListener, MouseMotionListener
{
	// state representation
	private ArrayList<CPiece> blocks;  

	private boolean isShiftDown;
	private boolean isCtrlDown;

	private CPiece BlockToBeMoved;
	private int m_nOldX;
	private int m_nOldY;

    public Tangram()	// constructor
    {
		// Initial State
		blocks = new ArrayList<CPiece>();
		blocks.add(new CPiece(400, 300, 300, 200, 300, 400));
		blocks.add(new CPiece(400, 300, 300, 400, 500, 400));
		blocks.add(new CPiece(500, 200, 400, 200, 500, 300));
		blocks.add(new CPiece(400, 300, 450, 250, 350, 250));
		blocks.add(new CPiece(450, 350, 500, 400, 500, 300));
		blocks.add(new CPiece(400, 300, 450, 350, 500, 300, 450, 250));
		blocks.add(new CPiece(300, 200, 350, 250, 450, 250, 400, 200));

		isShiftDown=false;
		isCtrlDown=false;

		BlockToBeMoved=null; // no shape selected 
		
        // handle key, mouse and mouse motion events
		this.setFocusable(true);
		this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    // State Presentation
    public void paint( Graphics g )
    {
		// State Presentation, using double buffers
    	// create the back buffer
    	Image backBuffer=createImage(getSize().width, getSize().height);
    	Graphics gBackBuffer=backBuffer.getGraphics();
		// clear the back buffer
		gBackBuffer.setColor(Color.white);
		gBackBuffer.clearRect(0, 0, getSize().width, getSize().height);
		// draw the pieces to back buffer
		for (int i=0; i<blocks.size(); i++) {
			blocks.get(i).draw(gBackBuffer);
		}
		// copy from back buffer to front
		g.drawImage(backBuffer, 0, 0, null);
    } // end method paintComponent
    
    // KeyListener event handlers
    public void keyPressed( KeyEvent e )
    {
		if (e.getKeyCode()==KeyEvent.VK_SHIFT) isShiftDown=true;
		if (e.getKeyCode()==KeyEvent.VK_CONTROL) isCtrlDown=true;   	
    }
    
    public void keyReleased( KeyEvent e )
    {
		if (e.getKeyCode()==KeyEvent.VK_SHIFT) isShiftDown=false;
		if (e.getKeyCode()==KeyEvent.VK_CONTROL) isCtrlDown=false;   	    	
    }

    public void keyTyped( KeyEvent e )
    {    	
    }

    // MouseListener event handlers
    public void mouseClicked( MouseEvent e )
    {
		if (!isShiftDown && !isCtrlDown) return;
    	if (e.isMetaDown()) return;	// ignore right button

    	for (int i=blocks.size()-1; i>=0; i--) {
    		CPiece p=blocks.get(i);
			if (p.isInside(e.getX(), e.getY())) {
				p.rotate(isShiftDown?-5:5);	// note difference btw coordinate systems 
				blocks.remove(i);
				blocks.add(p);	// move to the end, i.e. the top
				repaint();
				break;
			}
		}
    }

    public void mousePressed( MouseEvent e )
    {
		if (isShiftDown || isCtrlDown) return;
    	if (e.isMetaDown()) return;	// ignore right button
        
		// search from top down (i.e. back to front)
		for (int i=blocks.size()-1; i>=0; i--) {
			CPiece p=blocks.get(i);
			if (p.isInside(e.getX(), e.getY())) {
				blocks.remove(i);
				blocks.add(p);	// move to the end, i.e. the top
				BlockToBeMoved=p;
				m_nOldX=e.getX();
				m_nOldY=e.getY();
				repaint();
				break;
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

    // MouseMotionListener event handlers
    public void mouseMoved( MouseEvent e )
    {
    }
    
    public void mouseDragged( MouseEvent e )
    {
        if (e.isMetaDown()) return;	// ignore right button
    	
		if (BlockToBeMoved!=null) {
			BlockToBeMoved.translate(e.getX()-m_nOldX, e.getY()-m_nOldY);
			m_nOldX=e.getX();
			m_nOldY=e.getY();
			repaint();
		}

    }

    // The main function for the application
	public static void main(String[] args) {
		JFrame application = new JFrame( "Tangram" );
	      
		Tangram tangramPanel = new Tangram();
		application.add(tangramPanel);
	  
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		application.setSize( 800, 600 ); // set frame size
		application.setVisible( true ); // display frame
	}
}
