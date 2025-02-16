package EVENTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEventRepository implements EventRepository{
	 private final Map<String, Event> storage = new HashMap<>();

	    @Override
	    public void save(Event event) {
	        if (event != null) {
	            storage.put(event.getId(), event);
	        }
	    }

	    @Override
	    public List<Event> findAll() {
	        return new ArrayList<>(storage.values());
	    }

	    @Override
	    public Event findById(String id) {
	        return storage.get(id);
	    }
}
