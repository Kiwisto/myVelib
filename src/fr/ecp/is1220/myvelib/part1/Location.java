package fr.ecp.is1220.myvelib.part1;

import java.lang.Math;

/**
 * Location class: defines a location by its latitude and longitude.
 * @author Kellysto
 *
 */

public class Location {
	
	private double latitude;
	private double longitude;
	
	public Location(double latitude, double longitude) {this.latitude = latitude; this.longitude = longitude;}
	
	public Location() {this.latitude = 0; this.longitude = 0;}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns the distance between to locations
	 * @param location the location whose distance from this.location is computed
	 * @return the distance between the parameter location
	 */
	public double distanceTo(Location location) {
		double res = 0;
		double latitudeDiff = latitude - location.getLatitude();
		double longitudeDiff = longitude - location.getLongitude();
		res = Math.sqrt((Math.pow(latitudeDiff,2) + Math.pow(longitudeDiff,2)));
		return res;
	}
	
	public String toString() {
		return "longitude:" + longitude + ",latitude:" + latitude;
	}
	
}
