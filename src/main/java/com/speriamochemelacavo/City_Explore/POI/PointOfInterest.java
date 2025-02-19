package com.speriamochemelacavo.City_Explore.POI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContent;
import com.speriamochemelacavo.City_Explore.ELEMENT.AbstractElement;
import com.speriamochemelacavo.City_Explore.EVENTO.Event;
import com.speriamochemelacavo.City_Explore.TOUR.Tour;
import com.speriamochemelacavo.City_Explore.USER.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class PointOfInterest extends AbstractElement{
	private double latitude;
	private double longitude;
	private LocalDate open_time;
	private LocalDate close_time;
	private POIType type;
	@OneToMany
	private final List<MultimediaContent> multimediaContentList;
	@ManyToMany
	public final List<Event> eventList;
	@ManyToMany
	public final List<Tour> tourList;
	
	public PointOfInterest(String name, String description, double lat, double lon, User author, POIType type) {
		super(name, description, author);
		this.latitude = lat;
		this.longitude = lon;
		this.open_time = null;  
		this.close_time = null; 
		this.type = type;
		this.multimediaContentList = new ArrayList<>();
		this.eventList = new ArrayList<>();
		this.tourList = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "PointOfInterest {" +
				"\n  id=" + super.getId() +
				",\n  name=" + super.getName() +
				",\n  description=" + super.getDescription() +
				",\n  latitude=" + latitude +
				",\n  longitude=" + longitude +
				",\n  open_time=" + open_time +
				",\n  close_time=" + close_time +
    			",\n  author=" + super.getAuthor() +
				",\n  type=" + type +
//				",\n  multimediaContentList=" + multimediaContentList +
//				",\n  eventList=" + eventList +
//				",\n  tourList=" + tourList +
				",\n  published=" + super.isPublished() +
				",\n  ElementStatus=" + getStatus() +
				"\n}";
	}

	  // Getter e Setter
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }
	public double getLongitude() {
	        return longitude;
	    }

	public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }

	public LocalDate getOpen_time() {
	        return open_time;
	    }

	public void setOpen_time(LocalDate open_time) {
	        this.open_time = open_time;
	    }

	public LocalDate getClose_time() {
	        return close_time;
	    }

	public void setClose_time(LocalDate close_time) {
	        this.close_time = close_time;
	    }

	public POIType getType() {
	        return type;
	    }

	public void setType(POIType type) {
	        this.type = type;
	    }

	public List<MultimediaContent> getMultimediaContentList() { return this.multimediaContentList; }

	public List<Event> getEvents() {
		return this.eventList;
	}

	public List<Tour> getTourList() { return tourList; }
}
