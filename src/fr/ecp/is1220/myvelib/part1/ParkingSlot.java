package fr.ecp.is1220.myvelib.part1;

/**
 * 
 * @author pioll
 *
 */
public class ParkingSlot {
	private int id;
	protected static int uniqueId = 0;
	private String status;
	private Bicycle bicycleOccupying;
	
	/**
	 * Constructs a new empty ParkingSlot.
	 */
	
	public ParkingSlot() {
		super();
		uniqueId++;
		this.id = uniqueId;
		this.status = "free";
	}
	
	/**
	 * Constructs a new ParkingSlot.
	 * @param isEmpty Use true for an empty ParkingSlot, false for an occupied ParkingSlot.
	 */
	
	public ParkingSlot(String status) {
		super();
		uniqueId++;
		this.id = uniqueId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
	
	public String toString() {
		return "Slot " + Integer.toString(id) + ": " + status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBicycleOccupying(Bicycle bicycleOccupying) {
		this.bicycleOccupying = bicycleOccupying;
	}

	public Bicycle getBicycleOccupying() {
		return bicycleOccupying;
	}
	
	public void empty() {
		this.status = "free";
		this.bicycleOccupying = null;
	}
	
	public void fill(Bicycle fillingBicycle) {
		this.status = "occupied";
		this.bicycleOccupying = fillingBicycle;
	}
	
}
