package fr.ecp.is1220.myvelib.part1;

/**
 * NoCard, extends Registration Card. Is actually a "fake" Card to make NoCardCost strategy work.
 * @author Kellysto
 *
 */

public class NoCard extends RegistrationCard {

	public NoCard(User owner) {
		this.owner = owner;
		owner.setRegistrationCard(NoCard.this);
	}
	
}
