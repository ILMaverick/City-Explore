package com.speriamochemelacavo.City_Explore.EVENTO;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementRepository;
import com.speriamochemelacavo.City_Explore.ELEMENT.Status;

import java.util.List;

public interface EventRepository extends ElementRepository<Event> {

	List<Event> searchByName(String name);

	List<Event> searchByDescription(String description);
	
	List<Event> searchByStatus(Status status);
}
