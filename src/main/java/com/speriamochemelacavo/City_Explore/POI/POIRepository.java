package POI;

import ELEMENT.ElementRepository;
import ELEMENT.Status;

import java.util.List;

public interface POIRepository extends ElementRepository<PointOfInterest> {

	List<PointOfInterest> searchByName(String name);
	
	List<PointOfInterest> searchByDescription(String description);
	
	List<PointOfInterest> searchByStatus(Status status);
}
