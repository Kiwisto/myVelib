package fr.ecp.is1220.myvelib.tests;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.CreditCard;
import fr.ecp.is1220.myvelib.part1.User;
import fr.ecp.is1220.myvelib.part1.VmaxCard;

class VmaxCostTest {

	/**
	 * Checks cost, time balance update, total time spent update and total charges update
	 */
	@Test
	void whenComputingCostThenNoExceptionThrown() {
		double cost;
		User alice = new User("Alice");
		alice.setCreditCard(new CreditCard(alice));
		alice.setRegistrationCard(new VmaxCard(alice, 20));
		Date rentTime = new Date();
		Date returnTime = new Date(rentTime.getTime() + 1000*60*75);
		
		Date rentTime2 = new Date();
		Date returnTime2 = new Date(rentTime2.getTime() + 1000*60*145);
		
		Date rentTime3 = new Date();
		Date returnTime3 = new Date(rentTime2.getTime() + 1000*60*135);
		
		try {
			cost = alice.getCost().cost("electric", rentTime, returnTime, alice.getRegistrationCard());
			assert(cost == (double) 0);
			assert(alice.getRegistrationCard().getTimeBalance() == 5);
			assert(alice.getTotalTimeSpent() == 75);
			cost = alice.getCost().cost("mechanic", rentTime2, returnTime2, alice.getRegistrationCard());
			assert(cost == (double) 2);
			assert(alice.getTotalCharges() == 0 + 2);
			assert(alice.getRegistrationCard().getTimeBalance() == 0);
			assert(alice.getTotalTimeSpent() == 75 + 145);
			cost = alice.getCost().cost("electric", rentTime3, returnTime3, alice.getRegistrationCard());
			assert(cost == (double) 2);
			assert(alice.getRegistrationCard().getTimeBalance() == 0);
			assert(alice.getTotalCharges() == 0 + 2 + 2);
			assert(alice.getTotalTimeSpent() == 75 + 145 + 135);
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	void whenReturnTimeBeforeRentTimeThenExceptionThrown() {
		User alice = new User("Alice");
		alice.setCreditCard(new CreditCard(alice));
		alice.setRegistrationCard(new VmaxCard(alice, 20));
		Date rentTime = new Date();
		Date returnTime = new Date(rentTime.getTime() - 1000*60*75);
		
		try {
			alice.getCost().cost("electric", rentTime, returnTime, alice.getRegistrationCard());
			throw new Exception("Test fail");
		}
		catch (Exception e) {
			assertTrue(e.getMessage().contains("Return time before rent time"));
		}
	}

}
