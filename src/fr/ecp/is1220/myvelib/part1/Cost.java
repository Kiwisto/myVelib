package fr.ecp.is1220.myvelib.part1;

import java.util.Date;

/**
 * Cost interface for Strategy design pattern. There is one strategy per card type, thus one cost method per type.
 * @author Kellysto
 *
 */

public interface Cost {
	
	public double cost(String type, Date rentTime, Date returnTime, RegistrationCard registrationCard) throws Exception;
}