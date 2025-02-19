package tour;

import java.util.List;

import element.ElementRepository;
import element.Status;

public interface TourRepository extends ElementRepository<Tour> {

	List<Tour> searchByName(String name);

	List<Tour> searchByDescription(String description);
	
	List<Tour> searchByStatus(Status status);

}
