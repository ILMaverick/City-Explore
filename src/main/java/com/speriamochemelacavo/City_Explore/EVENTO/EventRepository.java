package EVENTO;

import ELEMENT.ElementRepository;
import ELEMENT.Status;

import java.util.List;

public interface EventRepository extends ElementRepository<Event> {

	List<Event> searchByName(String name);

	List<Event> searchByDescription(String description);
	
	List<Event> searchByStatus(Status status);
}
