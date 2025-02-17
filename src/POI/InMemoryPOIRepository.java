package POI;

import java.util.*;

public class InMemoryPOIRepository implements POIRepository {
    private Map<String, PointOfInterest> storage = new HashMap<>();

    @Override
    public void save(PointOfInterest poi) {
        if (poi != null) {
            storage.put(poi.getId(), poi);
        }
    }

    @Override
    public List<PointOfInterest> findAll() {
        List<PointOfInterest> poiList = new ArrayList<>();
        for (Map.Entry<String, PointOfInterest> entry : storage.entrySet()) {
            PointOfInterest value = entry.getValue();
            poiList.add(value);
        }
        return poiList;
    }

    @Override
    public PointOfInterest findById(String id) {
        return storage.get(id);
    }

}
