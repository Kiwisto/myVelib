package fr.ecp.is1220.myvelib.tests;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part1.ParkingSlot;

/**
 * 
 * @author pioll
 *
 */
class ParkingSlotTest {

	@Test
	void whenSameIdThenExceptionThrown() {
		System.out.println("");
		System.out.println("failWhenSameId:");
		ParkingSlot ps1 = new ParkingSlot();
		ParkingSlot ps2 = new ParkingSlot();
		System.out.println(ps1);
		System.out.println(ps2);
		assert(ps1.getId() != ps2.getId());
	}
	
	@Test
	void whenReturnRightStatusThenNoExceptionThrown() {
		System.out.println("");
		System.out.println("returnRightStatus:");
		ParkingSlot ps1 = new ParkingSlot("free");
		ParkingSlot ps2 = new ParkingSlot("occupied");
		ParkingSlot ps3 = new ParkingSlot("OOS");
		System.out.println(ps1);
		System.out.println(ps2);
		System.out.println(ps3);
		assert(ps1.getStatus() == "free");
		assert(ps2.getStatus() == "occupied");
		assert(ps3.getStatus() == "OOS");
	}


}
