package fr.ecp.is1220.myvelib.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.ecp.is1220.myvelib.part2.InputCLUI;

class InputCLUITest {

	
	@Test
	void testRemoveDoubleSpaces() {
		String test = " a  gfgd  sdfsdf ";
		assert(InputCLUI.removeDoubleSpaces(test).equals("a gfgd sdfsdf"));
	}
	
	@Test
	void testCmdSetup1ArgNoError() {
		String res;
		try {
			res = InputCLUI.checkSyntax("setup Mon_beau_réseau");
			assert(res.equals("Correct syntax for setup command."));
		} catch (Exception e) {
			fail(e);
		}
		
	}
	
	@Test
	void testCmdSetup1ArgContent() throws Exception {
		String res = InputCLUI.checkSyntax("setup Mon_beau_réseau");
		assert(res.equals("Correct syntax for setup command."));
	}
	
	@Test
	void testCmdSetup5ArgNoError() throws Exception {
		String res = InputCLUI.checkSyntax("setup Mon_beau_réseau 11 11 5 70");
		assert(res.equals("Correct syntax for setup command."));
	}
	
	@Test
	void testCmdSetup5ArgContent() throws Exception {
		String res = InputCLUI.checkSyntax("setup Mon_beau_réseau 11 11 5 70");
		assert(res.equals("Correct syntax for setup command."));
	}
	
	@Test
	void testCmdSetup5ArgDoubleSpaceNoError() throws Exception {
		String res = InputCLUI.checkSyntax("setup Mon_beau_réseau 11 11  5 70");
		assert(res.equals("Correct syntax for setup command."));
	}
	
	@Test
	void testCmdSetup5ArgDoubleSpaceContent() throws Exception {
		String res = InputCLUI.checkSyntax("setup Mon_beau_réseau 11 11  5 70");
		assert(res.equals("Correct syntax for setup command."));
	}
	
	@Test
	void testCmdSetupOdd() throws Exception {
		try {
			String res = InputCLUI.checkSyntax("setup Mon_beau_réseau 11 11");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for setup command"));
		}
		
	}
	
	@Test
	void testAddUserNoError() throws Exception {
		try {
			String res = InputCLUI.checkSyntax("addUser Alice none network");
			assert(res.equals("Correct syntax for addUser command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testAddUserWrongCardType() {
		try {
			String res = InputCLUI.checkSyntax("addUser Alice CARTE network");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("The card type should be"));
		}
	}
	
	@Test
	void testAddUserTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("addUser Alice CARTE");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for addUser command"));
		}
	}
	
