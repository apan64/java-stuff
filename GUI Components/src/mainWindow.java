/*CS350
 *Project #5
 *Andrew Pan
 *This class defines the main window, from which the user can see add, edit, and 
 *remove driver information from a list, as well as save or open files.
 */
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
@SuppressWarnings("serial")
public class mainWindow extends JFrame  implements ActionListener{
	JLabel label;
	ArrayList<CDriver> lineArray;
	private DefaultListModel lines;
	JList lineList;
	JScrollPane scrollPane;
	JButton bnAdd;
    JButton bnDel;
    JButton bnEdit;
    JButton bnClear;
    JButton bnSave;
    JButton bnOpen;
    int currentnum;
    ObjectOutputStream output;
    ObjectInputStream input;
    JFileChooser files;
    
    public mainWindow()
    {
    	super("Test Drive Requests");
    	Container c = getContentPane();
    	c.setLayout(null);
    	currentnum = 1;
    	label = new JLabel("Customer No.               Customer Name               Driving Years               Jeep Owner               Models               Transmission");
    	label.setSize(800, 50);
    	label.setLocation(20, 20);
    	c.add(label);
    	
    	lineArray = new ArrayList<CDriver>();
    	lines = new DefaultListModel();
    	lineList = new JList(lines);
    	lineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	lineList.setFont(new Font("Monospaced", Font.PLAIN, 12));
    	scrollPane = new JScrollPane(lineList);
    	scrollPane.setSize(900, 500);
    	scrollPane.setLocation(20, 100);
    	c.add(scrollPane);
    	
    	bnAdd = new JButton("Add");
    	bnAdd.setSize(100, 50);
    	bnAdd.setLocation(20, 650);
    	bnAdd.addActionListener(this);
    	c.add(bnAdd);
    	
    	bnDel = new JButton("Delete");
    	bnDel.setSize(100, 50);
    	bnDel.setLocation(170, 650);
    	bnDel.addActionListener(this);
    	c.add(bnDel);
    	
    	bnEdit = new JButton("Edit");
    	bnEdit.setSize(100, 50);
    	bnEdit.setLocation(320, 650);
    	bnEdit.addActionListener(this);
    	c.add(bnEdit);
    	
    	bnClear = new JButton("Clear");
    	bnClear.setSize(100, 50);
    	bnClear.setLocation(470, 650);
    	bnClear.addActionListener(this);
    	c.add(bnClear);
    	
    	bnSave = new JButton("Save");
    	bnSave.setSize(100, 50);
    	bnSave.setLocation(620, 650);
    	bnSave.addActionListener(this);
    	c.add(bnSave);
    	
    	bnOpen = new JButton("Open");
    	bnOpen .setSize(100, 50);
    	bnOpen.setLocation(770, 650);
    	bnOpen.addActionListener(this);
    	c.add(bnOpen);
    	
    	setSize(1000, 900);
    	setLocation(0, 0);
    	setVisible(true);
    }
    public mainWindow(String filename) throws FileNotFoundException
    {
    	this();
    	try{
			input = new ObjectInputStream(new FileInputStream(filename));
		}
		catch (IOException ex){}
		try{
			CDriver driver = (CDriver) input.readObject();
			while(driver != null){
				lineArray.add(driver);
				lines.addElement(driver.line());
				driver = (CDriver) input.readObject();
			}
            input.close();
		}
		catch(IOException | ClassNotFoundException ex){}
		lineList.setSelectedIndex(lines.size()-1);
        lineList.ensureIndexIsVisible(lines.size()-1);
    }
    
    public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bnAdd) {
		    dialog dialogWnd = new dialog(this, "Add a Test Driver", new CDriver(currentnum));
		    if (!dialogWnd.isCancelled()) {
		    	lineArray.add(dialogWnd.getAnswer());
		    	lines.addElement(dialogWnd.getAnswer().line());
                lineList.setSelectedIndex(lines.size()-1);
                lineList.ensureIndexIsVisible(lines.size()-1);
                currentnum++;
		    }
		}
	    else if(e.getSource()==bnEdit) {
	    	int index=lineList.getSelectedIndex();
	    	if (index>=0) {
			    dialog dialogWnd = new dialog(this, "Edit Name", lineArray.get(index));
			    if (!dialogWnd.isCancelled()) {
			    	lineArray.set(index, dialogWnd.getAnswer());
			    	lines.set(index, dialogWnd.getAnswer().line());
			    }
	    	}
		}
	    else if(e.getSource()==bnDel) {
	    	int index=lineList.getSelectedIndex();
	    	if (index>=0) {
			    lineArray.remove(index);
			    lines.remove(index);
			    if (lines.size()>0) {	// not empty
			    	if (index==lines.size()) {	// last one deleted
			    		index--;
			    	}
			    	lineList.setSelectedIndex(index);
			    	lineList.ensureIndexIsVisible(index);
			    }
	    	}
		}
	    else if(e.getSource() == bnClear)
	    {
	    	lineArray = new ArrayList<CDriver>();
	    	lines = new DefaultListModel();
	    	lineList.setModel(lines);
	    }
	    else if(e.getSource() == bnSave)
	    {
	    	int num = files.showSaveDialog(this);
	    	if (num == JFileChooser.APPROVE_OPTION){
	    		try{
	    			output = new ObjectOutputStream(new FileOutputStream(files.getSelectedFile()));	
	    		}	
	    		catch(IOException ex){}
	    		for (CDriver driver:lineArray){
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
	    else if(e.getSource() == bnOpen)
	    {
	    	int num = files.showOpenDialog(this);
	    	if (num == JFileChooser.APPROVE_OPTION){
	    		try{
	    			input = new ObjectInputStream(new FileInputStream(files.getSelectedFile()));
	    		}
	    		catch (IOException ex){}
	    		lineArray = new ArrayList<CDriver>();
		    	lines = new DefaultListModel();
		    	lineList.setModel(lines);
	    		try{
	    			CDriver driver = (CDriver) input.readObject();
	    			while(driver != null){
	    				lineArray.add(driver);
	    				lines.addElement(driver.line());
	    				driver = (CDriver) input.readObject();
	    			}
	                input.close();
	    		}
	    		catch(IOException | ClassNotFoundException ex){}
	    		lineList.setSelectedIndex(lines.size()-1);
                lineList.ensureIndexIsVisible(lines.size()-1);
	    	}
	    }
    }
    public static void main(String[] args) throws FileNotFoundException {

			mainWindow mw = new mainWindow();

    }
}
