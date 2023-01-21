package fr.ecp.is1220.myvelib.part1;

import java.util.*;
import java.util.Random;

/**
 * MyVelib class. Main class of the myVelib system, with all main functions of the system :
 * Rent, returnRent, printRents, printStations, printUsers, charge (a user for a ride), etc.
 * @author Kellysto
 *
 */

public class MyVelib {

	protected static int uniqueId = 0;
	
	private int id;
	private ArrayList<Station> stations;
	private ArrayList<User> users;
	private ArrayList<Bicycle> bicycles;
	private ArrayList<RegistrationCard> registrationCards;
	private ArrayList<CreditCard> creditCards;
	private ArrayList<Rent> rents;
	
	private int nbStations = 0;
	private int nbPlus = 0;
	private int nbStandards = 0;
	
	private int nbPSlots = 0;
	
	private int nbBicycles = 0;
	private int nbElectrics = 0;
	private int nbMechanics = 0;
	
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}
	
	/**
	 * NEW
	 * @param s Station to add to the MyVelib.
	 */
	
	public void addStation(Station s) {
		this.stations.add(s);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * NEW
	 * @param u User to add to the MyVelib.
	 */
	
	public void addUser(User u) {
		this.users.add(u);
	}

	public ArrayList<Bicycle> getBicycles() {
		return bicycles;
	}

	public void setBicycles(ArrayList<Bicycle> bicycles) {
		this.bicycles = bicycles;
	}
	
	public void addBicycle(Bicycle b) {
		bicycles.add(b);
	}
	
	public ArrayList<Rent> getRents() {
		return rents;
	}

	public void setRents(ArrayList<Rent> rents) {
		this.rents = rents;
	}

	public ArrayList<RegistrationCard> getCards() {
		return registrationCards;
	}

	public void setCards(ArrayList<RegistrationCard> registrationCards) {
		this.registrationCards = registrationCards;
	}
	
	/**
	 * Constructs a new MyVelib object, with 30% of Plus stations (70% standard), 70% of parking slots
	 * Occupied, 30% of bicycles being electric, etc. 
	 * @param name The name of the network.
	 * @param N The number of stations.
	 * @param M The <u>total</u> number of parking slots.
	 */
	
	public MyVelib(String name, int N, int M) {
		

		uniqueId++;
		setId(uniqueId);
		this.name = name;
		users = new ArrayList<User>();
		rents = new ArrayList<Rent>();
		
		Random rd = new Random();
		double longitude;
		double latitude;
		Location location;
		
		double res = 0.3*N;
		int nbPlus2 = (int) res;
		ArrayList<Station> stations = new ArrayList<Station>();
		Station station;
		
		int parkingSlotsPerStation = M/N;
		int remainingParkingSlots = M%N;
		ArrayList<ParkingSlot> parkingSlots;
		ParkingSlot parkingSlot;
		
		res = 0.7*M;
		int nbBicycle = (int) res;
		int bicyclePerStation = nbBicycle/N;
		int remainingBicycle = nbBicycle%N;
		ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
		Bicycle bicycle;
		
		res = 0.3*nbBicycle;
		int nbElectric = (int) res;
		int electricPerStation = nbElectric/N;
		int remainingElectric = nbElectric%N;
		
		try {
			for (int i=0; i<N-nbPlus2; i++) {
				longitude = rd.nextDouble()*10;
				latitude = rd.nextDouble()*10;
				location = new Location(longitude, latitude);
				station = new StandardStation(location);
				stations.add(station);
				nbStations++;
				nbStandards++;
			}
			for (int l=0; l<nbPlus2; l++) {
				longitude = rd.nextDouble()*10;
				latitude = rd.nextDouble()*10;
				location = new Location(longitude, latitude);
				station = new PlusStation(location);
				stations.add(station);
				nbStations++;
				nbPlus++;
			}
			
			for (Station stat : stations) {
				
				parkingSlots = new ArrayList<ParkingSlot>();
				
				for (int j=0; j<parkingSlotsPerStation; j++) {
					parkingSlot = new ParkingSlot();
					parkingSlots.add(parkingSlot);
					nbPSlots++;
				}
				if (remainingParkingSlots >0) {
					parkingSlot = new ParkingSlot();
					parkingSlots.add(parkingSlot);
					nbPSlots++;
					remainingParkingSlots--;
				}
				stat.setParkingSlots(parkingSlots);
				
				for (int k=0; k<bicyclePerStation; k++) {
					if (k<electricPerStation) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
					}
					else if (remainingElectric >0) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
						remainingElectric--;
					}
					else {
						bicycle = new MechanicBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbMechanics++;
						bicycle.registerObserver(stat);
					}
				}
				if (remainingBicycle > 0) {
					if (remainingElectric >0) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
						remainingElectric--;
					}
					else {
						bicycle = new MechanicBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbMechanics++;
						bicycle.registerObserver(stat);
					}
					remainingBicycle--;
				}
			
			}
		setStations(stations);
		setBicycles(bicycles);
		}
		catch (Exception e) {
			String errorReport = new String();
			errorReport += "Couldn't create myVelib " + MyVelib.this.getId() + ":" + e;
			System.out.println(errorReport);
		}
	}
	
	/**
	 * Constructs a new MyVelib object.
	 * @param name The name of the network.
	 * @param N The number of stations.
	 * @param M The <u>total</u> number of parking slots.
	 * @param squareSize The size of the square containing the stations (in km).
	 * @param nbikes The <u>total</u> number of bikes.
	 * À FINIR
	 */
	
	public MyVelib(String name, int N, int M, double squareSize, int nbikes) {
		

		uniqueId++;
		setId(uniqueId);
		this.name = name;
		
		Random rd = new Random();
		double longitude;
		double latitude;
		Location location;
		
		double res = 0.3*N;
		int nbPlus2 = (int) res;
		ArrayList<Station> stations = new ArrayList<Station>();
		Station station;
		
		int parkingSlotsPerStation = M/N;
		int remainingParkingSlots = M%N;
		ArrayList<ParkingSlot> parkingSlots;
		ParkingSlot parkingSlot;
		
		res = 0.7*M;
		int nbBicycle = (int) res;
		int bicyclePerStation = nbBicycle/N;
		int remainingBicycle = nbBicycle%N;
		ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
		Bicycle bicycle;
		
		res = 0.3*nbBicycle;
		int nbElectric = (int) res;
		int electricPerStation = nbElectric/N;
		int remainingElectric = nbElectric%N;
		
		try {
			for (int i=0; i<N-nbPlus2; i++) {
				longitude = rd.nextDouble()*squareSize;
				latitude = rd.nextDouble()*squareSize;
				location = new Location(longitude, latitude);
				station = new StandardStation(location);
				stations.add(station);
				nbStations++;
				nbStandards++;
			}
			for (int l=0; l<nbPlus2; l++) {
				longitude = rd.nextDouble()*10;
				latitude = rd.nextDouble()*10;
				location = new Location(longitude, latitude);
				station = new PlusStation(location);
				stations.add(station);
				nbStations++;
				nbPlus++;
			}
			
			for (Station stat : stations) {
				
				parkingSlots = new ArrayList<ParkingSlot>();
				
				for (int j=0; j<parkingSlotsPerStation; j++) {
					parkingSlot = new ParkingSlot();
					parkingSlots.add(parkingSlot);
					nbPSlots++;
				}
				if (remainingParkingSlots >0) {
					parkingSlot = new ParkingSlot();
					parkingSlots.add(parkingSlot);
					nbPSlots++;
					remainingParkingSlots--;
				}
				stat.setParkingSlots(parkingSlots);
				
				for (int k=0; k<bicyclePerStation; k++) {
					if (k<electricPerStation) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
					}
					else if (remainingElectric >0) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
						remainingElectric--;
					}
					else {
						bicycle = new MechanicBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbMechanics++;
						bicycle.registerObserver(stat);
					}
				}
				if (remainingBicycle > 0) {
					if (remainingElectric >0) {
						bicycle = new ElectricBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbElectrics++;
						bicycle.registerObserver(stat);
						remainingElectric--;
					}
					else {
						bicycle = new MechanicBicycle();
						bicycles.add(bicycle);
						nbBicycles++;
						nbMechanics++;
						bicycle.registerObserver(stat);
					}
					remainingBicycle--;
				}
			
			}
		setStations(stations);
		setBicycles(bicycles);
		}
		catch (Exception e) {
			String errorReport = new String();
			errorReport += "Couldn't create myVelib " + MyVelib.this.getId() + ":" + e;
			System.out.println(errorReport);
		}
	}
	
	public void rent(User user, Station station, String type, Date time) throws Exception {
		try {
			if (station.isOnline()) {
				ParkingSlot p = station.findParkingSlotOccupied(type);
				Bicycle bicycle = p.getBicycleOccupying();
				bicycle.registerObserver(user);
				bicycle.removeObserver(station);
				user.setRentTime(time);
				user.setRentStation(station);
				station.setNbRents(station.getNbRents() + 1);
			}
			else {
				throw new Exception("Station is offline");
			}
		}
		catch (Exception e) {
			String res = new String();
			res += "User " + user.getName() + " couldn't rent: " + e;
			System.out.println(res);
			throw e;
		}
	}
	
	public void returnRent(User user, Station station, Date time) throws Exception {
		try {
			if (user.getHasRentedBicycle() == false) {
				throw new Exception("User has not rented a bicycle");
			}
			if (time.compareTo(user.getRentTime()) < 0) {
				throw new Exception("Return time is earlier than rent time");
			}
			else {
				Bicycle bicycle = user.getRentedBicycle();
				String type = bicycle.getType();
				bicycle.registerObserver(station);
				bicycle.removeObserver(user);
				if (station.getType() == "plus" && user.getHasRentedBicycle()) {
					user.getRegistrationCard().setTimeBalance(user.getRegistrationCard().getTimeBalance() + 5);
					user.setTimeCreditEarn(user.getTimeCreditEarn() + 5);
				}
				double cost = charge(user, type, user.getRentTime(), time);
				user.setNbRides(user.getNbRides() + 1);
				Rent rent = new Rent(user, bicycle, user.getRentStation(), station, user.getRentTime(), time, cost);
				rents.add(rent);
				user.setRentTime(null);
				user.setRentStation(null);
				station.setNbReturns(station.getNbReturns() + 1);	
			}
		}
		catch (Exception e) {
			String res = new String();
			res += "User " + user.getName() + " couldn't return bicycle: " + e;
			System.out.println(res);
			throw e;
		}
	}
	
	public double charge(User user, String type, Date rentTime, Date returnTime) throws Exception {
		try {
			double cost = user.getCost().cost(type, rentTime, returnTime, user.getRegistrationCard());
			double newBalance = user.getCreditCard().getBalance() - cost;
			user.getCreditCard().setBalance(newBalance);
			return (double) cost;
		}
		catch (Exception e) {
			String res = new String();
			res += "Couldn't charge User " + user.getName() + ": " + e;
			System.out.println(res);
			throw e;
		}
	}
	
	/**
	 * Create and add a bicycle to a station.
	 * @param station the station where the bicycle must be added
	 * @param type the type of the bicycle
	 */
	public void addNewBicycle(Station station, String type) {
		Bicycle bicycle;
		try {
			if (type == "mechanic") {
				bicycle = new MechanicBicycle();
			}
			else if (type == "electric") {
				bicycle = new ElectricBicycle();
			}
			else {
				throw new Exception("Unknown bicycle type");
			}
			bicycle.registerObserver(station);
			bicycles.add(bicycle);
		}
		catch (Exception e) {
			String res = new String();
			res += "Couldn't add new " + type + " bicycle: " + e;
			System.out.println(res);
		}
	}
	
	/**
	 * Finds a user giving its id.
	 * @param id
	 * @return the user associated to this id
	 * @throws Exception "User not found"
	 */
	public User findUser(int id) throws Exception {
		if (users == null || users.size() == 0) {throw new Exception("No user in system");}
		else {
			for (User user : users)
				if (user.getId() == id) {return user;}
			throw new Exception("User not found");
		}
	}
	
	/**
	 * Finds a station giving its id.
	 * @param id
	 * @return the station associated to this id
	 * @throws Exception "Station not found"
	 */
	public Station findStation(int id) throws Exception {
		if (stations == null || stations.size() == 0) {throw new Exception("No station in system");}
		else {
			for (Station station : stations)
				if (station.getId() == id) {return station;}
			throw new Exception("Station not found");
		}
	}
	
	/**
	 * Finds a bicycle giving its id.
	 * @param id
	 * @return the bicycle associated to this id
	 * @throws Exception "Bicycle not found"
	 */
	public Bicycle findBicycle(int id) throws Exception {
		if (bicycles == null || bicycles.size() == 0) {throw new Exception("No bicycle in system");}
		else {
			for (Bicycle bicycle : bicycles)
				if (bicycle.getId() == id) {return bicycle;}
			throw new Exception("Bicycle not found");
		}
	}
	
	public String toString() {
		String res = "";
		res += "myVelib system id:" + Integer.toString(id) + "\n";
		res += "Stations:" + nbStations + "\n";
		res += "Standard stations:" + nbStandards + "\n";
		res += "Plus stations:" + nbPlus + "\n";
		for (Station st:stations) {
			res += "- " + st.getType() + " Station " + st.getId() +": " + st.getStatus() + "\n";
		}
		res += "Parking slots:" + nbPSlots + "\n";
		res += "Bicycles:" + nbBicycles + "\n";
		res += "Mechanic bicycles:" + nbMechanics + "\n";
		res += "Electric bicycles:" + nbElectrics + "\n";
		res += users.size() + " users:" + "\n";
		for (User user:users) {
			res += "- " + user.getName() + " id "+ user.getId() +": " + user.getStatus() + "\n";
		}
		return res;
	}
	
	/**
	 * Print rents record informations
	 * @return
	 * @throws Exception "No rent registered"
	 */
	public String printRents() throws Exception {
		String res = "Rents:" + "\n";
		if (rents != null && rents.size() != 0) {
			for (Rent rent:rents) {
				res += "Rent " + rent.getId() + ", " + rent.getRentTime() + ", " + rent.getUser().getName() + "\n";
			}
		}
		else {
			throw new Exception("No rent registered");
		}
		return res;
	}
	
	/**
	 * Print stations informations
	 * @return
	 * @throws Exception "No station registered"
	 */
	public String printStations() throws Exception {
		String res = "Stations:" + "\n";
		if (stations != null && stations.size() != 0) {
			for (Station st: stations) {
				res += "- " + st.getType() + " Station " + st.getId() +": " + st.getStatus() + "\n";
			}
		}
		else {
			throw new Exception("No rent registered");
		}
		return res;
	}
	
	/**
	 * Print users informations
	 * @return
	 * @throws Exception "No user registered"
	 */
	public String printUsers() throws Exception {
		String res = "Users:" + "\n";
		if (users != null && users.size() != 0) {
			for (User user: users) {
				res += "- " + user.getName() + " id "+ user.getId() +": " + user.getStatus() + "\n";
			}
		}
		else {
			throw new Exception("No rent registered");
		}
		return res;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<RegistrationCard> getRegistrationCards() {
		return registrationCards;
	}

	public ArrayList<CreditCard> getCreditCards() {
		return creditCards;
	}

	public int getNbStations() {
		return nbStations;
	}

	public int getNbPlus() {
		return nbPlus;
	}

	public int getNbStandards() {
		return nbStandards;
	}

	public int getNbPSlots() {
		return nbPSlots;
	}

	public int getNbBicycles() {
		return nbBicycles;
	}

	public int getNbElectrics() {
		return nbElectrics;
	}

	public int getNbMechanics() {
		return nbMechanics;
	}
	
}
