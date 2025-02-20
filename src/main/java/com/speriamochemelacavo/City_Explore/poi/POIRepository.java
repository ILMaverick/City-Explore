package poi;

import java.util.List;

import element.ElementRepository;
import element.Status;

public interface POIRepository extends ElementRepository<PointOfInterest> {

	List<PointOfInterest> searchByName(String name);
	
	List<PointOfInterest> searchByDescription(String description);
	
	List<PointOfInterest> searchByStatus(Status status);
	List<PointOfInterest> searchByType(POIType type);
}
