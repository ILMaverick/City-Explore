package TOUR;

import ELEMENT.ElementStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTourRepository implements TourRepository{
	private final List<Tour> storage = new ArrayList<>();

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

    @Override
    public List<Tour> findByStatus(ElementStatus status) {
        return this.storage.stream().filter(tour -> tour.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public List<Tour> searchByName(String name) {
        return this.storage.stream().filter(tour -> tour.getName() == name).collect(Collectors.toList());
    }

    @Override
    public List<Tour> searchByDescription(String description) {
        return this.storage.stream().filter(tour -> tour.getDescription() == description).collect(Collectors.toList());
    }

}
