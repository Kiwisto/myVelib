package fr.ecp.is1220.myvelib.part1;

/**
 * CreditCard class. A credit card has an id and a user, and register the credit balance of this user.
 * @author Kellysto
 *
 */

public class CreditCard {

	private int id;
	private static int uniqueId = 0;
	private double balance = 0;
	private User owner;
	
	public CreditCard(User owner) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.owner = owner;
		owner.setCreditCard(CreditCard.this);
	}
	
	public CreditCard(User owner, double initialBalance) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.owner = owner;
		owner.setCreditCard(CreditCard.this);
		this.balance = initialBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
