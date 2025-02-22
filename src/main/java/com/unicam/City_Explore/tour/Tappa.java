package com.unicam.City_Explore.tour;

import com.unicam.City_Explore.poi.POIType;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Tappa extends PointOfInterest{
	private int numeroTappa;
	@ManyToOne
	private PointOfInterest pointOfInterest;
	@ManyToOne
	private Way way;

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
	public PointOfInterest getPointOfInterest() {
		return pointOfInterest;
	}

	public void setPointOfInterest(PointOfInterest pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}

	public Way getWay() {
		return way;
	}

	public void setWay(Way way) {
		this.way = way;
	}
}
