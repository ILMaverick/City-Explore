package com.unicam.City_Explore.poi;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.user.User;

@Component
public class PointOfInterestFactory {
    public PointOfInterest createFromOverpassElement(OverpassElement element, User author, POIType type) {
        if (element == null) {
            throw new IllegalArgumentException("L'elemento OverpassElement non puo' essere nullo.");
        }

        return create(element.tags.name, "POI generato da OSM", element.lat, element.lon, author, type);
    }
    
    public PointOfInterest create(String name, String description, double lat, double lon, User author, POIType type) {
    	return new PointOfInterest(name, description, lat, lon, author, type);
    }
}
