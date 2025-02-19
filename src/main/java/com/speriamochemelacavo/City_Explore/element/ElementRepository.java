package element;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository<T extends AbstractElement> extends JpaRepository<T, Integer> {

}
