package EVENTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryEventRepository implements EventRepository{
	 private final List<Event> storage = new ArrayList<>();

	@Override
	public void save(Event event) {
		if(event.getId() == 0) {
			event.setId(storage.size()+1);
			storage.add(event);
		} else if (event.getId() <= storage.size()){
			storage.set(event.getId()-1, event);
		}
	}

	@Override
	public Event findById(int id) {
		return storage.stream()
				.filter(event -> event.getId() == id)
				.toList()
				.get(0);
	}

	@Override
	public List<Event> findAll() {
		return this.storage;
	}

	@Override
	public List<Event> searchByName(String name) {
		return this.storage.stream().filter(event -> Objects.equals(event.getName(), name)).collect(Collectors.toList());
	}

	@Override
	public List<Event> searchByDescription(String description) {
		return this.storage.stream().filter(event -> Objects.equals(event.getDescription(), description)).collect(Collectors.toList());
	}

	@Override
	public void deleteByID(int id) {
		this.storage.removeIf(event -> event.getId() == id);
	}
}
