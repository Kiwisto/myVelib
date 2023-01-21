package fr.ecp.is1220.myvelib.part2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.ecp.is1220.myvelib.part1.*;

/**
 * Class used for the command window.
 * @author pioll
 *
 */

public class CommandWindow {
	
	private static JFrame frame = new JFrame("myVelib");
	private static JTextArea labelOutput = new JTextArea("Output");
	private static JPanel panel = new JPanel();
	private static JLabel labelCommands = new JLabel("Type your commands below.");
	private static JTextArea textArea = new JTextArea("", 5, 20);
	private static MyVelib network = null;
	
	public CommandWindow() {
		super();
	}

	public static void main(String[] args) {
		CommandWindow cw = new CommandWindow();
		cw.displayFrame();
	}
	
	/**
	 * Updates the output part of the window.
	 * @param text text that will be analyzed to get the display
	 */
	
	public void updateLabelOutput(String text) {
		try {
			String s = InputCLUI.checkSyntax(text);
			
			labelOutput.setText(s);
			labelOutput.setForeground(Color.BLACK);
			
			s = analyzeText(text);
			labelOutput.setForeground(Color.BLACK);
			labelOutput.setText(s);
			System.out.println(s);
		}
		catch (Exception e) {
			labelOutput.setText(e.getMessage());
			labelOutput.setForeground(Color.RED);
		}
	}
	
	public void displayFrame() {
		// link the closure of the window to application exit
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setSize(1000, 600); 
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(labelCommands, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.LINE_START;

		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Consolas", Font.BOLD, 12));
		textArea.setCaretColor(Color.WHITE);
		textArea.setMinimumSize(new Dimension(200, 100));
		textArea.setMaximumSize(new Dimension(600, 500));
		textArea.setPreferredSize(new Dimension(500, 400));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		panel.add(textArea, c);
		
		labelOutput.setEditable(false);
		labelOutput.setFont(new Font("Consolas", Font.BOLD, 12));
		labelOutput.setMinimumSize(new Dimension(500, 500));
		labelOutput.setMaximumSize(new Dimension(500, 500));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridheight = 3;
		c.gridy = 0;
		c.weightx = 1.;
		c.weighty = 1.;
		c.anchor = GridBagConstraints.LINE_END;
		
		panel.add(labelOutput, c);
		
		frame.setContentPane(panel);

		
		// display the frame (top-level containers are not visible by default):
		frame.setVisible(true);
		
		
		//if the key Enter is pressed, the command typed is executed
		textArea.addKeyListener(new KeyListener() {

		    @Override
		    public void keyTyped(KeyEvent arg0) {
		    }

		    @Override
		    public void keyReleased(KeyEvent arg0) {
		    }

		    @Override
		    public void keyPressed(KeyEvent arg0) {
		        String currentText = textArea.getText();
		        // only update if the key that got pressed is Enter
		        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		            updateLabelOutput(lastLine(currentText));
		        }

		    }
		});
	}
	
	/**
	 * Returns the last line of a multi-line text.
	 * @param text the text from which the last line is taken
	 * @return Returns the last line of the text. Returns "" for a blank text.
	 */
	
	public String lastLine(String text) {
		String [] lines = text.split("\n");
		if (lines.length == 0) return "";
		return lines[lines.length - 1];
	}
	
	/**
	 * Analyzes the text given: calls all the necessary functions
	 * @param text
	 * @return Returns a String object stating that everything went fine if the operation succeeded.
	 * @throws Exception in case of a problem.
	 * @implNote Does not check the syntax of the command.
	 * @see {@link InputCLUI}
	 */
	
	public String analyzeText(String text) throws Exception {
		
		String correctedText = InputCLUI.removeDoubleSpaces(text);
		if (!text.equals(correctedText)) System.out.println("Command modified. New command:\n" + correctedText);
		String[] words = InputCLUI.removeDoubleSpaces(text).split(" ");
		String command = words[0];
		String velibNetworkName = "";
		int stationId = -1;
		int userId = -1;
		User concernedUser = null;
		Station concernedStation = null;
		
		switch(command) {
		
		case "setup":
			int nbArgs = words.length - 1;
			
			/**
			 * Two syntaxes for the setup command, hence a second switch
			 */
			
			switch(nbArgs) {
			case 1:
				velibNetworkName = words[1];
				network = new MyVelib(velibNetworkName, 10, 100);
				return "Setup command executed.";
			case 5:
				velibNetworkName = words[1];
				int nstations = Integer.parseInt(words[2]);
				int nslots = Integer.parseInt(words[3]);
				double s = Double.parseDouble(words[4]);
				int nbikes = Integer.parseInt(words[5]);
				network = new MyVelib(velibNetworkName, nstations, nstations*nslots, s, nbikes); //à vérifier
				return "Setup command executed.";		
				
			default:
				throw new Exception("Wrong parameter count.");
				
			}
					
		case "addUser":
			
			String userName = words[1];
			String cardType = words[2];
			velibNetworkName = words[3];
			if (velibNetworkName.equals(network.getName())) {
				User u = new User(userName);
				switch(cardType) {
				case "Vlibre":
					u.setRegistrationCard(new VlibreCard(u));
					break;
				case "Vmax":
					u.setRegistrationCard(new VmaxCard(u));
					break;
				case "none":
					break;
				default:
					throw new Exception("Unknown card type: " + cardType);
				}
				network.addUser(u);
			}	
			else throw new Exception("Unknown network name: " + velibNetworkName + ".\nTry " + network.getName() + " instead.");

			return "User command executed.";

		case "offline":
			velibNetworkName = words[1];
			stationId = Integer.parseInt(words[2]);
			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					s.setOnline(false);
					break;
				}
			}

