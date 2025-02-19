package com.speriamochemelacavo.City_Explore.TOUR;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementRepository;
import com.speriamochemelacavo.City_Explore.ELEMENT.Status;

import java.util.List;

public interface TourRepository extends ElementRepository<Tour> {

	List<Tour> searchByName(String name);

	List<Tour> searchByDescription(String description);
	
	List<Tour> searchByStatus(Status status);

}
