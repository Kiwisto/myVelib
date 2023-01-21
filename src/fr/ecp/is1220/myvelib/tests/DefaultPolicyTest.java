package fr.ecp.is1220.myvelib.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.DefaultPolicy;
import fr.ecp.is1220.myvelib.part1.Location;
import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.ParkingSlot;
import fr.ecp.is1220.myvelib.part1.Station;

/**
 * 
 * @author Kellysto
 *
 */

class DefaultPolicyTest {

	
	/**
	 * Station locations are randomly generated in a 10x10 square. 
	 * If ridePlanning works correctly, starting at 0,0 and going to 10,10 should always succeed in finding two appropriate startStation and endStation.
	 * @throws Exception if failed to find two appropriate stations
	 */
	@Test
	void whenPlanningThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		
		Location startLocation = new Location(-5, -5);
		Location endLocation = new Location(15, 15);
		DefaultPolicy planning = new DefaultPolicy(startLocation, endLocation);
		
		try {
			planning.ridePlanning(stations, "mechanic");	
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenCloserToDestinationThanAllStationsThenExceptionThrown() {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		
		Location startLocation = new Location(-10, -10);
		Location endLocation = new Location(-3, -3);
		DefaultPolicy planning = new DefaultPolicy(startLocation, endLocation);
		
		try {
			planning.ridePlanning(stations, "mechanic");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No starting station closest to destination than starting location, better walk"));;
		}
	}
	
	@Test
	void whenNoStationOnlineThenExceptionThrown() {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		for (Station station:stations) {
			station.setOnline(false);
		}
		
		Location startLocation = new Location(-10, -10);
		Location endLocation = new Location(-3, -3);
		DefaultPolicy planning = new DefaultPolicy(startLocation, endLocation);
		
		try {
			planning.ridePlanning(stations, "mechanic");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No available starting station found ; try another bicycle type"));;
		}
	}
	
	@Test
	void whenNoElectricBicycleAvailableThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		// Removing all electric bicycles
		for (Station station:stations) {
			for (ParkingSlot ps:station.getParkingSlots()) {
				if (ps.getStatus() == "occupied") {
					ps.getBicycleOccupying().removeObserver(station);;
				}
			}
		}
		
		Location startLocation = new Location(-10, -10);
		Location endLocation = new Location(-3, -3);
		DefaultPolicy planning = new DefaultPolicy(startLocation, endLocation);
		
		try {
			planning.ridePlanning(stations, "electric");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No available starting station found ; try another bicycle type"));;
		}
	}
	
	@Test
	void whenBestStartingStationEqualsBestEndStationThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		Location newLocation = new Location(-15,0);
		stations.get(0).setLocation(newLocation);
		
		Location startLocation = new Location(-10, 0);
		Location endLocation = new Location(-20, 0);
		DefaultPolicy planning = new DefaultPolicy(startLocation, endLocation);
		
		try {
			planning.ridePlanning(stations, "mechanic");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No end station closest to destination than starting station, better walk"));;
		}
	}
	
	

}