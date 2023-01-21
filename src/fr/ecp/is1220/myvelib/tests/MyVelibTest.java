package fr.ecp.is1220.myvelib.tests;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.CreditCard;
import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.ParkingSlot;
import fr.ecp.is1220.myvelib.part1.Station;
import fr.ecp.is1220.myvelib.part1.User;
import fr.ecp.is1220.myvelib.part1.VlibreCard;

/**
 * 
 * @author Kellysto
 *
 */

class MyVelibTest {
	
	@Test
	void whenCreatingSystemThenNoExceptionThrown() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User bob = new User("Bob");
			sys.addUser(bob);
			
			User alice = new User("alice");
			sys.addUser(alice);
			
			System.out.println(sys);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenRentingThenNoExceptionThrown() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User alice = new User("Alice");
			sys.addUser(alice);
			
			Station station = sys.getStations().get(0);
			int bicycleCount = station.getBicycleCount();
			
			Date time = new Date();
			sys.rent(alice, station, "electric", time);
			
			assert(alice.getHasRentedBicycle() == true);
			assert(station.getBicycleCount() == bicycleCount - 1);
			assert(alice.getRentedBicycle().getType() == "electric");
			assert(station.getNbRents() == 1);
			assert(alice.getRentStation() == station);
			
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenRentingThenNoExceptionThrown2() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User bob = new User("Bob");
			sys.addUser(bob);
			
			Station station = sys.getStations().get(8);
			int bicycleCount = station.getBicycleCount();
			
			Date time = new Date();
			sys.rent(bob, station, "mechanic", time);
			
			assert(bob.getHasRentedBicycle() == true);
			assert(station.getBicycleCount() == bicycleCount - 1);
			assert(bob.getRentedBicycle().getType() == "mechanic");
			assert(station.getNbRents() == 1);
			assert(bob.getRentStation() == station);
			
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenUserAlreadyRentingTriesRentThenExceptionThrown() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User alice = new User("Alice");
			sys.addUser(alice);
			
			Station station = sys.getStations().get(0);
			
			Date time = new Date();
			sys.rent(alice, station, "electric", time);
			sys.rent(alice, station, "electric", time);
			throw new Exception("test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("User already rented a bicycle"));
		}
	}
	
	@Test
	void whenNoBicycleAvailableAtStationThenExceptionThrown() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User alice = new User("Alice");
			sys.addUser(alice);
			
			Station station = sys.getStations().get(0);
			for (ParkingSlot ps:station.getParkingSlots()) {
				if (ps.getStatus() == "occupied") {
					ps.setStatus("free");
				}
			}
			
			Date time = new Date();
			sys.rent(alice, station, "electric", time);
			throw new Exception("test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No bicycle found"));
		}
	}
	
	@Test
	void whenStationOfflineThenExceptionThrown() throws Exception {
		try {
			MyVelib sys = new MyVelib("name", 10, 100);
			
			User alice = new User("Alice");
			sys.addUser(alice);
			
			Station station = sys.getStations().get(0);
			station.setOnline(false);
			
			Date time = new Date();
			sys.rent(alice, station, "electric", time);
			throw new Exception("test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("Station is offline"));
		}
	}

	@Test
	void whenReturnBicycleThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		
		Station station = sys.getStations().get(0);
		int bicycleCount = station.getBicycleCount();
		Date time = new Date();
		
		User alice = new User("Alice");
		sys.addUser(alice);
		
		try {
			sys.rent(alice, station, "mechanic", time);
			sys.returnRent(alice, station, time);
			assert(alice.getHasRentedBicycle() == false);
			assert(station.getBicycleCount() == bicycleCount);
			assert(alice.getHasRentedBicycle() == false);
			assert(station.getNbReturns() == 1);
			assert(alice.getNbRides() == 1);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void whenReturnsWhenNotRentingThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		
		Station station = sys.getStations().get(0);
		Date time = new Date();
		Date returnTime = new Date(time.getTime() + 1000*60*145);
		
		User alice = new User("Alice");
		sys.addUser(alice);
		
		try {
			sys.rent(alice, station, "electric", time);
			sys.returnRent(alice, station, returnTime);
			sys.returnRent(alice, station, returnTime);
			throw new Exception("test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("User has not rented a bicycle"));
		}
	}
	
	@Test
	void whenReturnsWhenStationIsFullThenExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		
		Station station = sys.getStations().get(0);
		for (ParkingSlot ps:station.getParkingSlots()) {
			ps.setStatus("occupied");
		}
		
		Station rentStation = sys.getStations().get(3);
		
		Date time = new Date();
		Date returnTime = new Date(time.getTime() + 1000*60*145);
		
		User alice = new User("Alice");
		sys.addUser(alice);
		
		try {
			sys.rent(alice, rentStation, "electric", time);
			sys.returnRent(alice, station, returnTime);
			throw new Exception("test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("No parking slot available in station"));
		}
	}

	@Test
	void whenChargingUserThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		
		Station station = sys.getStations().get(0);
		System.out.println(station);
		Date time = new Date();
		Date returnTime = new Date(time.getTime() + 1000*60*75);
		
		User alice = new User("Alice");
		alice.setCreditCard(new CreditCard(alice));
		alice.setRegistrationCard(new VlibreCard(alice, 20));
		sys.addUser(alice);
		
		try {
			sys.rent(alice, station, "electric", time);
			sys.returnRent(alice, station, returnTime);
			assert(alice.getTotalCharges() == 1);
			assert(alice.getTotalTimeSpent() == 75);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
