package com.unicam.City_Explore.poi;

import java.util.List;
import java.util.Optional;

import com.unicam.City_Explore.elementi.ElementRepository;
import com.unicam.City_Explore.elementi.Status;

public interface POIRepository extends ElementRepository<PointOfInterest> {

	Optional<PointOfInterest> findByNameAndLatitudeAndLongitude(String name, double lat, double lon);
	List<PointOfInterest> searchByName(String name);
	List<PointOfInterest> searchByDescription(String description);
	List<PointOfInterest> searchByStatus(Status status);
	List<PointOfInterest> searchByType(POIType type);
}
