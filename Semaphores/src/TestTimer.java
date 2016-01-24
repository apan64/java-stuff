import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TestTimer extends JApplet implements ActionListener {
    private JLabel lbSecs = null;
    private JProgressBar progressBar = null;    
    private JButton bnStart = null;
    private JButton bnStop = null;

    private Timer timer=null;
    private int count=0;
    
    public TestTimer() {   
    	Container c = getContentPane();
    	c.setLayout(null);		

	    lbSecs = new JLabel("0.0");
	    lbSecs.setSize( 32, 16 );
	    lbSecs.setLocation( 37, 85 );
		c.add(lbSecs);
		
		progressBar = new JProgressBar();
		progressBar.setSize( 120, 23 );
		progressBar.setLocation( 89, 85 );
		// progressBar.setStringPainted(true); // Paint the percent in a string
	    progressBar.setMaximum(60);
	    progressBar.setValue(0);
		c.add(progressBar);
    
		bnStart = new JButton("Start");
		bnStart.addActionListener(this);
		bnStart.setSize( 65, 27 );
		bnStart.setLocation( 40, 190 );
		c.add(bnStart);	

		bnStop = new JButton("Stop");
		bnStop.setEnabled(false);
		bnStop.addActionListener(this);
		bnStop.setSize( 65, 27 );
		bnStop.setLocation( 146, 190 );
		c.add(bnStop);	
		
		// Create a timer
		timer = new Timer(100, this);
    }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bnStart) {
			timer.start();
			bnStart.setEnabled(false);
			bnStop.setEnabled(true);
		}
		else if(e.getSource()==bnStop) {
			timer.stop();
			bnStart.setEnabled(true);
			bnStop.setEnabled(false);
		}
		else if(e.getSource()==timer) {
			count++; 
			count %= 600; 
			lbSecs.setText(String.format("%d.%d", count/10, count%10));
			// Update ProgressBar
			progressBar.setValue(count/10); 
		}
    }
 
	// Add the following if being run as an application
    public static void main(String[] args) {
		TestTimer applet = new TestTimer();
	    JFrame frame = new JFrame();
	    frame.setTitle("One Minute Timer");
	    frame.add(applet);
	    frame.setSize(270, 300);
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
    }
}
