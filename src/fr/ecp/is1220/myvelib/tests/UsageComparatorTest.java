package fr.ecp.is1220.myvelib.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.Station;
import fr.ecp.is1220.myvelib.part1.UsageComparator;
import fr.ecp.is1220.myvelib.part1.User;

class UsageComparatorTest {

	@Test
	void whenSortingCorrectlyThenNoExceptionThrown() throws Exception {
		MyVelib sys = new MyVelib("name", 10, 100);
		ArrayList<Station> stations = sys.getStations();
		
		Station station = sys.getStations().get(0);
		Date time = new Date();
		Date returnTime = new Date(time.getTime() + 1000*60*145);
		
		User alice = new User("Alice");
		
		try {
			sys.rent(alice, station, "electric", time);
			station = sys.getStations().get(5);
			sys.returnRent(alice, station, returnTime);
			
			sys.rent(alice, station, "electric", time);
			station = sys.getStations().get(1);
			sys.returnRent(alice, station, returnTime);
			station = sys.getStations().get(3);
			
			sys.rent(alice, station, "electric", time);
			station = sys.getStations().get(1);
			sys.returnRent(alice, station, returnTime);
			station = sys.getStations().get(5);
			
			sys.rent(alice, station, "electric", time);
			station = sys.getStations().get(1);
			sys.returnRent(alice, station, returnTime);
			
			sys.rent(alice, station, "electric", time);
			sys.returnRent(alice, station, returnTime);
			
			station = sys.getStations().get(4);
			
			sys.rent(alice, station, "electric", time);
			station = sys.getStations().get(2);
			sys.returnRent(alice, station, returnTime);
			
			UsageComparator uc = new UsageComparator();
			
			Collections.sort(stations, uc);
			
			int usageFactorA = stations.get(0).getNbReturns() + stations.get(0).getNbRents();
			int usageFactorB = stations.get(1).getNbReturns() + stations.get(1).getNbRents();
			assert(usageFactorA >= usageFactorB);
			usageFactorA = stations.get(1).getNbReturns() + stations.get(1).getNbRents();
			usageFactorB = stations.get(2).getNbReturns() + stations.get(2).getNbRents();
			assert(usageFactorA >= usageFactorB);
			usageFactorA = stations.get(2).getNbReturns() + stations.get(2).getNbRents();
			usageFactorB = stations.get(3).getNbReturns() + stations.get(3).getNbRents();
			assert(usageFactorA >= usageFactorB);
			usageFactorA = stations.get(3).getNbReturns() + stations.get(3).getNbRents();
			usageFactorB = stations.get(8).getNbReturns() + stations.get(8).getNbRents();
			assert(usageFactorA >= usageFactorB);
		}
		catch (Exception e) {
			throw e;
		}
	}

}
