package POI;

import ELEMENT.ElementStatus;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryPOIRepository implements POIRepository {
    private final List<PointOfInterest> storage = new ArrayList<>();

    @Override
    public void save(PointOfInterest poi) {
        if(poi.getId() == 0) {
            poi.setId(storage.size()+1);
            storage.add(poi);
        } else if (poi.getId() <= storage.size()){
            storage.set(poi.getId()-1, poi);
        }
    }
    @Override
    public PointOfInterest findById(int id) {
        return storage.stream()
                .filter(pointOfInterest -> pointOfInterest.getId() == id)
                .toList()
                .get(0);
    }

    @Override
    public List<PointOfInterest> findAll() {
        return this.storage;
    }

    @Override
    public List<PointOfInterest> findByStatus(ElementStatus status) {
        return this.storage.stream().filter(pointOfInterest -> pointOfInterest.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public List<PointOfInterest> searchByName(String name) {
        return this.storage.stream().filter(pointOfInterest -> pointOfInterest.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<PointOfInterest> searchByDescription(String description) {
        return this.storage.stream().filter(pointOfInterest -> pointOfInterest.getDescription().equals(description)).collect(Collectors.toList());
    }

    @Override
    public void deleteByID(int id) {
        this.storage.removeIf(pointOfInterest -> pointOfInterest.getId() == id);
    }

}
