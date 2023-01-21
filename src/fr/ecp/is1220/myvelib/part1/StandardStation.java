package fr.ecp.is1220.myvelib.part1;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Standard station, extends Station class.
 * @author Kellysto
 *
 */

public class StandardStation extends Station {

	public StandardStation(Location location) {
		super.uniqueId++;
		
		this.id = uniqueId;
		this.type = "standard";
		this.online = true;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}
	
	public StandardStation(Location location, boolean online) {
		super.uniqueId++;
		
		this.id = uniqueId;
		this.type = "standard";
		this.online = online;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}
	
	public StandardStation(Location location, boolean online, ArrayList<ParkingSlot> parkingSlots) {
		super.uniqueId++;
		
		this.id = uniqueId;
		this.type = "standard";
		this.online = online;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}

	
}
