package POI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new ArrayList<>(storage.values());
    }

    @Override
    public PointOfInterest findById(String id) {
        return storage.get(id);
    }

}
