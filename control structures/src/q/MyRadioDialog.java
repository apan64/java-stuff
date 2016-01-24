import java.awt.Container;
import java.awt.event.*;
import javax.swing.*; 

public class MyDialog extends JDialog implements ActionListener {
    private JLabel myLabel;
    private JRadioButton rbFreshman;    
    private JRadioButton rbSophomore;    
    private JRadioButton rbJunior;    
    private JRadioButton rbSenior;    
    private ButtonGroup radioGroup;
    
    private JButton okButton;
    private JButton cancelButton;
    
    private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    private int answer;
    public int getAnswer() { return answer; }

    public MyDialog(JFrame owner, String title, int initVal) {
		super(owner, title, true);
		
	    Container c = getContentPane();
	    c.setLayout(null);		

	    myLabel = new JLabel("Select your classification:");
		myLabel.setSize( 200, 50 );
		myLabel.setLocation( 100, 50 );
		c.add(myLabel);
		
		rbFreshman = new JRadioButton("Freshman", initVal==0);
		rbFreshman.setSize( 100, 25 );
		rbFreshman.setLocation( 100, 100 );
		c.add(rbFreshman);

		rbSophomore = new JRadioButton("Sophomore", initVal==1);
		rbSophomore.setSize( 100, 25 );
		rbSophomore.setLocation( 100, 125 );
		c.add(rbSophomore);

		rbJunior = new JRadioButton("Junior", initVal==2);
		rbJunior.setSize( 100, 25 );
		rbJunior.setLocation( 100, 150 );
		c.add(rbJunior);

		rbSenior = new JRadioButton("Senior", initVal==3);
		rbSenior.setSize( 100, 25 );
		rbSenior.setLocation( 100, 175 );
		c.add(rbSenior);

		radioGroup = new ButtonGroup();
		radioGroup.add(rbFreshman);
		radioGroup.add(rbSophomore);
		radioGroup.add(rbJunior);
		radioGroup.add(rbSenior);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setSize( 75, 50 );
		cancelButton.setLocation( 100, 200 );
		c.add(cancelButton);	

		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setSize( 75, 50 );
		okButton.setLocation( 200, 200 );
		c.add(okButton);	
		
	    setSize( 400, 300 );
		setLocationRelativeTo(owner);
		setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okButton) {
			answer = -1;
			if (rbFreshman.isSelected()) answer = 0;
			if (rbSophomore.isSelected()) answer = 1;
			if (rbJunior.isSelected()) answer = 2;
			if (rbSenior.isSelected()) answer = 3;
		    cancelled = false;
		    setVisible(false);
		}
		else if(e.getSource()==cancelButton) {
		    cancelled = true;
		    setVisible(false);
		}
    }
    
}
