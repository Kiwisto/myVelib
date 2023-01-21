package fr.ecp.is1220.myvelib.part1;

import java.util.ArrayList;

/**
 * Plus station, extends Station class.
 * @author Kellysto
 *
 */

public class PlusStation extends Station {

	public PlusStation(Location location) {
		super.uniqueId++;
		this.id = uniqueId;
		this.type = "plus";
		this.online = true;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}
	
	public PlusStation(Location location, boolean online) {
		super.uniqueId++;
		this.id = uniqueId;
		this.type = "plus";
		this.online = online;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}
	
	public PlusStation(Location location, boolean online, ArrayList<ParkingSlot> parkingSlots) {
		super.uniqueId++;
		this.id = uniqueId;
		this.type = "plus";
		this.online = online;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
	}

	
}
