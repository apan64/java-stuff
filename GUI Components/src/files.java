import java.awt.Container;
import java.awt.event.*;

import javax.swing.*; 

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextField;
public class files extends JDialog implements ActionListener {
	private boolean save, cancelled;
	private File answer;
	private JButton finish, cancel;
	private JFileChooser txt;
	public files(JFrame owner, String title, boolean savetype)
	{
		super(owner, title, true);
		save = savetype;
		cancelled = false;
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel label = new JLabel("Filename?");
		label.setSize(150, 50);
		label.setLocation(25, 25);
		c.add(label);
		
		txt = new JFileChooser("");
		txt.setSize(400, 400);
		txt.setLocation(25, 100);
		c.add(txt);
		
		if(save)
			finish = new JButton("Save");
		else
			finish = new JButton("Open");
		finish.addActionListener(this);
		finish.setSize(100, 50);
		finish.setLocation(20, 600);
		c.add(finish);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setSize(100, 50);
		cancel.setLocation(180, 600);
		c.add(cancel);
		
		setSize(500, 700);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	public File getAnswer()
	{
		return answer;
	}
	public boolean isCancelled()
	{
		return cancelled;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==finish) {
			answer = txt.getCurrentDirectory();
			cancelled = false;
			setVisible(false);
		}
		else if (e.getSource() == cancel)
		{
			cancelled = true;
			setVisible(false);
		}
	}

}
