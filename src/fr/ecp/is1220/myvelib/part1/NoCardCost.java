package fr.ecp.is1220.myvelib.part1;

import java.util.Date;

/**
 * NoCardCost implements Cost, as in Strategy design pattern. 
 * NoCardCost is the strategy used when the user does not have a card.
 * @author Kellysto
 *
 */

public class NoCardCost implements Cost {

	/**
	 * 
	 * cost method which compute the cost for a user that has no card.
	 * Also updates the user's total amount charged and total time spent riding a bicycle.
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
		User user = registrationCard.getOwner();
		if (type == "mechanic") {
			user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
			res = duration/60 + 1;
			user.setTotalCharges(user.getTotalCharges() + res);
			return (double) res;
		}
		else if (type == "electric") {
			user.setTotalTimeSpent(user.getTotalTimeSpent() + duration);
			res = (duration/60 + 1)*2;
			user.setTotalCharges(user.getTotalCharges() + res);
			return (double) res;
		}
		else {
			throw new Exception("Unknown bicycle type");
		}
	}
	
}
