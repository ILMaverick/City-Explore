package com.unicam.City_Explore.user;

import com.unicam.City_Explore.notifica.NotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionRequestService {
    @Autowired
    private PermissionRequestRepository permissionRequestRepository;
    @Autowired
    private NotificationListener notificationListener;

    public PermissionRequest createRequest(User user, String requestMessage) {
        if (user != null) {
            if(user.getRole() != Role.ADMINISTRATOR) {
                PermissionRequest request = new PermissionRequest();
                request.setUser(user);
                request.setRequestMessage(requestMessage);
                request.setApproved(false);
                permissionRequestRepository.save(request);
                notificationListener.handleRequestSent(request);
                return request;
            }
        }
        return null;
    }

    public List<PermissionRequest> getAllRequests() {
        return permissionRequestRepository.findAll();
    }

    public void deleteRequest(int idRequest) {
        permissionRequestRepository.findById(idRequest).ifPresent(request -> permissionRequestRepository.delete(request));
    }

}
