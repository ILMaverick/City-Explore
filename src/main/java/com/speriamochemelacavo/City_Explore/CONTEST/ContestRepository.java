package com.speriamochemelacavo.City_Explore.CONTEST;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementRepository;
import com.speriamochemelacavo.City_Explore.ELEMENT.Status;

import java.util.List;

public interface ContestRepository extends ElementRepository<Contest> {

	List<Contest> searchByName(String name);

	List<Contest> searchByDescription(String description);
	
	List<Contest> findByStatus(Status status);
}