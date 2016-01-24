/******************************
 * 
 * @author Patrick Atkins
 * CS 350 Project 5
 * April 3 2015
 * CWID: 11495287
 * MyDialog.java
 * Creates a dialog box that passes an instance of CDriver to the main window, containg the form's input
 *
 *****************************/

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyDialog  extends JDialog implements ActionListener{
	
	private JLabel custNoLabel;
	private JLabel custNo;
	
	private JLabel custNameLabel;
	private JTextField custNameText;
	
	private JLabel yearsDrivingLabel;
	private IntegerField yearsDrivingText;
	
	private JLabel jeepOwnerLabel;
	private JRadioButton rbYes;
	private JRadioButton rbNo;
	private ButtonGroup jeepOwnerGroup;
	
	private JLabel modelsLabel;
	private JCheckBox cbCompass;
	private JCheckBox cbGrandCherokee;
	private JCheckBox cbPatriot;
	private JCheckBox cbRenegade;
	private JCheckBox cbOthers;
	
	private JLabel transmissionLabel;
	private JRadioButton rb5spd;
	private JRadioButton rb6spd;
	private JRadioButton rb8spd;
	private JRadioButton rbDontCare;
	private ButtonGroup transmissionGroup;
	
	private JButton submit;
	private JButton cancel;
	
	private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    private CDriver answer;
    public CDriver getAnswer() { return answer; }
    
    public MyDialog(JFrame owner, String title, CDriver src){
    	super(owner, title, true);
    	
    	Container c = getContentPane();
    	c.setLayout(null);
    	
    	custNoLabel = new JLabel("Customer No.");
    	custNoLabel.setSize(90,20);
    	custNoLabel.setLocation(50,60);
    	c.add(custNoLabel);
    	
    	//format customer number with proceeding 0's
    	String num = "" + src.getCustomerNumber();
		int numZero = 4-num.length();
		String label = "";
		for (int i = 0; i<numZero;i++){
			label += "0";
		}
		label += num;
    	custNo = new JLabel(label);
    	custNo.setSize(75,20);
    	custNo.setLocation(145,60);
    	c.add(custNo);
    	
    	custNameLabel = new JLabel("Customer Name");
    	custNameLabel.setSize(105,20);
    	custNameLabel.setLocation(50,110);
    	c.add(custNameLabel);
    	
    	custNameText = new JTextField(src.getName());
    	custNameText.setSize(300,25);
    	custNameText.setLocation(155,110);
    	custNameText.setDocument(new JTextFieldLimit(19));
    	custNameText.setText(src.getName());
    	c.add(custNameText);
    	
    	yearsDrivingLabel = new JLabel("How many years have you been driving?");
    	yearsDrivingLabel.setSize(275,20);
    	yearsDrivingLabel.setLocation(50,180);
    	c.add(yearsDrivingLabel);
    	
    	if (src.getYearsDriving() != -1){yearsDrivingText = new IntegerField("" + src.getYearsDriving());} //-1 holds no entry
    	else{yearsDrivingText = new IntegerField("" + src.getYearsDriving());}
    	yearsDrivingText.setSize(60,25);
    	yearsDrivingText.setLocation(335,180);
    	c.add(yearsDrivingText);
    	
    	jeepOwnerLabel = new JLabel("Do you have a jeep?");
    	jeepOwnerLabel.setSize(135,20);
    	jeepOwnerLabel.setLocation(50,240);
    	c.add(jeepOwnerLabel);
    	
    	rbYes = new JRadioButton("Yes", src.getJeepOwner()==0);
    	rbYes.setSize(55,20);
    	rbYes.setLocation(85,275);
    	c.add(rbYes);

    	rbNo = new JRadioButton("No", src.getJeepOwner()==1);
    	rbNo.setSize(50,20);
    	rbNo.setLocation(180,275);
    	c.add(rbNo);
    	
    	jeepOwnerGroup = new ButtonGroup();
    	jeepOwnerGroup.add(rbYes);
    	jeepOwnerGroup.add(rbNo);
    	
    	modelsLabel = new JLabel("Which Models are you interested in?");
    	modelsLabel.setSize(240,20);
    	modelsLabel.setLocation(50,320);
    	c.add(modelsLabel);
    	
    	cbCompass = new JCheckBox("Compass", src.getModels().contains("C"));
    	cbCompass.setSize(90,20);
    	cbCompass.setLocation(85,360);
    	c.add(cbCompass);
    	
    	cbGrandCherokee = new JCheckBox("Grand Cherokee", src.getModels().contains("G"));
    	cbGrandCherokee.setSize(135,20);
    	cbGrandCherokee.setLocation(185,360);
    	c.add(cbGrandCherokee);
    	
    	cbPatriot = new JCheckBox("Patriot", src.getModels().contains("P"));
    	cbPatriot.setSize(75,20);
    	cbPatriot.setLocation(330,360);
    	c.add(cbPatriot);
    	
    	cbRenegade = new JCheckBox("Renegade", src.getModels().contains("R"));
    	cbRenegade.setSize(95,20);
    	cbRenegade.setLocation(435,360);
    	c.add(cbRenegade);
    	
    	cbOthers = new JCheckBox("Others", src.getModels().contains("O"));
    	cbOthers.setSize(75,20);
    	cbOthers.setLocation(550,360);
    	c.add(cbOthers);
    	
    	transmissionLabel = new JLabel("Transmission:");
    	transmissionLabel.setSize(95,20);
    	transmissionLabel.setLocation(50,405);
    	c.add(transmissionLabel);
    	    	
    	rb5spd = new JRadioButton("5-Speed Manual", src.getTransmission()==0);
    	rb5spd.setSize(135 ,20);
    	rb5spd.setLocation(85,445);
    	c.add(rb5spd);
    	
    	rb6spd = new JRadioButton("6-Speed Manual", src.getTransmission()==1);
    	rb6spd.setSize(135,20);
    	rb6spd.setLocation(220,445);
    	c.add(rb6spd);
    	
    	rb8spd = new JRadioButton("8-Speed Paddle-Shift Automatic", src.getTransmission()==2);
    	rb8spd.setSize(240,20);
    	rb8spd.setLocation(360,445);
    	c.add(rb8spd);
    	
    	rbDontCare = new JRadioButton("Don't Care", src.getTransmission()==3);
    	rbDontCare.setSize(100,20);
    	rbDontCare.setLocation(595,445);
    	c.add(rbDontCare);
    	
    	transmissionGroup = new ButtonGroup();
    	transmissionGroup.add(rb5spd);
    	transmissionGroup.add(rb6spd);
    	transmissionGroup.add(rb8spd);
    	transmissionGroup.add(rbDontCare);
    	
    	submit = new JButton("Submit");
    	submit.addActionListener(this);
    	submit.setSize(145,50);
    	submit.setLocation(190,515);
    	c.add(submit);
    	
    	cancel = new JButton("Cancel");
    	cancel.addActionListener(this);
    	cancel.setSize(145,50);
    	cancel.setLocation(490,515);
    	c.add(cancel);
    	
    	setSize(820,640);
    	setLocationRelativeTo(owner);
		setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	if (e.getSource()==submit) {
    		//prepares parameters of CDriver answer
    		int num = Integer.parseInt(custNo.getText());
    		int y = Integer.parseInt(yearsDrivingText.getText());
    		int o;
    		if (rbYes.isSelected()){o = 0;}
    		else if (rbNo.isSelected()){o=1;}
    		else {o=-1;}
    		String m = "";
    		if (cbCompass.isSelected()){m+="C";}
    		else {m+="-";}
    		if (cbGrandCherokee.isSelected()){m+="G";}
    		else {m+="-";}
    		if (cbPatriot.isSelected()){m+="P";}
    		else {m+="-";}
    		if (cbRenegade.isSelected()){m+="R";}
    		else {m+="-";}
    		if (cbOthers.isSelected()){m+="O";}
    		else {m+="-";}
    		String t = "";
    		if (rb5spd.isSelected()){t = "5-Manual";}
    		else if (rb6spd.isSelected()){t = "6-Manual";}
    		else if (rb8spd.isSelected()){t = "8-Auto";}
    		else if (rbDontCare.isSelected()){t = "Don't Care";}
    		else {t="";}//when none are selected
			answer = new CDriver(num, custNameText.getText(),y,o,m,t);
		    cancelled = false;
		    setVisible(false);
		}
		else if(e.getSource()==cancel) {
		    cancelled = true;
		    setVisible(false);
		}
    }
}
