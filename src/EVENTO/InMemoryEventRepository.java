package EVENTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEventRepository implements EventRepository{
	 private final Map<Integer, Event> storage = new HashMap<>();

	    @Override
	    public void save(Event event) {
			if (event != null) {
				event.setId(storage.size());
				storage.put(event.getId(), event);
			}
	    }

		@Override
		public Event findById(int id) {
		return storage.get(id);
	}

	    @Override
	    public List<Event> findAll() {
			return new ArrayList<>(storage.values());
	    }


}
