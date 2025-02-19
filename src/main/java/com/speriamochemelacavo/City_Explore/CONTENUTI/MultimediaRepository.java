package CONTENUTI;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ELEMENT.Status;

public interface MultimediaRepository<T extends MultimediaContent> extends JpaRepository<T, Integer>{
	
	List<MultimediaContent> searchByName(String name);
	
	List<MultimediaContent> searchByDescription(String description);
	
	List<MultimediaContent> findByStatus(Status status);
}
