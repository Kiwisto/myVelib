package fr.ecp.is1220.myvelib.part1;

/**
 * Observer interface, for Observer design pattern. The concrete observers are User and Station.
 * In the end, the update method was not useful for this project ; but its implementation could serve
 * to add functionalities (the user or station could be informed of the state of the bicycle)
 * @author Kellysto
 *
 */

public interface Observer {
	
	public void update();

}
