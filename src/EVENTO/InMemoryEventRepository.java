package EVENTO;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventRepository implements EventRepository{
	 private final List<Event> storage = new ArrayList<>();

	    @Override
	    public void save(Event event) {
	        if (event != null) {
	        	event.setId(storage.size());
	            storage.add(event);
	        }
	    }

	    @Override
	    public List<Event> findAll() {
	        return this.storage;
	    }

	    @Override
	    public Event findById(int id) {
	        return storage.get(id);
	    }
}
