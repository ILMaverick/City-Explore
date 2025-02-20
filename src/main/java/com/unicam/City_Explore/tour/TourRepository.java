package com.unicam.City_Explore.tour;

import java.util.List;

import com.unicam.City_Explore.elementi.ElementRepository;
import com.unicam.City_Explore.elementi.Status;

public interface TourRepository extends ElementRepository<Tour> {

	List<Tour> searchByName(String name);

	List<Tour> searchByDescription(String description);
	
	List<Tour> searchByStatus(Status status);

}
