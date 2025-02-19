package com.speriamochemelacavo.City_Explore.POI;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementRepository;
import com.speriamochemelacavo.City_Explore.ELEMENT.Status;

import java.util.List;

public interface POIRepository extends ElementRepository<PointOfInterest> {

	List<PointOfInterest> searchByName(String name);
	
	List<PointOfInterest> searchByDescription(String description);
	
	List<PointOfInterest> searchByStatus(Status status);
}
