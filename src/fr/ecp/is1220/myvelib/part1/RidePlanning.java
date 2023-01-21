package fr.ecp.is1220.myvelib.part1;

import java.util.ArrayList;

/**
 * Ride planning abstract class, defined as in a Strategy design pattern. 
 * It is extended by the different ride planning policies (strategies).
 * A RidePlanning object is tied to a user.
 * @author Kellysto
 *
 */

public abstract class RidePlanning {
	
	protected Station startStation;
	protected Station endStation;
	protected Location startLocation;
	protected Location endLocation;
	
	/**
	 * Constructor needs the starting location (user's location in usual case) and the destination.
	 * @param startLocation the starting location of the planning
	 * @param endLocation the destination of the planning
	 */
	public RidePlanning(Location startLocation, Location endLocation) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
	}

	public abstract void ridePlanning(ArrayList<Station> stations, String type) throws Exception;

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	
	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public String toString() {
		String res = "Ride planning from starting location " + startLocation.toString() + " to " + endLocation.toString() + "\n";
		res += "Start station: " + startStation.getId() + ", at location " + startStation.getLocation().toString() + "\n";
		res += "End station: " + endStation.getId() + ", at location " + endStation.getLocation().toString() + "\n";
		return res;
	}
	
}
