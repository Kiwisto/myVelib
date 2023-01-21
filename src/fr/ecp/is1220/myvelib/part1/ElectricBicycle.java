package fr.ecp.is1220.myvelib.part1;

/**
 * Electric bicycle, extends Bicycle class.
 * @author pioll
 */

public class ElectricBicycle extends Bicycle {

	public ElectricBicycle() {
		super.uniqueId++;
		this.id = super.uniqueId;
		this.type = "electric";
	}

	

}
