import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TestDialog extends JFrame implements ActionListener {
	JLabel  myLabel;
    ArrayList<String> nameArray;
    private DefaultListModel names;
	JList   nameList;
	JScrollPane scrollPane;
	JButton bnAdd;
    JButton bnDel;
    JButton bnEdit;

    
    public TestDialog() {
		super("Test Dialog");

	    Container c = getContentPane();
	    c.setLayout(null);		

	    myLabel = new JLabel("Name");
		myLabel.setSize( 200, 50 );
		myLabel.setLocation( 100, 100 );
		c.add(myLabel);

	    nameArray = new ArrayList<String>(); 
		names = new DefaultListModel();
		nameList = new JList(names);
	    // nameList.setVisibleRowCount( 5 );
	    nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
	    // nameList.setFont(new Font("Courier New", Font.PLAIN, 12));
	    nameList.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    scrollPane = new JScrollPane(nameList);
	    scrollPane.setSize(250, 80);
	    scrollPane.setLocation(100, 150);
	    c.add( scrollPane );
	      
		bnAdd = new JButton("Add");
		bnAdd.setSize( 100, 50 );
		bnAdd.setLocation( 100, 500 );
		bnAdd.addActionListener(this);
		c.add(bnAdd);

		bnDel = new JButton("Delete");
		bnDel.setSize( 100, 50 );
		bnDel.setLocation( 250, 500 );
		bnDel.addActionListener(this);
		c.add(bnDel);

		bnEdit = new JButton("Edit");
		bnEdit.setSize( 100, 50 );
		bnEdit.setLocation( 400, 500 );
		bnEdit.addActionListener(this);
		c.add(bnEdit);

	    setSize( 800, 600 );
	    setLocation( 100, 100 );
	    setVisible(true);
   }
    
    public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bnAdd) {
		    MyDialog dialogWnd = new MyDialog(this, "Add Name", "");
		    if (!dialogWnd.isCancelled()) {
		    	nameArray.add(dialogWnd.getAnswer());
		    	names.addElement(dialogWnd.getAnswer());
                nameList.setSelectedIndex(names.size()-1);
                nameList.ensureIndexIsVisible(names.size()-1);
		    }
		}
	    else if(e.getSource()==bnEdit) {
	    	int index=nameList.getSelectedIndex();
	    	if (index>=0) {
			    MyDialog dialogWnd = new MyDialog(this, "Edit Name", nameArray.get(index));
			    if (!dialogWnd.isCancelled()) {
			    	nameArray.set(index, dialogWnd.getAnswer());
			    	names.set(index, dialogWnd.getAnswer());
			    }
	    	}
		}
	    else if(e.getSource()==bnDel) {
	    	int index=nameList.getSelectedIndex();
	    	if (index>=0) {
			    nameArray.remove(index);
			    names.remove(index);
			    if (names.size()>0) {	// not empty
			    	if (index==names.size()) {	// last one deleted
			    		index--;
			    	}
			    	nameList.setSelectedIndex(index);
			    	nameList.ensureIndexIsVisible(index);
			    }
	    	}
		}
    }

	public static void main(String[] args) {
    	TestDialog mainWnd = new TestDialog();
    }
}
