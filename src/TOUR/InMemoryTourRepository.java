package TOUR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTourRepository implements TourRepository{
	private Map<String, Tour> storage = new HashMap<>();

    @Override
    public void save(Tour tour) {
        if (tour != null) {
            storage.put(tour.getId(), tour);
        }
    }

    @Override
    public List<Tour> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Tour findById(String id) {
        return storage.get(id);
    }

}
