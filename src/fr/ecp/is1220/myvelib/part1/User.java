package fr.ecp.is1220.myvelib.part1;

import java.util.Date;

/**
 * 
 * @author Kellysto
 *
 */

public class User implements Observer{
	private String name;
	private int id;
	private static int uniqueId = 0;
	private Location location;
	private CreditCard creditCard;
	private boolean hasRegistrationCard = false;
	private RegistrationCard registrationCard = new NoCard(User.this);
	private boolean hasRentedBicycle = false;
	private Bicycle rentedBicycle;
	private Cost cost = new NoCardCost();
	private Date rentTime;
	private Station rentStation;
	private int totalTimeSpent = 0;
	private int nbRides = 0;
	private double totalCharges = 0;
	private int timeCreditEarn = 0;
	private RidePlanning planning;
	
	/**
	 * Creates a new User object.
	 * @param name The name of the user
	 */
	
	public User(String name) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = new CreditCard(User.this);
		this.location = new Location();
	}
	
	/**
	 * Creates a new User object.
	 * @param name The name of the user
	 * @param creditCard The user's credit card.
	 */
	
	public User(String name, CreditCard creditCard) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = creditCard;
		this.location = new Location();
	}
	
	/**
	 * Creates a new User object.
	 * @param name The name of the user
	 * @param creditCard The user's credit card.
	 * @param location The user's location.
	 */
	
	public User(String name, CreditCard creditCard, Location location) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = creditCard;
		this.location = location;
	}
	
	public User(String name, Location location) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = new CreditCard(User.this);
		this.location = location;
	}
	
	public User(String name, RegistrationCard registrationCard, Location location) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = new CreditCard(User.this);
		this.location = location;
		this.registrationCard = registrationCard;
		this.hasRegistrationCard = true;
		if (registrationCard.getType() == "Vlibre") {
			cost = new VlibreCost();
		}
		if (registrationCard.getType() == "Vmax") {
			cost = new VmaxCost();
		}
	}
	
	public User(String name, CreditCard creditCard, RegistrationCard registrationCard) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		this.hasRegistrationCard = true;
		if (registrationCard.getType() == "Vlibre") {
			cost = new VlibreCost();
		}
		if (registrationCard.getType() == "Vmax") {
			cost = new VmaxCost();
		}
	}
	
	public User(String name, CreditCard creditCard, RegistrationCard registrationCard, Location location) {
		super();
		uniqueId += 1;
		this.id = uniqueId;
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		this.hasRegistrationCard = true;
		this.location = location;
		if (registrationCard.getType() == "Vlibre") {
			cost = new VlibreCost();
		}
		if (registrationCard.getType() == "Vmax") {
			cost = new VmaxCost();
		}
	}
	
	public void update() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public RegistrationCard getRegistrationCard() {
		return registrationCard;
		
	}

	public void setRegistrationCard(RegistrationCard registrationCard) {
		this.registrationCard = registrationCard;
		this.hasRegistrationCard = true;
		if (registrationCard.getType() == "Vlibre") {
			cost = new VlibreCost();
		}
		if (registrationCard.getType() == "Vmax") {
			cost = new VmaxCost();
		}
	}
	
	public boolean getHasRegistrationCard() {
		return hasRegistrationCard;
	}

	public void setHasRegistrationCard(boolean hasRegistrationCard) {
		this.hasRegistrationCard = hasRegistrationCard;
	}

	public Bicycle getRentedBicycle() throws Exception{
		return rentedBicycle;
	}

	public void setRentedBicycle(Bicycle rentedBicycle) {
		this.rentedBicycle = rentedBicycle;
	}
	
	public boolean getHasRentedBicycle() {
		return hasRentedBicycle;
	}

	public void setHasRentedBicycle(boolean hasRentedBicycle) {
		this.hasRentedBicycle = hasRentedBicycle;
	}
	
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	public Date getRentTime() {
		return rentTime;
	}

	public void setRentTime(Date rentTime) {
		this.rentTime = rentTime;
	}

	public Station getRentStation() {
		return rentStation;
	}

	public void setRentStation(Station rentStation) {
		this.rentStation = rentStation;
	}

	public int getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(int totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}

	public int getNbRides() {
		return nbRides;
	}

	public void setNbRides(int nbRides) {
		this.nbRides = nbRides;
	}

	public double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public int getTimeCreditEarn() {
		return timeCreditEarn;
	}

	public void setTimeCreditEarn(int timeCreditEarn) {
		this.timeCreditEarn = timeCreditEarn;
	}
	
	public String getStatus() {
		if (hasRentedBicycle) {return "riding bicycle " + rentedBicycle.getId();}
		else {return "Not riding a bicycle";}
	}

	public RidePlanning getPlanning() {
		return planning;
	}

	public void setPlanning(RidePlanning planning) {
		this.planning = planning;
	}

	public String toString() {
		String res = "";
		res += "User name:" + name + "\n";
		res += "User id:" + id + "\n";
		res += "Latitude:" + location.getLatitude() + "\n";
		res += "Longitude:" + location.getLongitude() + "\n";
		if (hasRentedBicycle) {
			res += "Riding bicycle " + rentedBicycle.getId() + "\n";
		}
		else {
			res += "Not riding a bicycle" + "\n";
		}
		res += "Credit card id:" + creditCard.getId() + "\n";
		res += "Credit card balance:" + creditCard.getBalance() + "\n";
		res += "Total charges:" + totalCharges + "\n";
		if (hasRegistrationCard) {
			res += "Registration card id:" + registrationCard.getId() + "\n";
			res += "Registration card type:" + registrationCard.getType() + "\n";
			res += "registration card time balance:" + registrationCard.getTimeBalance() + "\n";
			res += "Total time credit earned:" + timeCreditEarn + "\n";
		}
		else {
			res += "No registration card" + "\n";
		}
		res += "Number of rides:" + nbRides + "\n";
		res += "Total rent time:" + totalTimeSpent + "\n";
		return res;
	}
	
}
