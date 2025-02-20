package com.unicam.City_Explore.tour;

import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.Entity;

@Entity
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
