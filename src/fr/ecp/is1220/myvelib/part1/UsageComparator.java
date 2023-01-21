package fr.ecp.is1220.myvelib.part1;

import java.util.Comparator;

/**
 * UsageComparator implements Comparator<Station>. Used to sort stations by most used station.
 * @author Kellysto
 *
 */

public class UsageComparator implements Comparator<Station> {

	@Override
	public int compare(Station s1, Station s2) {
		return - s1.getNbRents() - s1.getNbReturns() + s2.getNbRents() + s2.getNbReturns();
	}

}