package fr.ecp.is1220.myvelib.tests;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.Bicycle;
import fr.ecp.is1220.myvelib.part1.ElectricBicycle;
import fr.ecp.is1220.myvelib.part1.MechanicBicycle;
import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.ParkingSlot;
import fr.ecp.is1220.myvelib.part1.Station;
import fr.ecp.is1220.myvelib.part1.User;

import static org.junit.Assert.assertTrue;

import java.util.Date;

/**
 * 
 * @author Kellysto
 *
 */

class BicycleTest {
	
	@Test
	void whenDifferentIdThenNoExceptionThrown() {
		ElectricBicycle b1 = new ElectricBicycle();
		MechanicBicycle b2 = new MechanicBicycle();
		assert(b1.getId() != b2.getId());	
	}
	
	@Test
	void whenAddToStationThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("system", 10, 100);
		Station st = sys.getStations().get(0);
		ParkingSlot ps = st.findParkingSlotFree(st.getParkingSlots());
		int bicycleCount = st.getBicycleCount();
		
		ElectricBicycle b1 = new ElectricBicycle();
		
		try {
			b1.registerObserver(st);
			assert(b1.isHasBelongingStation() == true);
			assert(b1.getBelongingStation() == st);
			assert(b1.isHasParkingSlot() == true);
			assert(b1.getCurrentParkingSlot() == ps);
			
			assert(st.getBicycleCount() == bicycleCount + 1);
			assert(st.getOccupyingBicycles().get(st.getOccupyingBicycles().size() - 1) == b1);
			
			assert(ps.getBicycleOccupying() == b1);
			assert(ps.getStatus().equals("occupied"));
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenAddToUserThenNoExceptionThrown() throws Exception {
		User alice = new User("Alice");
		Bicycle b1 = new ElectricBicycle();
		
		try {
			b1.registerObserver(alice);
			assert(b1.isHasUser() == true);
			assert(b1.getUser() == alice);
			assert(alice.getHasRentedBicycle() == true);
			assert(alice.getRentedBicycle() == b1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	@Test
	void whenLeavingUserThenNoExceptionThrown() throws Exception {
		User alice = new User("Alice");
		Bicycle b1 = new ElectricBicycle();
		
		try {
			b1.registerObserver(alice);
			b1.removeObserver(alice);
			assert(b1.isHasUser() == false);
			assert(b1.getUser() == null);
			assert(alice.getHasRentedBicycle() == false);
			assert(alice.getRentedBicycle() == null);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	@Test
	void whenleavingStationThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("system", 10, 100);
		Station st = sys.getStations().get(0);
		ParkingSlot ps = st.findParkingSlotFree(st.getParkingSlots());
		int bicycleCount = st.getBicycleCount();
		int size = st.getOccupyingBicycles().size();
		
		ElectricBicycle b1 = new ElectricBicycle();
		
		try {
			b1.registerObserver(st);
			b1.removeObserver(st);
			assert(b1.isHasBelongingStation() == false);
			assert(b1.getBelongingStation() == null);
			assert(b1.isHasParkingSlot() == false);
			assert(b1.getCurrentParkingSlot() == null);
			
			assert(st.getBicycleCount() == bicycleCount);
			assert(st.getOccupyingBicycles().size() == size);
			
			assert(ps.getBicycleOccupying() == null);
			assert(ps.getStatus().equals("free"));
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenAddToFullStationthenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("system", 10, 100);
		Station st = sys.getStations().get(0);
		
		ElectricBicycle b1 = new ElectricBicycle();
		ElectricBicycle b2 = new ElectricBicycle();
		ElectricBicycle b3 = new ElectricBicycle();
		ElectricBicycle b4 = new ElectricBicycle();
		
		try {
			b1.registerObserver(st);
			b2.registerObserver(st);
			b3.registerObserver(st);
			b4.registerObserver(st);
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No parking slot available in station"));
		}
	}
	
	@Test
	void whenAddAlreadyRentingUserThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("system", 10, 100);
		Station st = sys.getStations().get(0);
		
		User alice = new User("Alice");
		sys.rent(alice, st, "electric", new Date());
		
		ElectricBicycle b1 = new ElectricBicycle();
		
		try {
			b1.registerObserver(alice);
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("User already rented a bicycle"));
		}
	}
	
	@Test
	void whenUserRentAlreadyRentedBicycleThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("system", 10, 100);
		Station st = sys.getStations().get(0);
		
		User alice = new User("Alice");
		User bob = new User("Bob");
		sys.rent(alice, st, "electric", new Date());
		
		Bicycle b1 = alice.getRentedBicycle();
		
		try {
			b1.registerObserver(bob);
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("Bicycle already rented"));
		}
	}
	
	@Test
	void whenLeavingStationWhenNotInStationThenExceptionThrown() {
		Bicycle b1 = new MechanicBicycle();
		
		try {
			b1.leaveStation();
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("Not in a station"));
		}
	}
	
	@Test
	void whenLeavingUserWhenNotRentedThenExceptionThrown() {
		Bicycle b1 = new MechanicBicycle();
		
		try {
			b1.leaveUser();
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("Not rented"));
		}
	}
	
}
