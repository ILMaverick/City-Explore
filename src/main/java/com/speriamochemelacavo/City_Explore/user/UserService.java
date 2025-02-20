package user;

import notifica.NotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRequestService permissionRequestService;
    @Autowired
    private NotificationListener notificationListener;

    public User createUser(String name, String surname, String username, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(int idUser, User user) {
        User userSelected = userRepository.findById(idUser).orElse(null);
        if(userSelected != null) {
            userSelected.setName(user.getName());
            userSelected.setSurname(user.getSurname());
            userSelected.setUsername(user.getUsername());
            user.setEmail(user.getEmail());
            userRepository.save(userSelected);
            return userSelected;
        }
        return null;
    }

    public void deleteUser(int idUser) {
        userRepository.findById(idUser).ifPresent(userRepository::delete);
    }

    public User updateUserRole(int idUser, Role role) {
        User user = userRepository.findById(idUser).orElse(null);
        if(user != null) {
            user.setRole(role);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public List<User> searchUsersByRole(Role role) {
        return userRepository.searchUsersByRole(role);
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.searchUsersByName(name);
    }

    public List<User> searchUsersBySurname(String surname) {
        return userRepository.searchUsersBySurname(surname);
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.searchUsersByUsername(username);
    }

    public List<User> searchUsersByEmail(String email) {
        return userRepository.searchUsersByEmail(email);
    }

    public void approveRequest(int requestId, boolean isApproved, Role newRole) {
        List<PermissionRequest> requestList = permissionRequestService.getAllRequests();
        for(PermissionRequest request: requestList) {
            request.setApproved(isApproved);
            User user = request.getAuthor();
            if(request.isApproved()) {
                notificationListener.handleApprovedRequest(request);
                updateUserRole(user.getId(), newRole);
            } else {
                notificationListener.handleRejectRequest(request);
            }
            permissionRequestService.deleteRequest(requestId);
        }
    }


}
