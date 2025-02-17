package POI;

import java.util.*;

public class InMemoryPOIRepository implements POIRepository {
    private List<PointOfInterest> storage = new ArrayList<PointOfInterest>();
    

    @Override
    public void save(PointOfInterest poi) {
        if (poi != null) {
        	poi.setId(storage.size());
            storage.add(poi);
        }
    }

    @Override
    public List<PointOfInterest> findAll() {
        return this.storage;
    }

    @Override
    public PointOfInterest findById(int id) {
        return storage.get(id);
    }

}
