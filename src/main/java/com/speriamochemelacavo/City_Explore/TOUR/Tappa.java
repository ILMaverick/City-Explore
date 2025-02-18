package com.speriamochemelacavo.City_Explore.TOUR;

import com.speriamochemelacavo.City_Explore.POI.POIType;
import com.speriamochemelacavo.City_Explore.POI.PointOfInterest;
import com.speriamochemelacavo.City_Explore.USER.User;

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
