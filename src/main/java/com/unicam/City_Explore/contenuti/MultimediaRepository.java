package com.unicam.City_Explore.contenuti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicam.City_Explore.elementi.Status;


public interface MultimediaRepository<T extends MultimediaContent> extends JpaRepository<T, Integer>{
	
	List<MultimediaContent> searchByName(String name);
	
	List<MultimediaContent> searchByDescription(String description);
	
	List<MultimediaContent> findByStatus(Status status);
}
