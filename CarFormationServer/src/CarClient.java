/*CS350
 Project #6
 Andrew Pan & Chipper Atkins
 This class defines the client which interacts with the Server
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
public class CarClient extends JFrame{
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ArrayList<MyCar> messageO;
	private ArrayList<MyCar> messageD;
	private String server;
	private Socket client;
	private CarPanel cp;
	public CarClient(String host)
	{
		super("Car Client");
		server = host;
		
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
	public void runClient()
	{
		try
		{
			connectToServer();
			getStreams();
			processConnection();
		}
		catch(EOFException eofException)
		{
			displayMessage("\nClient terminated connection");
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}
	private void connectToServer() throws IOException
	{
		displayMessage("Attempting connection\n");
		client = new Socket(InetAddress.getByName(server), 12345);
		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}
	private void getStreams() throws IOException
	{
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
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
			catch(ClassNotFoundException classNotFoundException)
			{
				displayMessage("\nUnknown object type received");
			}
		} while(true);
	}
	private void closeConnection()
	{
		displayMessage("\nClosing Connection");
		try
		{
			output.close();
			input.close();
			client.close();
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
			output.writeObject(cars);
			output.flush();
			displayMessage("\nArray List sent");
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
			);// end call to SwingUtilities.invokeLater
	}
}