			return "Offline command executed";
			
		case "online":
			velibNetworkName = words[1];
			stationId = Integer.parseInt(words[2]);
			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					s.setOnline(true);
					break;
				}
			}
			return "Online command executed.";
			
		case "rentBike":
		case "rentMechanicalBike":

			userId = Integer.parseInt(words[1]);
			stationId = Integer.parseInt(words[2]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = null;
			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					concernedStation = s;
					break;
				}
			}
			if (concernedStation != null) {
				if (concernedStation.hasBicycleAvailable()) {
					network.rent(concernedUser, concernedStation, "mechanical", new Date());
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "RentMechanicalBike command executed.";
			
		case "rentElectricalBike":

			userId = Integer.parseInt(words[1]);
			stationId = Integer.parseInt(words[2]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = null;
			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					concernedStation = s;
					break;
				}
			}
			if (concernedStation != null) {
				if (concernedStation.hasBicycleAvailable()) {
					network.rent(concernedUser, concernedStation, "electrical", new Date());
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "RentElectricalBike command executed.";
			
		case "rentClosestBike":
		case "rentClosestMechanicalBike":

			userId = Integer.parseInt(words[1]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = getClosestStation(network.getStations(), concernedUser.getLocation(), "mechanic");
			if (concernedStation != null) {
				if (concernedStation.hasBicycleAvailable()) {
					network.rent(concernedUser, concernedStation, "mechanic", new Date());
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "RentMechanicalBike command executed.";
			
		case "rentClosestElectricalBike":

			userId = Integer.parseInt(words[1]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = getClosestStation(network.getStations(), concernedUser.getLocation(), "electric");
			if (concernedStation != null) {
				if (concernedStation.hasBicycleAvailable()) {
					Date d = new Date();
					network.rent(concernedUser, concernedStation, "electric", d);
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "RentElectricalBike command executed.";
			
		case "returnBike":

			userId = Integer.parseInt(words[1]);
			stationId = Integer.parseInt(words[2]);
			double duration = Double.parseDouble(words[3]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = null;
			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					concernedStation = s;
					break;
				}
			}
			if (concernedStation != null) {
				if (concernedStation.hasParkingSlotAvailable()) {
					Date d = new Date();
					Date d2 = new Date(d.getTime() + (int)(1000 * 60 * duration));
					network.returnRent(concernedUser, concernedStation, d2);
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "ReturnBike command executed.";
			
		case "returnClosestBike":

			userId = Integer.parseInt(words[1]);
			double duration2 = Double.parseDouble(words[2]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedStation = getClosestFreeStation(network.getStations(), concernedUser.getLocation());
			if (concernedStation != null) {
				if (concernedStation.hasParkingSlotAvailable()) {
					Date d = new Date();
					Date d2 = new Date(d.getTime() + (int)(1000 * 60 * duration2));
					network.returnRent(concernedUser, concernedStation, d2);
				}
				else throw new Exception("No bicycle available at station " + Integer.toString(stationId));
			}
			else throw new Exception("Unknown station " + Integer.toString(stationId));

			return "ReturnBike command executed.";
			
		case "displayStation":

			velibNetworkName = words[1];
			stationId = Integer.parseInt(words[2]);
			concernedStation = null;

			for (Station s:network.getStations()) {
				if (s.getId() == stationId) {
					concernedStation = s;
					break;
				}
			}

			if (concernedStation == null) throw new Exception("Unknown station " + Integer.toString(stationId));
			return "DisplayStation command executed.\n\n"
					+ concernedStation.toString();
			
		case "displayUser":
			velibNetworkName = words[1];
			userId = Integer.parseInt(words[2]);
			concernedUser = null;
			if (network.equals(null)) throw new Exception("Uninitialized network.");
			if (network.getUsers().size() == 0) throw new Exception("No user in the network.");
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			return "DisplayUser command executed.\n\n"
					+ concernedUser.toString();

			
		case "sortStation":
			velibNetworkName = words[1];
			String sortPolicy = words[2];
			
			switch(sortPolicy) {
			case "usage":
				UsageComparator uc = new UsageComparator();
				Collections.sort(network.getStations(), uc);
				return "SortStation command executed.\n\n" + network.printStations();
				
			case "occupation":
				OccupyingComparator oc = new OccupyingComparator();
				Collections.sort(network.getStations(), oc);
				return "SortStation command executed.\n\n" + network.printStations();
				
			default:
				throw new Exception("The sortPolicy parameter should be usage or occupation.");
			
			}
			
		case "display":
			velibNetworkName = words[1];
			return "Display command executed.\n\n"
					+ network.toString();
		
		case "runtest":
			String fileName = words[1];
			String fn = Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
			File doc = new File(fn);
			Scanner obj = new Scanner(doc);

			while (obj.hasNextLine()) {
				String s = obj.nextLine();
				updateLabelOutput(s); //recursive call
				Thread.sleep(500);
			}

			obj.close();

			return "Running scenario " + fileName;
			
		case "setCoordinates":
			userId = Integer.parseInt(words[1]);
			double x = Double.parseDouble(words[2]);
			double y = Double.parseDouble(words[3]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedUser.setLocation(new Location(x, y));
			return "setCoordinates command executed.";
	
		case "setBalance":
			userId = Integer.parseInt(words[1]);
			double balance = Double.parseDouble(words[2]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedUser.getCreditCard().setBalance(balance);
			return "setBalance command executed.";
			
		case "setTimeBalance":
			userId = Integer.parseInt(words[1]);
			int timeBalance = Integer.parseInt(words[2]);
			concernedUser = null;
			for (User u:network.getUsers()) {
				if (u.getId() == userId) {
					concernedUser = u;
					break;
				}
			}
			if (concernedUser == null) throw new Exception("Unknown user " + Integer.toString(userId));
			concernedUser.getRegistrationCard().setTimeBalance(timeBalance);
			return "setBalance command executed.";
			
		case "loadIni":
			String networkName = words[1];
			loadIni(networkName);
			return "Users loaded";
			
			
			//ajouter les nouvelles commandes au-dessus
			
		}

		
		throw new Exception("Unknown command " + command + ".");
		
	}
	
	/**
	 * Loads an initial file my_velib.ini to add the users
	 */
	public void loadIni(String networkName) throws Exception {
		String fn = Thread.currentThread().getContextClassLoader().getResource("my_velib.ini").getFile();
		File doc2 = new File(fn);
		Scanner obj2 = new Scanner(doc2);
		int id = 0;
		while (obj2.hasNextLine()) {
			id++;
			String s = obj2.nextLine();
			String[] words = s.split(" ");
			updateLabelOutput("addUser " + words[0] + " " +  words[4] + " " + networkName);
			updateLabelOutput("setCoordinates " + Integer.toString(id) + " " + words[1] + " " + words[2]);
			updateLabelOutput("setBalance " + Integer.toString(id) + " " + words[3]);
			updateLabelOutput("setTimeBalance " + Integer.toString(id) + " " + words[5]);
		}

		obj2.close();
	}
	
	/**
	 * Finds the closest online station from <code>startLocation</code> that has at least one bicycle of type <code>type</code>.
	 * @param stations
	 * @param startLocation
	 * @param type
	 * @return The closest online station fulfilling the criteria.
	 * @throws Exception
	 */
	
	public Station getClosestStation(ArrayList<Station> stations, Location startLocation, String type) throws Exception {
		double distance;
		Station closestStation = stations.get(0);
		double minDistance = closestStation.getLocation().distanceTo(startLocation);
		boolean hasFoundStation = false;
		
		for (Station station : stations) {
			if (station.isOnline() && station.hasBicycleAvailable(type)) {
				hasFoundStation = true;
				distance = station.getLocation().distanceTo(startLocation);
				if (distance < minDistance) {
					closestStation = station;
					minDistance = distance;
				}
			}
		}
		if (!hasFoundStation) throw new Exception("No bicycle of type " + type + " available.");
		return closestStation; 
	}
	
	/**
	 * Finds the closest online station from <code>startLocation</code> that has at least one free parking slot.
	 * @param stations
	 * @param startLocation
	 * @return The closest online station fulfilling the criteria.
	 * @throws Exception
	 */
	
	public Station getClosestFreeStation(ArrayList<Station> stations, Location startLocation) throws Exception {
		double distance;
		Station closestStation = stations.get(0);
		double minDistance = closestStation.getLocation().distanceTo(startLocation);
		boolean hasFoundStation = false;
		
		for (Station station : stations) {
			if (station.isOnline() && station.hasParkingSlotAvailable()) {
				hasFoundStation = true;
				distance = station.getLocation().distanceTo(startLocation);
				if (distance < minDistance) {
					closestStation = station;
					minDistance = distance;
				}
			}
		}
		if (!hasFoundStation) throw new Exception("No parking slot available.");
		return closestStation; 
	}
	
}
