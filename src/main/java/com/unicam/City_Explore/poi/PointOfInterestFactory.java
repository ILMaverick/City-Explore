package com.unicam.City_Explore.poi;

import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.user.User;

import java.time.LocalTime;

public class PointOfInterestFactory {
    public static PointOfInterest createFromOverpassElement(OverpassElement element, User author, POIType type) {
        if (element == null) {
            throw new IllegalArgumentException("L'elemento OverpassElement non può essere nullo.");
        }

        return create(element.tags.name, "POI generato da OSM", element.lat, element.lon, author, type);
    }
    
    public static PointOfInterest create(String name, String description, double lat, double lon, User author, POIType type) {
    	return new PointOfInterest(name, description, lat, lon, author, type);
    }
}
