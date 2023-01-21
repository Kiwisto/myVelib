package fr.ecp.is1220.myvelib.part1;

import java.util.Date;

/**
 * Rent class. Registers information about rents : user, time, start and end stations, bicycle used, etc.
 * @author Kellysto
 *
 */

public class Rent {

	private static int uniqueId = 0;
	private int id;
	private User user;
	private Date rentTime;
	private Date returnTime;
	private Station startStation;
	private Station endStation;
	private Bicycle bicycle;
	private double charge;
	
	public Rent(User user, Bicycle bicycle, Station startStation, Station endStation, Date rentTime, Date returnTime, double charge) {
		uniqueId++;
		this.id = uniqueId;
		this.user = user;
		this.rentTime = rentTime;
		this.returnTime = returnTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.bicycle = bicycle;
		this.charge = charge;
	}
	
	public String toString() {
		String res = "Rent " + id+ "\n";
		res += "User:" + user.getName()+ "\n";
		res += "Bicycle:" + bicycle.getId()+ "\n";
		res += "Rent time:" + rentTime+ "\n";
		res += "Return time:" + returnTime+ "\n";
		res += "Start station:" + startStation.getId()+ "\n";
		res += "End station:" + endStation.getId()+ "\n";
		res += "Amount charged:" + charge + "\n";
		return res;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRentTime() {
		return rentTime;
	}

	public void setRentTime(Date rentTime) {
		this.rentTime = rentTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}

	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}
	
	
}
