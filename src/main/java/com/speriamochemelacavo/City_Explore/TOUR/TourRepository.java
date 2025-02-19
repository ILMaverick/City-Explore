package TOUR;

import ELEMENT.ElementRepository;
import ELEMENT.Status;

import java.util.List;

public interface TourRepository extends ElementRepository<Tour> {

	List<Tour> searchByName(String name);

	List<Tour> searchByDescription(String description);
	
	List<Tour> searchByStatus(Status status);

}
