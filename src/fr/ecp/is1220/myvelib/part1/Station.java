package fr.ecp.is1220.myvelib.part1;

import java.util.ArrayList;

/**
 * Station abstract class, implemented by the two types of stations (Standard, Plus).
 * Has multiple parking slots, and keep tracks of the bicycles parked in them,
 * as well as station statistics
 * @author pioll
 *
 */

public abstract class Station implements Observer {
	protected static int uniqueId = 0;
	protected int id;
	protected String type;
	protected boolean online;
	protected Location location;
	protected ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	protected int bicycleCount;
	protected ArrayList<Bicycle> occupyingBicycles = new ArrayList<Bicycle>();
	private int nbRents;
	private int nbReturns;

	public boolean isOnline() {
		return online;
	}
	
	/**
	 * Not useful in the project's current state, see Observer class
	 */
	public void update() {}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
	/**
	 * Return the station status (online or offline) as a string
	 * @return "offline" or "online"
	 */
	public String getStatus() {
		if (online) {return "online";}
		else {return "offline";}
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(ArrayList<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}

	public int getId() {
		return id;
	}
	
	public int getBicycleCount() {
		return bicycleCount;
	}

	public void setBicycleCount(int bicycleCount) {
		this.bicycleCount = bicycleCount;
	}

	public ArrayList<Bicycle> getOccupyingBicycles() {
		return occupyingBicycles;
	}

	public void setOccupyingBicycles(ArrayList<Bicycle> occupyingBicycles) {
		this.occupyingBicycles = occupyingBicycles;
	}

	public int getNbRents() {
		return nbRents;
	}

	public void setNbRents(int nbRents) {
		this.nbRents = nbRents;
	}

	public int getNbReturns() {
		return nbReturns;
	}

	public void setNbReturns(int nbReturns) {
		this.nbReturns = nbReturns;
	}

	public String toString() {
		String res = "";
		res += "Station " + Integer.toString(id) + (online? " - online" : " - offline") + "\n";
		res += "Type: " + type + "\n";
		res += "Latitude: " + Double.toString(location.getLatitude()) + "\n";
		res += "Longitude: " + Double.toString(location.getLongitude()) + "\n";
		res += parkingSlots.size() + " Parking slots, " + bicycleCount + " bicycles parked:" + "\n\n";
		for (ParkingSlot ps:parkingSlots) {
			res += "- " + ps.toString() + "\n";
		}
		return res;
	}
	

	public void incrBCount() {bicycleCount++;}
	
	public void decrBCount() {if (bicycleCount > 0) bicycleCount--;}
	
	/**
	 * 
	 * @param parkingSlots (list of parking slots)
	 * @return Returns the numbers of free parking slots in <code>parkingSlots</code>.
	 */
	
	public boolean hasParkingSlotAvailable() {
		return countParkingSlotsFree() > 0;
	}
	
	/**
	 * Checks if there is an available bicycle of type <code>type</code>.
	 * @param type A String, either "electric" or "mechanic".
	 * @return true if there is at least a bicycle of type <code>type</code> available, false otherwise.
	 */

	public boolean hasBicycleAvailable(String type) {
		return countParkingSlotsOccupied(type) > 0;
	}
	
	/**
	 * Checks if there is an available bicycle, independently of its type.
	 * @return true if there is at least a bicycle available, false otherwise.
	 */
	
	public boolean hasBicycleAvailable() {
		return hasBicycleAvailable("electric") || hasBicycleAvailable("mechanic");
	}
	
	/**
	 * Counts the number of free parking slots in <code>parkingSlots</code>.
	 * @param parkingSlots
	 * @return int
	 */

	public int countParkingSlotsFree() {
		int res = 0;
		for (ParkingSlot p:parkingSlots) {
			if (p.getStatus() == "free")
				res++;
		}
		return res;
	}
	
	/**
	 * Count the number of occupied parking slots with a given bicycle type.
	 * @param type the bicycle type searched
	 * @return int the number of occupied parking slots
	 */
	public int countParkingSlotsOccupied(String type) {
		int res = 0;
		for (ParkingSlot p:parkingSlots) {
			if (p.getStatus() == "occupied" && p.getBicycleOccupying().getType() == type)
				res++;
		}
		return res;
	}
	
	/**
	 * Find if there is a parking slot occupied with the giver bicycle type.
	 * @param type the type of bicycle searched
	 * @return the parking slot occupied by a bicycle of the right type
	 * @throws Exception "No (type) bicycle found, other type available" if there are bicycles available, but of another type only
	 * @throws Exception "No bicycle found" if there is no bicycle available.
	 */
	public ParkingSlot findParkingSlotOccupied(String type) throws Exception {
		boolean bicycleFound = false;
		if (Station.this.getBicycleCount() > 0) {
			for (ParkingSlot p:parkingSlots) {
				if (p.getStatus() == "occupied") {
					bicycleFound = true;
					if (p.getBicycleOccupying().getType() == type) {
						return p;
					}
				}
			}
		}
		if (bicycleFound == true) {
			throw new Exception("No " + type + " bicycle found, other type available");
		}
		else {
			throw new Exception("No bicycle found");
		}
	}
	
	/**
	 * Finds a free parking slot in <code>parkingSlots</code>. Throws an exception if no slot is free.
	 * @param parkingSlots
	 * @return ParkingSlot
	 * @throws Exception
	 */
	
	public ParkingSlot findParkingSlotFree(ArrayList<ParkingSlot> parkingSlots) throws Exception {
		for (ParkingSlot p:parkingSlots) {
			if (p.getStatus() == "free")
				return p;
		}
		throw new Exception("No free parking slot");
	}
	
}
