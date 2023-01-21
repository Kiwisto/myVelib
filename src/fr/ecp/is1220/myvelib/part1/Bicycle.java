package fr.ecp.is1220.myvelib.part1;

/**
 * Abstract class for handling bicycles. 
 * Bicycle implements Observable, it can be observed by Users or Stations.
 * A User is an observer of bicycle when they are riding it.
 * A Station is an observer of bicycle when it is parked in this station.
 * @author Kellysto
 * 
 */

public abstract class Bicycle implements Observable {
	
	protected static int uniqueId = 0;
	
	protected int id;
	protected String type;
	protected Station belongingStation;
	boolean hasBelongingStation = false;
	
	protected ParkingSlot currentParkingSlot;
	boolean hasParkingSlot = false;
	
	protected User user;
	boolean hasUser = false;
	
	public String getType() {return type;};
	public int getId(){return id;};
	
	/**
	 * Registers a station observer ; used when a bicycle is parked in the station. Calls addToStation.
	 * @param station the station to be registered
	 */
	@Override
	public void registerObserver(Station observer) throws Exception {
		addToStation(observer);
	}

	/**
	 * Removes a station observer ; used when a bicycle leave the station. Calls leaveStation.
	 * @param station the station to be removed
	 */
	@Override
	public void removeObserver(Station observer) throws Exception{
		leaveStation();
	}
	
	/**
	 * Registers a user observer ; used when an user rent a bicycle. Calls addUser.
	 * @param user the renting user
	 */
	@Override
	public void registerObserver(User observer) throws Exception {
		addUser(observer);
	}
	
	/**
	 * Removes a user observer ; used when an user return the bicycle to a station. Calls leaveUser.
	 * @param user the user to be removed
	 */
	@Override
	public void removeObserver(User observer) throws Exception {
		leaveUser();
	}
	
	/**
	 * Sets up a user as riding this bicycle.
	 * @param user the renting user
	 */
	public void addUser(User observer) throws Exception {
		if (observer.getHasRentedBicycle() == true) {
			throw new Exception("User already rented a bicycle");
		}
		else if (this.hasUser == true) {
			throw new Exception("Bicycle already rented");
		}
		else {
			this.user = observer;
			hasUser = true;
			user.setRentedBicycle(Bicycle.this);
			user.setHasRentedBicycle(true);
		}
	}
	
	/**
	 * Sets up the bicycle's user as not riding this bicycle anymore.
	 */
	public void leaveUser() throws Exception {
		if (hasUser) {
			user.setRentedBicycle(null);
			user.setHasRentedBicycle(false);
			user = null;
			hasUser = false;
		}
		else {
			throw new Exception("Not rented");
		}
		
	}
	
	/**
	 * Sets up the bicycle's station as not having this bicycle parked anymore.
	 */
	public void leaveStation() throws Exception {
		if (hasBelongingStation) {
			unpark();
			belongingStation.getOccupyingBicycles().remove(Bicycle.this);
			belongingStation.decrBCount();
			belongingStation = null;
			hasBelongingStation = false;
		}
		else {
			throw new Exception("Not in a station");
		}
	}
	
	/**
	 * Parks the bicycle in a parking slot : sets its current parking slot to <code>ps</code> and fill <code>ps</code> with this bicycle.
	 * @param ps the parking Slot to park in
	 */
	public void park(ParkingSlot ps) {
		currentParkingSlot = ps;
		ps.fill(Bicycle.this);
		this.hasParkingSlot = true;
	}
	
	/**
	 * Unparks the bicycle from its parking slot.
	 */
	public void unpark() {
		currentParkingSlot.empty();
		currentParkingSlot = null;
		this.hasParkingSlot = false;
	}
	
	/**
	 * Sets up a station as this bicycle's parking station.
	 * @param station the station to park in
	 * @throws Exception when no parking slot is available
	 */
	public void addToStation(Station station) throws Exception {
		
		if (station.hasParkingSlotAvailable()) {
			hasBelongingStation = true;
			belongingStation = station;
			ParkingSlot p = station.findParkingSlotFree(station.getParkingSlots());
			this.park(p);
			belongingStation.getOccupyingBicycles().add(Bicycle.this);
			belongingStation.incrBCount();
		}
		
		else {
			throw new Exception("No parking slot available in station " + station.getId());
		}
	}
	
	public Station getBelongingStation() {
		return belongingStation;
	}
	public ParkingSlot getCurrentParkingSlot() {
		return currentParkingSlot;
	}
	public boolean isHasBelongingStation() {
		return hasBelongingStation;
	}
	public void setHasBelongingStation(boolean hasBelongingStation) {
		this.hasBelongingStation = hasBelongingStation;
	}
	public boolean isHasParkingSlot() {
		return hasParkingSlot;
	}
	public void setHasParkingSlot(boolean hasParkingSlot) {
		this.hasParkingSlot = hasParkingSlot;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isHasUser() {
		return hasUser;
	}
	public void setHasUser(boolean hasUser) {
		this.hasUser = hasUser;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}