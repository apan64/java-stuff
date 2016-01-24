/*CS350
 *Project #5
 *Andrew Pan
 *This class defines the dialog window, which adds or edits drivers and passes the
 *information onto the main window.
 */
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class dialog extends JDialog implements ActionListener{
	private JLabel label, label2, label3, label4, label5, label6, label7;
	private JTextField text, text2;
	private JCheckBox check, check2, check3, check4, check5;
	private JRadioButton radio, radio2, radio3, radio4, radio5, radio6;
	private JButton ok, cancel;
	private boolean cancelled;
	private CDriver driver, answer;
	private int drivernumber;
	public boolean isCancelled()
	{
		return cancelled;
	}
	public CDriver getAnswer()
	{
		return answer;
	}
	public dialog(JFrame owner, String title, CDriver d)
	{
		super(owner, title, true);
		driver = d;
		drivernumber = d.getNum();
		cancelled = false;
		Container c = getContentPane();
		c.setLayout(null);
		
		label = new JLabel("Customer No.");
		label.setSize(200, 50);
		label.setLocation(50, 50);
		c.add(label);
		
		String custn = "";
		for(int x = 5; x > Integer.toString(drivernumber).length(); x--)
		{
			custn += "0";
		}
		label2 = new JLabel(custn + Integer.toString(drivernumber));
		label2.setSize(200, 50);
		label2.setLocation(150, 50);
		c.add(label2);
		
		label3 = new JLabel("Customer Name");
		label3.setSize(200, 50);
		label3.setLocation(50, 100);
		c.add(label3);
		
		label4 = new JLabel("How many years have you been driving?");
		label4.setSize(300, 50);
		label4.setLocation(50, 200);
		c.add(label4);
		
		label5 = new JLabel("Do you have a Jeep?");
		label5.setSize(200, 50);
		label5.setLocation(50, 250);
		c.add(label5);
		
		label6 = new JLabel("Which Models are you interested in?");
		label6.setSize(250, 50);
		label6.setLocation(50, 350);
		c.add(label6);
		
		label7 = new JLabel("Transmission:");
		label7.setSize(200, 50);
		label7.setLocation(50, 450);
		c.add(label7);
		
		if(driver.newd){
			text = new JTextField("");
		}
		else text = new JTextField(driver.name);
		text.setSize(300, 25);
		text.setLocation(150, 112);
		c.add(text);
		
		if(driver.newd){
			text2 = new JTextField("");
		}
		else text2 = new JTextField(Integer.toString(driver.years));
		text2.setSize(100, 25);
		text2.setLocation(300, 212);
		c.add(text2);
		
		if(driver.model[0]) check = new JCheckBox("Compass", true);
		else check = new JCheckBox("Compass", false);
		check.setSize(100, 50);
		check.setLocation(100, 400);
		c.add(check);
		
		if(driver.model[1]) check2 = new JCheckBox("Grand Cherokee", true);
		else check2 = new JCheckBox("Grand Cherokee", false);
		check2.setSize(150, 50);
		check2.setLocation(200, 400);
		c.add(check2);
		
		if(driver.model[2]) check3 = new JCheckBox("Patriot", true);
		else check3 = new JCheckBox("Patriot", false);
		check3.setSize(100, 50);
		check3.setLocation(350, 400);
		c.add(check3);
		
		if(driver.model[3]) check4 = new JCheckBox("Renegade", true);
		else check4 = new JCheckBox("Renegade", false);
		check4.setSize(100, 50);
		check4.setLocation(450, 400);
		c.add(check4);
		
		if(driver.model[4]) check5 = new JCheckBox("Other", true);
		else check5 = new JCheckBox("Other", false);
		check5.setSize(100, 50);
		check5.setLocation(550, 400);
		c.add(check5);
		
		if(driver.jeep)
		{
			radio = new JRadioButton("Yes", true);
			radio2 = new JRadioButton("No", false);
		}
		else if(driver.newd)
		{
			radio = new JRadioButton("Yes");
			radio2 = new JRadioButton("No");
		}
		else
		{
			radio = new JRadioButton("Yes");
			radio2 = new JRadioButton("No", true);
		}
		radio.setSize(100, 25);
		radio.setLocation(100, 300);
		c.add(radio);
		
		radio2.setSize(100, 25);
		radio2.setLocation(200, 300);
		c.add(radio2);
		
		ButtonGroup jeepGroup = new ButtonGroup();
		jeepGroup.add(radio);
		jeepGroup.add(radio2);
		
		radio3 = new JRadioButton("5-Speed Manual", driver.transmission == 1);
		radio3.setSize(150, 25);
		radio3.setLocation(100, 500);
		c.add(radio3);
		
		radio4 = new JRadioButton("6-Speed Manual", driver.transmission == 2);
		radio4.setSize(150, 25);
		radio4.setLocation(250, 500);
		c.add(radio4);
		
		radio5 = new JRadioButton("8-Speed Paddle-Shift Automatic", driver.transmission == 3);
		radio5.setSize(250, 25);
		radio5.setLocation(400, 500);
		c.add(radio5);
		
		radio6 = new JRadioButton("Don't Care", driver.transmission == 4);
		radio6.setSize(200, 25);
		radio6.setLocation(650, 500);
		c.add(radio6);
		
		ButtonGroup transmissionGroup = new ButtonGroup();
		transmissionGroup.add(radio3);
		transmissionGroup.add(radio4);
		transmissionGroup.add(radio5);
		transmissionGroup.add(radio6);
		
		ok = new JButton("Submit");
		ok.addActionListener(this);
		ok.setSize(75, 50);
		ok.setLocation(200, 700);
		c.add(ok);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setSize(75, 50);
		cancel.setLocation(300, 700);
		c.add(cancel);
		
		setSize(800, 900);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ok) {
			boolean jeeped;
			if(radio.isSelected()) jeeped = true;
			else if(radio2.isSelected()) jeeped = false;
			else return;
			boolean checkb = false;
			boolean checkb2 = false;
			boolean checkb3 = false;
			boolean checkb4 = false;
			boolean checkb5 = false;
			if(check.isSelected()) checkb = true;
			if(check2.isSelected()) checkb2 = true;
			if(check3.isSelected()) checkb3 = true;
			if(check4.isSelected()) checkb4 = true;
			if(check5.isSelected()) checkb5 = true;
			int trans = 0;
			if(radio3.isSelected()) trans = 1;
			else if (radio4.isSelected()) trans = 2;
			else if (radio5.isSelected()) trans = 3;
			else if (radio6.isSelected()) trans = 4;
			else return;
			answer = new CDriver(drivernumber, text.getText(), Integer.parseInt(text2.getText()), jeeped, checkb, checkb2, checkb3, checkb4, checkb5, trans);
		    cancelled = false;
		    setVisible(false);
		}
		else if(e.getSource()==cancel) {
		    cancelled = true;
		    setVisible(false);
		}
    }
}
