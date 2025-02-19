package CONTEST;

import ELEMENT.ElementRepository;
import ELEMENT.Status;

import java.util.List;

public interface ContestRepository extends ElementRepository<Contest> {

	List<Contest> searchByName(String name);

	List<Contest> searchByDescription(String description);
	
	List<Contest> findByStatus(Status status);
}