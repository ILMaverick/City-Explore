package TOUR;

import POI.POIType;
import POI.PointOfInterest;
import USER.User;

public class Tappa extends PointOfInterest{
	private int numeroTappa;

	public Tappa(String name, String description, double lat, double lon, User author, POIType type, int numeroTappa) {
		super(name, description, lat, lon, author, type);
		this.numeroTappa = numeroTappa;
	}

	public int getNumeroTappa() {
		return numeroTappa;
	}

	public void setNumeroTappa(int numeroTappa) {
		this.numeroTappa = numeroTappa;
	}
}
