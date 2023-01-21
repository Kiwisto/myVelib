package fr.ecp.is1220.myvelib.part2;

import fr.ecp.is1220.myvelib.part1.MyVelib;

//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 
 * @author pioll
 *
 */

public class InputCLUI {
	
	/**
	 * Removes the double spaces and the initial and final spaces of a String.
	 * @param text
	 * @return a copy of the initial string without the extra spaces
	 */
	
	public static String removeDoubleSpaces(String text) {
		return text.trim().replaceAll(" +", " ");
	}
	
	/**
	 * Checks the syntax of the text provided as argument.
	 * @param text
	 * @return A String object like "Correct syntax for XXX command" (where XXX denotes the command) if the syntax is correct.
	 * @throws Exception when the syntax is wrong (the exception can then be displayed on the CLUI).
	 * @see {@link CommandWindow.analyzeText}.
	 * @implNote Ids are considered strictly positive.
	 */

	public static String checkSyntax(String text) throws Exception {
		
		String correctedText = removeDoubleSpaces(text);
		if (!text.equals(correctedText)) System.out.println("Command modified. New command:\n" + correctedText);
		String[] words = removeDoubleSpaces(text).split(" ");
		String command = words[0];
		
		switch(command) {
		
		case "setup":
			int nbArgs = words.length - 1;
			
			/**
			 * Two syntaxes for the setup command, hence a second switch
			 */
			
			switch(nbArgs) {
			case 1:
				String velibNetworkName = words[1];	
				return "Correct syntax for setup command.";
			case 5:
				String name = words[1];
				int nstations = Integer.parseInt(words[2]);
				int nslots = Integer.parseInt(words[3]);
				double s = Double.parseDouble(words[4]);
				int nbikes = Integer.parseInt(words[5]);
				return "Correct syntax for setup command.";	
				
			default:
				throw new Exception("Wrong syntax for setup command.\n"
						+ "The correct syntaxes are:\n"
						+ "setup <velibnetworkName>\n"
						+ "setup <name> <nstations> <nslots> <s> <nbikes>.");
				
			}
					
		case "addUser":
			if (words.length == 4) {
				String userName = words[1];
				String cardType = words[2]; //provisoire ?
				String velibNetworkName = words[3];
				if (cardType.equals("none") || cardType.equals("Vlibre") || cardType.equals("Vmax")) {
					return "Correct syntax for addUser command.";
				}
				else throw new Exception("Card type " + cardType + " is unknown.\n"
									   + "The card type should be none, Vlibre or Vmax.");
			
			}
			else throw new Exception("Wrong syntax for addUser command.\n"
								   + "The correct syntax is:\n"
								   + "addUser <userName> <cardType> <velibnetworkName>.");
			
		case "offline":
			if (words.length == 3) {
				String velibNetworkName = words[1];
				try {
					int stationId = Integer.parseInt(words[2]);
					if (stationId > 0) return "Correct syntax for offline command.";
					else throw new Exception("Wrong parameters for offline command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for offline command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for offline command.\n"
								   + "The correct syntax is:\n"
								   + "offline <velibnetworkName> <stationID>.");
			
		case "online":
			if (words.length == 3) {
				String velibNetworkName = words[1];
				try {
					int stationId = Integer.parseInt(words[2]);
					if (stationId > 0) return "Correct syntax for online command.";
					else throw new Exception("Wrong parameters for online command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for online command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for online command.\n"
					   + "The correct syntax is:\n"
					   + "online <velibnetworkName> <stationID>.");
			
		case "rentBike":
		case "rentElectricalBike":
		case "rentMechanicalBike":
			if (words.length == 3) {
				try {
					int userId = Integer.parseInt(words[1]);
					int stationId = Integer.parseInt(words[2]);
					if (userId > 0 && stationId > 0) return "Correct syntax for rentBike command.";
					else throw new Exception("Wrong parameters for rentBike command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for rentBike command.");
					}
					else throw ex;
				}

			}
			else throw new Exception("Wrong syntax for rentBike command.\n"
								   + "The correct syntax is:\n"
								   + "rentBike <userID> <stationID>.");
			
		case "rentClosestBike":
		case "rentClosestElectricalBike":
		case "rentClosestMechanicalBike":
			if (words.length == 2) {
				try {
					int userId = Integer.parseInt(words[1]);
					if (userId > 0) return "Correct syntax for rentClosestBike command.";
					else throw new Exception("Wrong parameters for rentClosestBike command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for rentClosestBike command.");
					}
					else throw ex;
				}

			}
			else throw new Exception("Wrong syntax for rentClosestBike command.\n"
								   + "The correct syntax is:\n"
								   + "rentBike <userID> <stationID>.");
			
		case "returnBike":
			if (words.length == 4) {
				try {
					int userId = Integer.parseInt(words[1]);
					int stationId = Integer.parseInt(words[2]);
					double duration = Double.parseDouble(words[3]);
					if (userId > 0 && stationId > 0 && duration > 0) return "Correct syntax for returnBike command.";
					else throw new Exception("Wrong parameters for returnBike command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for returnBike command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for returnBike command.\n"
								   + "The correct syntax is:\n"
								   + "returnBike <userID> <stationID> <duration>.");
			
		case "returnClosestBike":
			if (words.length == 3) {
				try {
					int userId = Integer.parseInt(words[1]);
					double duration = Double.parseDouble(words[2]);
					if (userId > 0 && duration > 0) return "Correct syntax for returnClosestBike command.";
					else throw new Exception("Wrong parameters for returnClosestBike command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for returnClosestBike command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for returnClosestBike command.\n"
								   + "The correct syntax is:\n"
								   + "returnBike <userID> <stationID> <duration>.");
			
		case "displayStation":
			if (words.length == 3) {
				String velibNetworkName = words[1];
				try {
					int stationId = Integer.parseInt(words[2]);
					if (stationId > 0) return "Correct syntax for displayStation command.";
					else throw new Exception("Wrong parameters for displayStation command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for displayStation command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for displayStation command.\n"
								   + "The correct syntax is:\n"
								   + "displayStation <velibnetworkName> <stationID>.");
			
		case "displayUser":
			if (words.length == 3) {
				String velibNetworkName = words[1];
				try {
					int userId = Integer.parseInt(words[2]);
					if (userId > 0) return "Correct syntax for displayUser command.";
					else throw new Exception("Wrong parameters for displayUser command.");
				} catch(Exception ex) {
					if (ex.toString().contains("java.lang.NumberFormatException")) {
						throw new Exception("Wrong parameters for displayUser command.");
					}
					else throw ex;
				}
			}
			else throw new Exception("Wrong syntax for displayUser command.\n"
								   + "The correct syntax is:\n"
								   + "displayUser <velibnetworkName> <userID>.");
			
		case "sortStation":
			if (words.length == 3) {
				String velibNetworkName = words[1];
				String sortPolicy = words[2];
				return "Correct syntax for sortStation command.";
			}
			else throw new Exception("Wrong syntax for sortStation command.\n"
								   + "The correct syntax is:\n"
								   + "sortStation <velibnetworkName> <sortpolicy>.");
			
		case "display":
			if (words.length == 2) {
				String velibNetworkName = words[1];
				return "Correct syntax for display command.";
			}
			else throw new Exception("Wrong syntax for display command.\n"
								   + "The correct syntax is:\n"
								   + "display <velibnetworkName>.");
		
		case "runtest":
			if (words.length == 2) {
				String fileName = words[1];
				return "Correct syntax for runtest command.";
			}
			else throw new Exception("Wrong syntax for runtest command.\n"
					   + "The correct syntax is:\n"
					   + "runtest <filename>.");
			
		case "setCoordinates":
			if (words.length == 4) {
				int userId = Integer.parseInt(words[1]);
				double x = Double.parseDouble(words[2]);
				double y = Double.parseDouble(words[3]);
				return "Correct syntax for setCoordinates command.";
			}
			else throw new Exception("Wrong syntax for setCoordinates command.\n"
					   + "The correct syntax is:\n"
					   + "setCoordinates <userId> <x> <y>.");
			
		case "setBalance":
			if (words.length == 3) {
				int userId = Integer.parseInt(words[1]);
				double balance = Double.parseDouble(words[2]);
				return "Correct syntax for setBalance command.";
			}
			else throw new Exception("Wrong syntax for setBalance command.\n"
					   + "The correct syntax is:\n"
					   + "setBalance <userId> <balance>.");
			
		case "setTimeBalance":
			if (words.length == 3) {
				int userId = Integer.parseInt(words[1]);
				double timeBalance = Double.parseDouble(words[2]);
				return "Correct syntax for setTimeBalance command.";
			}
			else throw new Exception("Wrong syntax for setTimeBalance command.\n"
					   + "The correct syntax is:\n"
					   + "setBalance <userId> <balance>.");
			
		case "loadIni":
			if (words.length == 2) return "Correct syntax for loadIni command.";
			else throw new Exception("Wrong syntax for loadIni command.");
			
		}
		
		throw new Exception("Unknown command " + command + ".");
		
	}
}
