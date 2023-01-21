package fr.ecp.is1220.myvelib.part1;

import java.util.ArrayList;

/**
 * Default policy for ride planning ; extends RidePlanning according to Strategy design pattern.
 * @author Kellysto
 *
 */

public class DefaultPolicy extends RidePlanning {
	
	public DefaultPolicy(Location startLocation, Location endLocation) {
		super(startLocation, endLocation);
	}

	/**
	 * ridePlanning method, which compute the best starting and ending station for the ride. 
	 * Its constructor register the starting location (the user's location in usual case) and the destination.
	 * It does not return to computed closest stations for the ride, but registers them in startStation and endStation RidePlanning attributes.
	 * @param stations the stations ArrayList, type the bicycle type chosen for the ride
	 */
	@Override
	public void ridePlanning(ArrayList<Station> stations, String type) throws Exception {
		
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
		if (hasFoundStation && startLocation.distanceTo(endLocation) > minDistance) {
			startStation = closestStation;
		}
		else if (!hasFoundStation) {
			throw new Exception("No available starting station found ; try another bicycle type");
		}
		else if (startLocation.distanceTo(endLocation) <= minDistance) {
			throw new Exception("No starting station closest to destination than starting location, better walk");
		}
		
		hasFoundStation = false;
		closestStation = stations.get(0);
		minDistance = closestStation.getLocation().distanceTo(startLocation);
		for (Station station : stations) {
			if (station.isOnline() && station.hasParkingSlotAvailable()) {
				hasFoundStation = true;
				distance = station.getLocation().distanceTo(endLocation);
				if (distance < minDistance) {
					closestStation = station;
					minDistance = distance;
				}
			}
		}
		if (hasFoundStation && startStation != closestStation) {
			endStation = closestStation;
		}
		else if (!hasFoundStation) {
			throw new Exception("No available starting station found ; try another bicycle type");
		}
		else if (startStation == closestStation) {
			throw new Exception("No end station closest to destination than starting station, better walk");
		}
	}
	

}
