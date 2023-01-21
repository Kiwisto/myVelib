package fr.ecp.is1220.myvelib.part1;

/**
 * VlibreCard, extends Registration Card.
 * @author Kellysto
 *
 */

public class VlibreCard extends RegistrationCard {

	public VlibreCard(User owner) {
		super.uniqueId++;
		this.id=uniqueId;
		this.owner = owner;
		owner.setRegistrationCard(VlibreCard.this);
		this.type = "Vlibre";
	}
	
	public VlibreCard(User owner, int initialTimeBalance) {
		super.uniqueId++;
		this.id=uniqueId;
		this.owner = owner;
		owner.setRegistrationCard(VlibreCard.this);
		this.type = "Vlibre";
		this.timeBalance = initialTimeBalance;
	}
	
}
