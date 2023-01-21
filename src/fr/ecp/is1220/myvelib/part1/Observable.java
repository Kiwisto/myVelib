package fr.ecp.is1220.myvelib.part1;

/**
 * Observable interface, for Observer design pattern. The concrete observable is the Bicycle class.
 * @author Kellysto
 *
 */

public interface Observable {
	public void registerObserver(Station observer) throws Exception;
	public void registerObserver(User observer) throws Exception;
	public void removeObserver(Station observer) throws Exception;
	public void removeObserver(User observer) throws Exception;
}
