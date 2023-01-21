package fr.ecp.is1220.myvelib.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.MyVelib;
import fr.ecp.is1220.myvelib.part1.OccupyingComparator;
import fr.ecp.is1220.myvelib.part1.Station;
import fr.ecp.is1220.myvelib.part1.User;

/**
 * 
 * @author Kellysto
 *
 */

class OccupyingComparatorTest {

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
			
			OccupyingComparator oc = new OccupyingComparator();
			
			Collections.sort(stations, oc);
			
			int occupyingFactorA = stations.get(0).getNbReturns() - stations.get(0).getNbRents();
			int occupyingFactorB = stations.get(1).getNbReturns() - stations.get(1).getNbRents();
			assert(occupyingFactorA >= occupyingFactorB);
			occupyingFactorA = stations.get(1).getNbReturns() - stations.get(1).getNbRents();
			occupyingFactorB = stations.get(2).getNbReturns() - stations.get(2).getNbRents();
			assert(occupyingFactorA >= occupyingFactorB);
			occupyingFactorA = stations.get(2).getNbReturns() - stations.get(2).getNbRents();
			occupyingFactorB = stations.get(3).getNbReturns() - stations.get(3).getNbRents();
			assert(occupyingFactorA >= occupyingFactorB);
			occupyingFactorA = stations.get(3).getNbReturns() - stations.get(3).getNbRents();
			occupyingFactorB = stations.get(8).getNbReturns() - stations.get(8).getNbRents();
			assert(occupyingFactorA >= occupyingFactorB);
		}
		catch (Exception e) {
			throw e;
		}
	}

}
