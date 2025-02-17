package POI;

import ELEMENT.ElementStatus;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPOIRepository implements POIRepository {
    private final List<PointOfInterest> storage = new ArrayList<>();

    @Override
    public void save(PointOfInterest poi) {
        if (poi != null) {
            poi.setId(storage.size());
            storage.add(poi);
        }
    }
    @Override
    public PointOfInterest findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<PointOfInterest> findAll() {
        return this.storage;
    }

    @Override
    public List<PointOfInterest> findByStatus(ElementStatus status) {
        return this.storage.stream().filter(pointOfInterest -> pointOfInterest.getStatus() == status).collect(Collectors.toList());
    }

}
