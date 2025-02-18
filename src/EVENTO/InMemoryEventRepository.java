package EVENTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryEventRepository implements EventRepository{
	 private final List<Event> storage = new ArrayList<>();

	@Override
	public void save(Event event) {
		if(event.getId() < storage.size()) {
			storage.set(event.getId(), event);
		} else {
			event.setId(storage.size());
			storage.add(event);
		}
	}

	@Override
	public Event findById(int id) {
		return storage.get(id);
	}

	@Override
	public List<Event> findAll() {
		return this.storage;
	}

	@Override
	public List<Event> searchByName(String name) {
		return this.storage.stream().filter(event -> event.getName() == name).collect(Collectors.toList());
	}

	@Override
	public List<Event> searchByDescription(String description) {
		return this.storage.stream().filter(event -> event.getDescription() == description).collect(Collectors.toList());
	}
}
