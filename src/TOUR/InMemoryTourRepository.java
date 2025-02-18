package TOUR;

import ELEMENT.ElementStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTourRepository implements TourRepository{
	private final List<Tour> storage = new ArrayList<>();

    @Override
    public void save(Tour tour) {
        if(tour.getId() == 0) {
            tour.setId(storage.size()+1);
            storage.add(tour);
        } else if (tour.getId() <= storage.size()){
            storage.set(tour.getId()-1, tour);
        }
    }

    @Override
    public List<Tour> findAll() {
        return this.storage;
    }

    @Override
    public Tour findById(int id) {
        return storage.stream()
                .filter(tour -> tour.getId() == id)
                .toList()
                .get(0);
    }

    @Override
    public List<Tour> findByStatus(ElementStatus status) {
        return this.storage.stream().filter(tour -> tour.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public List<Tour> searchByName(String name) {
        return this.storage.stream().filter(tour -> tour.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Tour> searchByDescription(String description) {
        return this.storage.stream().filter(tour -> tour.getDescription().equals(description)).collect(Collectors.toList());
    }

    @Override
    public void deleteByID(int id) {
        this.storage.removeIf(tour -> tour.getId() == id);
    }

}
