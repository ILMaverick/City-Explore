package com.unicam.City_Explore.contest;

import java.util.List;

import com.unicam.City_Explore.elementi.ElementRepository;
import com.unicam.City_Explore.elementi.Status;

public interface ContestRepository extends ElementRepository<Contest> {

	List<Contest> searchByName(String name);

	List<Contest> searchByDescription(String description);
	
	List<Contest> findByStatus(Status status);
}