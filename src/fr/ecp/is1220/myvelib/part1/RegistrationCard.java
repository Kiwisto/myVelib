package fr.ecp.is1220.myvelib.part1;

/**
 * Registration card abstract class. Implemented by each type of card : Vlibre & Vmax.
 * Has an owner and registers its time balance.
 * @author Kellysto
 *
 */

public abstract class RegistrationCard {

	protected static int uniqueId = 0;
	protected int id;
	protected User owner;
	protected int timeBalance = 0;
	protected String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getTimeBalance() {
		return timeBalance;
	}
	public void setTimeBalance(int timeBalance) {
		this.timeBalance = timeBalance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}