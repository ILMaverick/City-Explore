package POI;

import java.util.Date;

import USER.User;

public class PointOfInterest {
	public String id;
	public String name;
	public String description;
	public double latitude;
	public double longitude;
	public Date open_time;
	public Date close_time;
	public User author;
	public POIType type;
	public boolean published;
	
	  @Override
	    public String toString() {
	        return "PointOfInterest {" +
	                "\n  id='" + id + '\'' +
	                ",\n  name='" + name + '\'' +
	                ",\n  description='" + description + '\'' +
	                ",\n  latitude=" + latitude +
	                ",\n  longitude=" + longitude +
	                ",\n  open_time=" + open_time +
	                ",\n  close_time=" + close_time +
	                ",\n  author=" + author +
	                ",\n  type=" + type +
	                ",\n  published=" + published +
	                "\n}";
	    }
}
