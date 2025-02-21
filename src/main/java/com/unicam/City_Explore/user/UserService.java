package com.unicam.City_Explore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.notifica.NotificationListener;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRequestService permissionRequestService;
    @Autowired
    private NotificationListener notificationListener;
    
    private User currentUser;
    
    

    public UserService(User currentUser) {
		super();
		this.currentUser = new User();
		this.currentUser.setName("Simone");
		this.currentUser.setSurname("Stacchiotti");
		this.currentUser.setUsername("SilverSimon");
		this.currentUser.setEmail("simone.stacchiotti.email@gmail.com");
		this.currentUser.setRole(Role.ADMINISTRATOR);
	}

	public User createUser(String name, String surname, String username, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        notificationListener.handleCreateUser(user);
        this.saveUser(user);
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
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            if (userSelected != null) {
                userSelected.setName(user.getName());
                userSelected.setSurname(user.getSurname());
                userSelected.setUsername(user.getUsername());
                user.setEmail(user.getEmail());
                notificationListener.handleUpdateUser(user);
                userRepository.save(userSelected);
                return userSelected;
            }
        } else {
            notificationListener.handleDenialPermission(administrator);
        }
        return null;
    }

    public void deleteUser(int idUser, String reason) {
        User user = userRepository.findById(idUser).orElse(null);
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            if (user != null) {
                notificationListener.handleDeleteUser(user, reason);
                userRepository.delete(user);
            }
        } else {
            notificationListener.handleDenialPermission(administrator);
        }
    }

    public void updateUserRole(int idUser, Role role) {
        User user = userRepository.findById(idUser).orElse(null);
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            if(user != null) {
                user.setRole(role);
                notificationListener.handleUpdateUserRole(user, role);
                userRepository.save(user);
            }
        } else {
            notificationListener.handleDenialPermission(administrator);
        }
    }

    public void updateContributor(int idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            if (user != null && user.getRole() == Role.CONTRIBUTOR) {
                user.setRole(Role.AUTORIZED_CONTRIBUTOR);
                notificationListener.handleUpdateContributor(user);
                userRepository.save(user);
            }
        } else {
            notificationListener.handleDenialPermission(administrator);
        }
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
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            List<PermissionRequest> requestList = permissionRequestService.getAllRequests();
            for (PermissionRequest request : requestList) {
                request.setApproved(isApproved);
                User user = request.getAuthor();
                if (request.isApproved()) {
                    notificationListener.handleApprovedRequest(request);
                    updateUserRole(user.getId(), newRole);
                } else {
                    notificationListener.handleRejectRequest(request);
                }
                permissionRequestService.deleteRequest(requestId);
            }
        } else {
            notificationListener.handleDenialPermission(administrator);
        }
    }

    private boolean checkAdministrator(User administrator) {
        return administrator != null && administrator.getRole() == Role.ADMINISTRATOR;
    }

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
