import java.awt.Container;
import java.awt.event.*;
import javax.swing.*; 

public class MyDialog extends JDialog implements ActionListener {
    private JLabel myLabel;
    private JTextField myTextField;    
    private JButton okButton;
    private JButton cancelButton;
    
    private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    private String answer;
    public String getAnswer() { return answer; }
    // public void setAnswer(String str) { answer=str; }

    public MyDialog(JFrame owner, String title, String initStr) {
		super(owner, title, true);
		
	    Container c = getContentPane();
	    c.setLayout(null);		

	    myLabel = new JLabel("Enter your name:");
		myLabel.setSize( 200, 50 );
		myLabel.setLocation( 100, 100 );
		c.add(myLabel);
		
		myTextField = new JTextField(initStr);
		myTextField.setSize( 100, 50 );
		myTextField.setLocation( 200, 100 );
		c.add(myTextField);

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
			answer = myTextField.getText();
		    cancelled = false;
		    setVisible(false);
		}
		else if(e.getSource()==cancelButton) {
		    cancelled = true;
		    setVisible(false);
		}
    }
    
}
