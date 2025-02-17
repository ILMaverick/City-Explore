package TOUR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTourRepository implements TourRepository{
	private List<Tour> storage = new ArrayList<Tour>();

    @Override
    public void save(Tour tour) {
        if (tour != null) {
        	tour.setId(storage.size());
            storage.add(tour);
        }
    }

    @Override
    public List<Tour> findAll() {
        return this.storage;
    }

    @Override
    public Tour findById(int id) {
        return storage.get(id);
    }

}
