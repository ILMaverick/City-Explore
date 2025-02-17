package EVENTO;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventRepository implements EventRepository{
	 private final List<Event> storage = new ArrayList<>();

	@Override
	public void save(Event event) {
		event.setId(storage.size());
		storage.add(event);
	}

	@Override
	public Event findById(int id) {
		return storage.get(id);
	}

	@Override
	public List<Event> findAll() {
			return this.storage;
	    }


}
