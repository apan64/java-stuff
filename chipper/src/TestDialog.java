/******************************
 * 
 * @author Patrick Atkins
 * CS 350 Project 5
 * April 3 2015
 * CWID: 11495287
 * TestDialog.java
 * Creates a main window, displaying a list of all CDrivers, and allowing the user to create, edit, remove, and clear
 * CDrivers
 *
 *****************************/

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.io.*;

@SuppressWarnings("serial")
public class TestDialog extends JFrame implements ActionListener{
	JLabel  custNo;
	JLabel  custName;
	JLabel  drivYears;
	JLabel  jeepOwner;
	JLabel  models;
	JLabel  transmission;
    ArrayList<CDriver> driversArray;
    private DefaultListModel<CDriver> drivers;
	JList<CDriver>   driverList;
	JScrollPane scrollPane;
	JButton bnAdd;
    JButton bnDel;
    JButton bnEdit;
    JButton bnClear;
    JButton bnOpen;
    JButton bnSave;
    ObjectOutputStream output;
    ObjectInputStream input;
    JFileChooser fileChooser;
    
    public TestDialog() {
		super("Test Drive Requests");

	    Container c = getContentPane();
	    c.setLayout(null);		

	    custNo = new JLabel("Customer No.");
	    custNo.setSize(90,15);
	    custNo.setLocation(30,50);
	    c.add(custNo);
	    
	    custName = new JLabel("Customer Name");
	    custName.setSize(105,15);
	    custName.setLocation(155,50);
	    c.add(custName);
	    
	    drivYears = new JLabel("Driving Years");
	    drivYears.setSize(100,15);
	    drivYears.setLocation(295,50);
	    c.add(drivYears);
	    
	    jeepOwner = new JLabel("Jeep Owner");
	    jeepOwner.setSize(80,15);
	    jeepOwner.setLocation(430,50);
	    c.add(jeepOwner);
	    
	    models = new JLabel("Models");
	    models.setSize(50,15);
	    models.setLocation(565,50);
	    c.add(models);
	    
	    transmission = new JLabel("Transmission");
	    transmission.setSize(95,15);
	    transmission.setLocation(700,50);
	    c.add(transmission);

	    driversArray = new ArrayList<CDriver>(); 
		drivers = new DefaultListModel<CDriver>();
		driverList = new JList<CDriver>(drivers);
	    driverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
	    driverList.setFont(new Font("Monospaced", Font.PLAIN, 12));
	    scrollPane = new JScrollPane(driverList);
	    scrollPane.setSize(815,255);
	    scrollPane.setLocation(15, 70);
	    c.add(scrollPane);
	      
		bnAdd = new JButton("Add");
		bnAdd.setSize(115,50);
		bnAdd.setLocation(20,340);
		bnAdd.addActionListener(this);
		c.add(bnAdd);

		bnDel = new JButton("Remove");
		bnDel.setSize(115,50);
		bnDel.setLocation(480,340);
		bnDel.addActionListener(this);
		c.add(bnDel);

		bnEdit = new JButton("Edit");
		bnEdit.setSize(115,50);
		bnEdit.setLocation(250,340);
		bnEdit.addActionListener(this);
		c.add(bnEdit);
		
		bnClear = new JButton("Clear");
		bnClear.setSize(115,50);
		bnClear.setLocation(715,340);
		bnClear.addActionListener(this);
		c.add(bnClear);
		
		bnOpen = new JButton("Open");
		bnOpen.setSize(115, 50);
		bnOpen.setLocation(305, 415);
		bnOpen.addActionListener(this);
		c.add(bnOpen);
		
		bnSave = new JButton("Save");
		bnSave.setSize(115,50);
		bnSave.setLocation(440,415);
		bnSave.addActionListener(this);
		c.add(bnSave);
		
		fileChooser = new JFileChooser();
		
		setSize(845,490);
	    setLocation(100,100);
	    setVisible(true);
   }
    //override such that program opens a file of name filename
    public TestDialog(String filename){
    	this();
    	try{
			input = new ObjectInputStream(new FileInputStream(filename));
		}
		catch (IOException ex){}
		try{
			CDriver driver = (CDriver) input.readObject();
			while(driver != null){
				driversArray.add(driver);
				drivers.addElement(driver);
				driver = (CDriver) input.readObject();
			}
            input.close();
		}
		catch(IOException | ClassNotFoundException ex){}
		driverList.setSelectedIndex(drivers.size()-1);
        driverList.ensureIndexIsVisible(drivers.size()-1);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bnAdd) {
			int drivNum = 1;
			if (driversArray.size() !=0){
				drivNum = driversArray.get(driversArray.size()-1).getCustomerNumber()+1;
			}
			CDriver newDriver = new CDriver(drivNum,"",-1,-1,"-----","");
		    MyDialog dialogWnd = new MyDialog(this, "Add Name", newDriver);
		    if (!dialogWnd.isCancelled()) {
		    	driversArray.add(dialogWnd.getAnswer());
		    	drivers.addElement(dialogWnd.getAnswer());
                driverList.setSelectedIndex(drivers.size()-1);
                driverList.ensureIndexIsVisible(drivers.size()-1);
		    }
		}
	    else if(e.getSource()==bnEdit) {
	    	int index=driverList.getSelectedIndex();
	    	if (index>=0) {
			    MyDialog dialogWnd = new MyDialog(this, "Edit Name", driversArray.get(index));
			    if (!dialogWnd.isCancelled()) {
			    	driversArray.set(index, dialogWnd.getAnswer());
			    	drivers.set(index, dialogWnd.getAnswer());
			    }
	    	}
		}
	    else if(e.getSource()==bnDel) {
	    	int index=driverList.getSelectedIndex();
	    	if (index>=0) {
			    driversArray.remove(index);
			    drivers.remove(index);
			    if (drivers.size()>0) {	// not empty
			    	if (index==drivers.size()) {	// last one deleted
			    		index--;
			    	}
			    	driverList.setSelectedIndex(index);
			    	driverList.ensureIndexIsVisible(index);
			    }
	    	}
		}
	    else if(e.getSource()==bnClear){
    		while (driversArray.size() != 0){ //remove all CDrivers
    			driversArray.remove(0);
    			drivers.remove(0);
    		}
	    }
		//use objectInputStream to read CDriver's from a file chosen in JFileChooser
	    else if(e.getSource()==bnOpen){
	    	int usrChoice = fileChooser.showOpenDialog(this);
	    	if (usrChoice == JFileChooser.APPROVE_OPTION){
	    		try{
	    			input = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
	    		}
	    		catch (IOException ex){}
	    		while (driversArray.size() != 0){ //remove all CDrivers
	    			driversArray.remove(0);
	    			drivers.remove(0);
	    		}
	    		try{
	    			CDriver driver = (CDriver) input.readObject();
	    			while(driver != null){
	    				driversArray.add(driver);
	    				drivers.addElement(driver);
	    				driver = (CDriver) input.readObject();
	    			}
	                input.close();
	    		}
	    		catch(IOException | ClassNotFoundException ex){}
	    		driverList.setSelectedIndex(drivers.size()-1);
                driverList.ensureIndexIsVisible(drivers.size()-1);
	    	}
	    }
		//use objectOutputStream to save CDriver's to file
	    else if(e.getSource()==bnSave){
	    	int usrChoice = fileChooser.showSaveDialog(this);
	    	if (usrChoice == JFileChooser.APPROVE_OPTION){
	    		try{
	    			output = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));	
	    		}	
	    		catch(IOException ex){}
	    		for (CDriver driver:driversArray){
	    			try{
	    				output.writeObject(driver);
	    			}
	    			catch(IOException ex){}
	    		}
	    		try{
	    			output.close();
	    		}
	   			catch(IOException ex){}
	    	}
	    }
    }
    
    public static void main(String[] args) {
    	new TestDialog("output.dat");
    	//new TestDialog();
    }
}
