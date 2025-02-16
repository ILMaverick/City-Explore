package POI;

import java.util.List;

import OSM.OverpassElement;
import USER.User;

public class POIService {
    private POIRepository poiRepository;

    public POIService() {
        this.poiRepository = new InMemoryPOIRepository();
    }
    
    public PointOfInterest createPOIFromScratch(String name, String description, double lat, double lon, User author, POIType type) {
        PointOfInterest poi = PointOfInterestFactory.create(name, description, lat, lon, author, type);
        poiRepository.save(poi);
        return poi;
    }
    
    public PointOfInterest createPOIFromOSM(OverpassElement element, User author, POIType type) {
        PointOfInterest poi = PointOfInterestFactory.createFromOverpassElement(element, author, type);
        poiRepository.save(poi);
        return poi;
    }

    public List<PointOfInterest> getAllPOIs() {
        return poiRepository.findAll();
    }

    public PointOfInterest getPOIById(String id) {
        return poiRepository.findById(id);
    }
}
