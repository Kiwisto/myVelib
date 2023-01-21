package fr.ecp.is1220.myvelib.part1;

import java.util.Date;
import java.lang.Math;

/**
 * VlibreCost implements Cost, as in Strategy design pattern. VlibreCost is the strategy used when the user holds a Vlibre card.
 * @author Kellysto
 *
 */

public class VlibreCost implements Cost {

	/**
	 * cost method which compute the cost for a holder of a Vlibre card.
	 * Also updates the user's time balance, total amount charged and total time spent riding a bicycle. 
	 * @param type the bicycle type
	 * @param rentTime the rent time
	 * @param returnTime the return time
	 * @param registrationCard the registration card, who registers the time balance
	 * @return cost the amount charged
	 */
	@Override
	public double cost(String type, Date rentTime, Date returnTime, RegistrationCard registrationCard) throws Exception {
		int duration = (int) (returnTime.getTime() - rentTime.getTime())/1000/60;
		if (duration < 0) {
			throw new Exception("Return time before rent time");
		}
		int res;
		int timeBalance = registrationCard.getTimeBalance();
		User user = registrationCard.getOwner();
		res = duration/60;
		if (res > 0) {
			int timeDifference = timeBalance - (duration - 60);
			int newTimeBalance = Math.max(0, timeDifference);
			if (timeDifference >=  0) {
				if (type == "mechanic") {
					user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
					registrationCard.setTimeBalance(newTimeBalance);
					res = 0;
					user.setTotalCharges(user.getTotalCharges() + res);
					return (double) res;
				}
				else if (type == "electric") {
					user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
					registrationCard.setTimeBalance(newTimeBalance);
					res = 1;
					user.setTotalCharges(user.getTotalCharges() + res);
					return (double) res;
				}
				else {
					throw new Exception("Unknown bicycle type");
				}
			}
			else {
				if (type == "mechanic") {
					user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
					registrationCard.setTimeBalance(newTimeBalance);
					res = (-timeDifference)/60 + 1;
					user.setTotalCharges(user.getTotalCharges() + res);
					return (double) res;
				}
				else if (type == "electric") {
					user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
					registrationCard.setTimeBalance(newTimeBalance);
					res = ((-timeDifference)/60 + 1)*2 + 1;
					user.setTotalCharges(user.getTotalCharges() + res);
					return (double) res;
				}
				else {
					throw new Exception("Unknown bicycle type");
				}
			}
		}
		else {
			if (type == "mechanic") {
				user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
				res = 0;
				user.setTotalCharges(user.getTotalCharges() + res);
				return (double) res;
			}
			else if (type == "electric") {
				user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
				res = 1;
				user.setTotalCharges(user.getTotalCharges() + res);
				return (double) res;
			}
			else {
				throw new Exception("Unknown bicycle type");
			}
		}
	}
}
