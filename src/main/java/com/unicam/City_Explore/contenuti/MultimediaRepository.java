package com.unicam.City_Explore.contenuti;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unicam.City_Explore.elementi.Status;


public interface MultimediaRepository extends JpaRepository<MultimediaContent, Integer>{

	List<MultimediaContent> findByStatus(Status status);
	List<MultimediaContent> searchByName(String name);
	List<MultimediaContent> searchByDescription(String description);
}