	@Test
	void testAddUserTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("addUser Alice CARTE network 10");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for addUser command"));
		}
	}
	
	//offline
	
	@Test
	void testOfflineNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("offline network 1");
			assert(res.equals("Correct syntax for offline command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testOfflineTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("offline 1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for offline command"));
		}
	}
	
	@Test
	void testOfflineTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("offline network 1 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for offline command"));
		}
	}
	
	@Test
	void testOfflineNonPositiveId() {
		try {
			String res = InputCLUI.checkSyntax("offline network -1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for offline command"));
		}
	}
	
	@Test
	void testOfflineNonDigitalId() {
		try {
			String res = InputCLUI.checkSyntax("offline network one");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for offline command"));
		}
	}
	
	@Test
	void testOfflineDecimalId() {
		try {
			String res = InputCLUI.checkSyntax("offline network 1.5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for offline command"));
		}
	}
	
	//online
	
	@Test
	void testOnlineNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("online network 1");
			assert(res.equals("Correct syntax for online command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testOnlineTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("online 1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for online command"));
		}
	}
	
	@Test
	void testOnlineTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("online network 1 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for online command"));
		}
	}
	
	@Test
	void testOnlineNonPositiveId() {
		try {
			String res = InputCLUI.checkSyntax("online network -1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for online command"));
		}
	}
	
	@Test
	void testOnlineNonDigitalId() {
		try {
			String res = InputCLUI.checkSyntax("online network one");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for online command"));
		}
	}
	
	@Test
	void testOnlineDecimalId() {
		try {
			String res = InputCLUI.checkSyntax("online network 1.5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for online command"));
		}
	}
	
	//rentBike
	
	@Test
	void testRentBikeNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("rentBike 3 5");
			assert(res.equals("Correct syntax for rentBike command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testRentBikeTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3 5 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeNonPositiveStationId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike -3 5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeNonDigitalStationId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike three 5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeDecimalStationId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3.1 5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeNonPositiveUserId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3 -5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeNonDigitalUserId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3 five");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	@Test
	void testRentBikeDecimalUserId() {
		try {
			String res = InputCLUI.checkSyntax("rentBike 3 5.2");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for rentBike command"));
		}
	}
	
	//returnBike
	
	@Test
	void testReturnBikeNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5 15");
			assert(res.equals("Correct syntax for returnBike command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testReturnBikeDecimalDurationNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5 15.2");
			assert(res.equals("Correct syntax for returnBike command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testReturnBikeTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5 15 Alice");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonPositiveStationId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike -3 5 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonDigitalStationId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike three 5 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeDecimalStationId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3.1 5 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonPositiveUserId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 -5 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonDigitalUserId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 five 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeDecimalUserId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5.2 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonPositiveDurationId() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 5 -15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	@Test
	void testReturnBikeNonDecimalDuration() {
		try {
			String res = InputCLUI.checkSyntax("returnBike 3 15 fifteen");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for returnBike command"));
		}
	}
	
	//displayStation
	
	@Test
	void testDisplayStationNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("displayStation network 1");
			assert(res.equals("Correct syntax for displayStation command."));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testDisplayStationTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("displayStation 1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for displayStation command"));
		}
	}
	
	@Test
	void testDisplayStationTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("displayStation network 1 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for displayStation command"));
		}
	}
	
	@Test
	void testDisplayStationNonPositiveId() {
		try {
			String res = InputCLUI.checkSyntax("displayStation network -1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayStation command"));
		}
	}
	
	@Test
	void testDisplayStationNonDigitalId() {
		try {
			String res = InputCLUI.checkSyntax("displayStation network one");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayStation command"));
		}
	}
	
	@Test
	void testDisplayStationDecimalId() {
		try {
			String res = InputCLUI.checkSyntax("displayStation network 1.5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayStation command"));
		}
	}
	
	//displayUser
	
	@Test
	void testDisplayUserNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("displayUser network 1");
			assert(res.equals("Correct syntax for displayUser command."));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testDisplayUserTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("displayUser 1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for displayUser command"));
		}
	}

	@Test
	void testDisplayUserTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("displayUser network 1 15");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for displayUser command"));
		}
	}

	@Test
	void testDisplayUserNonPositiveId() {
		try {
			String res = InputCLUI.checkSyntax("displayUser network -1");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayUser command"));
		}
	}

	@Test
	void testDisplayUserNonDigitalId() {
		try {
			String res = InputCLUI.checkSyntax("displayUser network one");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayUser command"));
		}
	}

	@Test
	void testDisplayUserDecimalId() {
		try {
			String res = InputCLUI.checkSyntax("displayUser network 1.5");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong parameters for displayUser command"));
		}
	}
	
	// sortStation
	
	@Test
	void testSortStationNoError() {
		// The existence of the station is not checked here but in CommandWindow.java.
		// Here we only focus on the syntax.
		try {
			String res = InputCLUI.checkSyntax("sortStation network ???");
			assert(res.equals("Correct syntax for sortStation command."));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testSortStationTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("sortStation ???");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for sortStation command"));
		}
	}

	@Test
	void testSortStationTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("sortStation network ??? Alice");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for sortStation command"));
		}
	}
	
	// display
	
	@Test
	void testDisplayNoError() {
		try {
			String res = InputCLUI.checkSyntax("display network");
			assert(res.equals("Correct syntax for display command."));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testDisplayTooFewArgs() {
		try {
			String res = InputCLUI.checkSyntax("display");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for display command"));
		}
	}

	@Test
	void testDisplayTooManyArgs() {
		try {
			String res = InputCLUI.checkSyntax("display network Alice");
			fail("No error thrown");
		} catch(Exception e) {
			assert(e.getMessage().contains("Wrong syntax for display command"));
		}
	}
	
	// runtest
	
		@Test
		void testRuntestNoError() {
			try {
				String res = InputCLUI.checkSyntax("runtest network");
				assert(res.equals("Correct syntax for runtest command."));
			} catch (Exception e) {
				fail(e);
			}
		}

		@Test
		void testRuntestTooFewArgs() {
			try {
				String res = InputCLUI.checkSyntax("runtest");
				fail("No error thrown");
			} catch(Exception e) {
				assert(e.getMessage().contains("Wrong syntax for runtest command"));
			}
		}

		@Test
		void testRuntestTooManyArgs() {
			try {
				String res = InputCLUI.checkSyntax("runtest network Alice");
				fail("No error thrown");
			} catch(Exception e) {
				assert(e.getMessage().contains("Wrong syntax for runtest command"));
			}
		}

}
