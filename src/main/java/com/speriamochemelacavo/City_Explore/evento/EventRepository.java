package evento;

import java.util.List;

import element.ElementRepository;
import element.Status;

public interface EventRepository extends ElementRepository<Event> {

	List<Event> searchByName(String name);

	List<Event> searchByDescription(String description);
	
	List<Event> searchByStatus(Status status);
}
