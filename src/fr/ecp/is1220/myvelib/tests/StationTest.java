package fr.ecp.is1220.myvelib.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.Location;
import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.ParkingSlot;
import fr.ecp.is1220.myvelib.part1.PlusStation;
import fr.ecp.is1220.myvelib.part1.StandardStation;
import fr.ecp.is1220.myvelib.part1.Station;

/**
 * 
 * @author Kellysto
 *
 */
class StationTest {

	@Test
	void whenSameIdThenExceptionThrown() {
		Station s1 = new StandardStation(new Location(48.85, 2.3));
		Station s2 = new PlusStation(new Location(48.86, 2.4));
		System.out.println(s1);
		System.out.println(s2);
		assert(s1.getId() != s2.getId());
	}
	
	@Test
	void whenCountingParkingSlotsFreeThenNoExceptionThrown() {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		station.setParkingSlots(new ArrayList<ParkingSlot>());
		try {
			assert(station.countParkingSlotsFree() == 0);
			station = sys.getStations().get(2);
			assert(station.countParkingSlotsFree() == 3);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenCountingBiggerParkingSlotsFreeThenNoExceptionThrown() {
		Location location = new Location();
		Station station = new StandardStation(location);
		ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
		int res = 0;
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("occupied"));
		parkingSlots.add(new ParkingSlot("out of service"));
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("out of service"));
		parkingSlots.add(new ParkingSlot("out of service"));
		parkingSlots.add(new ParkingSlot("occupied"));
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("free")); res++;
		parkingSlots.add(new ParkingSlot("out of service"));
		parkingSlots.add(new ParkingSlot("free")); res++;
		
		station.setParkingSlots(parkingSlots);
		assert(station.countParkingSlotsFree() == res);
	}
	
	@Test
	void whenHasParkingSlotsAvailableThenNoException() {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		
		assert(station.hasParkingSlotAvailable() == true);
	}
	
	@Test
	void whenCountingParkingSlotsOccupiedThenNoExceptionThrown() {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		station.setParkingSlots(new ArrayList<ParkingSlot>());
		try {
			assert(station.countParkingSlotsOccupied("mechanic") == 0);
			station = sys.getStations().get(2);
			assert(station.countParkingSlotsOccupied("electric") == 2);
			assert(station.countParkingSlotsOccupied("mechanic") == 5);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenHasNoParkingSlotsAvailableThenNoExceptionThrown() {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		for (ParkingSlot ps:station.getParkingSlots()) {
			ps.setStatus("occupied");
		}
		assert(station.hasParkingSlotAvailable() == false);
	}
	
	@Test
	void whenHasNoBicycleAvailableThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		int loop = station.getOccupyingBicycles().size();
		for (int i=0; i< loop; i++) {
			station.getOccupyingBicycles().get(0).removeObserver(station);
		}
		assert(station.hasBicycleAvailable() == false);
	}
	
	@Test
	void whenHasBicycleAvailableThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		assert(station.hasBicycleAvailable() == true);
	}
	
	@Test
	void whenSearchingAvailableParkingSlotThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		ParkingSlot ps = station.findParkingSlotFree(station.getParkingSlots());
		assert(ps == station.getParkingSlots().get(7));
	}
	
	@Test
	void whenSearchingOccupiedParkingSlotThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("sys", 10, 100);
		Station station = sys.getStations().get(0);
		ParkingSlot ps = station.findParkingSlotOccupied("electric");
		assert(ps == station.getParkingSlots().get(0));
	}
	
	@Test
	void whenSearchingOccupiedParkingSlotButNoElectricAvailableThenException() throws Exception {
		try {
			MyVelib sys = new MyVelib("sys", 10, 100);
			Station station = sys.getStations().get(0);
			for (ParkingSlot ps:station.getParkingSlots()) {
				if (ps.getStatus() == "occupied") {
					if (ps.getBicycleOccupying().getType() == "electric") {
						ps.getBicycleOccupying().setType("mechanic");
					}
				}
			}
			station.findParkingSlotOccupied("electric");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No electric bicycle found, other type available"));
		}
	}
	
	@Test
	void whenSearchingOccupiedParkingSlotButNoBicycleAvailableThenException() throws Exception {
		try {
			MyVelib sys = new MyVelib("sys", 10, 100);
			Station station = sys.getStations().get(0);
			for (ParkingSlot ps:station.getParkingSlots()) {
				if (ps.getStatus() == "occupied") {
					ps.setStatus("free");
				}
			}
			station.findParkingSlotOccupied("electric");
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No bicycle found"));
		}
	}
	
}
