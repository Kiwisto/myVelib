package fr.ecp.is1220.myvelib.part1;

/**
 * VmaxCard, extends Registration Card.
 * @author Kellysto
 *
 */

public class VmaxCard extends RegistrationCard {

	public VmaxCard(User owner) {
		super.uniqueId++;
		this.id=uniqueId;
		this.owner = owner;
		owner.setRegistrationCard(VmaxCard.this);
		this.type = "Vmax";
	}
	
	public VmaxCard(User owner, int initialTimeBalance) {
		super.uniqueId++;
		this.id=uniqueId;
		this.owner = owner;
		owner.setRegistrationCard(VmaxCard.this);
		this.type = "Vmax";
		this.timeBalance = initialTimeBalance;
	}
	
}