package user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> searchUsersByRole(Role role);

    List<User> searchUsersByName(String name);

    List<User> searchUsersBySurname(String surname);

    List<User> searchUsersByUsername(String username);

    List<User> searchUsersByEmail(String email);
}
