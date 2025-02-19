package contest;

import java.util.List;

import element.ElementRepository;
import element.Status;

public interface ContestRepository extends ElementRepository<Contest> {

	List<Contest> searchByName(String name);

	List<Contest> searchByDescription(String description);
	
	List<Contest> findByStatus(Status status);
}