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
	}

	public User createUser(String name, String surname, String username, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        this.saveUser(user);
        notificationListener.handleCreateUser(user);
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
                userRepository.save(userSelected);
                notificationListener.handleUpdateUser(user);
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
                userRepository.delete(user);
                notificationListener.handleDeleteUser(user, reason);
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
                userRepository.save(user);
                notificationListener.handleUpdateUserRole(user, role);
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
                userRepository.save(user);
                notificationListener.handleUpdateContributor(user);
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

    public void approveRequest(int requestId, boolean isApproved) {
        User administrator = userRepository.searchUsersByRole(Role.ADMINISTRATOR).stream().findFirst().orElse(null);
        if(checkAdministrator(administrator)) {
            List<PermissionRequest> requestList = permissionRequestService.getAllRequests();
            for (PermissionRequest request : requestList) {
                request.setApproved(isApproved);
                if (request.isApproved()) {
                    notificationListener.handleApprovedRequest(request);
                } else {
                    notificationListener.handleRejectRequest(request);
                }
                permissionRequestService.deleteRequest(request.getId());
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
