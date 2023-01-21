package fr.ecp.is1220.myvelib.part1;

import java.util.Comparator;

/**
 * Occupying comparator implements Comparator<Station>. Used to sort stations by least occupied station.
 * @author Kellysto
 *
 */

public class OccupyingComparator implements Comparator<Station> {

	/**
	 * Compares two stations.
	 * @param s1 station, s2 station to compare s1 with
	 * @return int result of the comparation
	 */
	@Override
	public int compare(Station s1, Station s2) {
		return -(s1.getNbReturns() - s1.getNbRents()) + (s2.getNbReturns() - s2.getNbRents());
	}

}