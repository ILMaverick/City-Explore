package com.unicam.City_Explore.poi;

import java.util.ArrayList;
import java.util.List;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.tour.Tappa;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class PointOfInterest extends AbstractElement{
	private double latitude;
	private double longitude;
	//private LocalTime open_time;
	//private LocalTime close_time;
	private POIType type;
	@ManyToMany(fetch = FetchType.EAGER)
	private final List<Event> eventList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER)
	private final List<Tappa> tappe = new ArrayList<>();
	
	public PointOfInterest() {
		
	}
	
	public PointOfInterest(String name, String description, double lat, double lon, User author, POIType type) {
		super(name, description, author);
		this.latitude = lat;
		this.longitude = lon;
		//this.open_time = null;
		//this.close_time = null;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "PointOfInterest {" +
				"\n  id=" + super.getId() +
				",\n  name=" + super.getName() +
				",\n  description=" + super.getDescription() +
				",\n  latitude=" + latitude +
				",\n  longitude=" + longitude +
				//",\n  open_time=" + open_time +
				//",\n  close_time=" + close_time +
    			",\n  author=" + super.getAuthor().getUsername() +
				",\n  type=" + type +
//				",\n  multimediaContentList=" + multimediaContentList +
//				",\n  eventList=" + eventList +
//				",\n  tourList=" + tourList +
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

		/*
		 * public LocalTime getOpen_time() { return open_time; }
		 * 
		 * public void setOpen_time(LocalTime open_time) { this.open_time = open_time; }
		 * 
		 * public LocalTime getClose_time() { return close_time; }
		 * 
		 * public void setClose_time(LocalTime close_time) { this.close_time =
		 * close_time; }
		 */

	public POIType getType() {
	        return type;
	    }

	public void setType(POIType type) {
	        this.type = type;
	    }

	public List<MultimediaContent> getContents(){
		return super.getMultimediaContentList();
	}

	public List<Event> getEvents() {
		return this.eventList;
	}

	public List<Tappa> getTappe() { return tappe; }
}
