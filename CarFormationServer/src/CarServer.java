/*CS350
 Project #6
 Andrew Pan & Chipper Atkins
 This class defines the Server which interacts with the Client
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
public class CarServer extends JFrame{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private JTextArea displayArea;
	private int counter = 1;
	private ArrayList<MyCar> messageO;
	private ArrayList<MyCar> messageD;
	private CarPanel cp;
	public CarServer()
	{
		super("Car Server");
		
		displayArea = new JTextArea();
		JScrollPane q = new JScrollPane(displayArea);
		q.setSize(400, 150);
		q.setLocation(200, 550);
		add(q);
		
		cp = new CarPanel(this);
		cp.setSize(800, 540);
		cp.setLocation(0, 0);
		add(cp);
		
		setSize(800, 700);
		setVisible(true);
	}
	public void runServer()
	{
		try
		{
			server = new ServerSocket(12345, 100);
			while(true)
			{
				try
				{
					waitForConnection();
					getStreams();
					processConnection();
				}
				catch(EOFException eofException)
				{
					displayMessage("\nServer terminated connection");
				}
				finally
				{
					closeConnection();
					counter++;
				}
			}
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	private void waitForConnection() throws IOException
	{
		displayMessage("Waiting for connection\n");
		connection = server.accept();
		displayMessage("Connection " + counter + " received from: " + connection.getInetAddress().getHostName());
	}
	private void getStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		displayMessage("\nGot I/O streams\n");
	}
	private void processConnection() throws IOException
	{
		do
		{
			try
			{
				messageD = (ArrayList<MyCar>) input.readObject();
				cp.setD(messageD);
				displayMessage("\nReceived");
			}
			catch(ClassNotFoundException ex)
			{
				displayMessage("\nUnknown object type received");
			}
		} while(true);
	}
	private void closeConnection()
	{
		displayMessage("\nTerminating connection\n");
		try
		{
			output.close();
			input.close();
			connection.close();
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	public void sendData(ArrayList<MyCar> cars)
	{
		try
		{
			output.writeObject(cars);;
			output.flush();
			displayMessage("\nArrayList sent");
		}
		catch(IOException ioException)
		{
			displayArea.append("\nError writing object");
		}
	}
	private void displayMessage(final String messageToDisplay)
	{
		SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run()
					{
						displayArea.append(messageToDisplay);
					}
				}
			); // end call to SwingUtilities.invokeLater
	}
}
