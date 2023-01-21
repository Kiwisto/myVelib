package fr.ecp.is1220.myvelib.part1;

/**
 * Electric bicycle, extends Bicycle class.
 * @author pioll
 */

public class MechanicBicycle extends Bicycle {
	
	public MechanicBicycle() {
		super.uniqueId++;
		this.id = super.uniqueId;
		this.type = "mechanic";
	}

}
