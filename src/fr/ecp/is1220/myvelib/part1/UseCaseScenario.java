package fr.ecp.is1220.myvelib.part1;

import java.util.Collections;
import java.util.Date;

/**
 * A use case scenario, similar to the one described in project description.
 * @author Kellysto
 *
 */

public class UseCaseScenario {

	public static void main(String[] args) {
		
		// MyVelib system creation
		int N = 10;
		int M = 100;
		MyVelib sys = new MyVelib("system1", N, M);
		System.out.println(sys);
		
		
		// Adding some users
		User alice = new User("Alice", new Location(6.8, 4.1));
		alice.setCreditCard(new CreditCard(alice));
		alice.setRegistrationCard(new VlibreCard(alice, 20));
		sys.addUser(alice);
		
		User bob = new User("Bob", new Location(6.8, 4.1));
		bob.setCreditCard(new CreditCard(bob, 50));
		bob.setRegistrationCard(new VmaxCard(bob, 30));
		sys.addUser(bob);
		
		User carol = new User("Carol", new Location(1.0, 2.7));
		carol.setRegistrationCard(new VlibreCard(carol));
		sys.addUser(carol);
		
		User daphne = new User("Daphne", new Location(8.4, 9.5));
		carol.setCreditCard(new CreditCard(daphne, 50));
		sys.addUser(daphne);
		
		System.out.println(alice + "\n");
		
		
		// Renting
		Station st = sys.getStations().get(0);
		Station st2 = sys.getStations().get(0);
		try {
			st = sys.findStation(2);
			st2 = sys.findStation(8);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		Date rentTime =  new Date();
		
		try {
			sys.rent(alice, st, "mechanic", rentTime);
		}
		catch (Exception e){
			System.out.println(e);
		}
		
			// Alice returns the bike to a "plus" station after 145 minutes
		Date returnTime = new Date(rentTime.getTime() + 1000*60*145);
		try {
			sys.returnRent(alice, st2, returnTime);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(alice.getTotalCharges() + "\n");
		System.out.println(alice.getTotalTimeSpent() + "\n");
		System.out.println(st.getNbRents() + "\n");
		System.out.println(st2.getNbReturns() + "\n");
		
		
		// Manager checks user status
		try {
			sys.rent(bob, st2, "electric", rentTime);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		int id = 2;
		try {
			User user = sys.findUser(id);
			System.out.println(user);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		// Manager check station status
		id = 9;
		try {
			Station station = sys.findStation(id);
			System.out.println(station);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		// Manager checks system status
		System.out.println(sys);
		
		
		// Ride planning
		Location destination = new Location(1.0, 1.0);
		try {
			daphne.setPlanning(new DefaultPolicy(daphne.getLocation(), destination));
			daphne.getPlanning().ridePlanning(sys.getStations(), "electric");
			System.out.println(daphne.getPlanning().toString());
			
			Station startStation = daphne.getPlanning().getStartStation();
			Station endStation = daphne.getPlanning().getEndStation();
			sys.rent(daphne, startStation, "electric", rentTime);
			sys.returnRent(daphne, endStation, returnTime);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		// Display of rental records, users, stations
		try {
			System.out.println(sys.printRents());
			System.out.println(sys.printUsers());
			System.out.println(sys.printStations());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		// Sorting stations w.r.t. most used station
		UsageComparator uc = new UsageComparator();
		Collections.sort(sys.getStations(), uc);
		try {
			System.out.println(sys.printStations());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Sorting stations w.r.t. least occupied station
		OccupyingComparator oc = new OccupyingComparator();
		Collections.sort(sys.getStations(), oc);
		try {
			System.out.println(sys.printStations());
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}
	
}