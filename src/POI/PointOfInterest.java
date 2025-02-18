package POI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CONTENUTI.MultimediaContent;
import ELEMENT.AbstractElement;
import EVENTO.Event;
import TOUR.Tour;
import USER.User;

public class PointOfInterest extends AbstractElement{
	private double latitude;
	private double longitude;
	private Date open_time;
	private Date close_time;
	private POIType type;
	private final List<MultimediaContent> multimediaContentList;
	public final List<Event> eventList;
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

	public Date getOpen_time() {
	        return open_time;
	    }

	public void setOpen_time(Date open_time) {
	        this.open_time = open_time;
	    }

	public Date getClose_time() {
	        return close_time;
	    }

	public void setClose_time(Date close_time) {
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
