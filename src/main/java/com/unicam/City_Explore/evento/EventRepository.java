package com.unicam.City_Explore.evento;

import java.util.List;

import com.unicam.City_Explore.elementi.ElementRepository;
import com.unicam.City_Explore.elementi.Status;

public interface EventRepository extends ElementRepository<Event> {

	List<Event> searchByName(String name);

	List<Event> searchByDescription(String description);
	
	List<Event> searchByStatus(Status status);
}
