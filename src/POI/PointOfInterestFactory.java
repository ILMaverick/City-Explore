package POI;

import OSM.OverpassElement;
import USER.User;

public class PointOfInterestFactory {
    public static PointOfInterest createFromOverpassElement(OverpassElement element, User author, POIType type) {
        if (element == null) {
            throw new IllegalArgumentException("L'elemento OverpassElement non può essere nullo.");
        }

        PointOfInterest poi = new PointOfInterest();
        poi.id = String.valueOf(element.id);
        poi.name = (element.tags != null && element.tags.name != null) ? element.tags.name : "Senza nome";
        poi.latitude = element.lat;
        poi.longitude = element.lon;
        poi.description = "POI generato da OSM"; 
        poi.open_time = null;  
        poi.close_time = null; 
        poi.author = author;
        poi.type = type;
        poi.published = false; 

        return poi;
    }
}
