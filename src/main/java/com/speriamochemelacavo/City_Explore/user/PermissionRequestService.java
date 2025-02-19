package user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionRequestService {
    @Autowired
    private PermissionRequestRepository permissionRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public PermissionRequest createRequest(int userId, String requestMessage) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            PermissionRequest request = new PermissionRequest();
            request.setUser(user);
            request.setRequestMessage(requestMessage);
            request.setApproved(false);
            permissionRequestRepository.save(request);
            return request;
        }
        return null;
    }

    public PermissionRequest approveRequest(int requestId, boolean isApproved) {
        PermissionRequest request = permissionRequestRepository.findById(requestId).orElse(null);
        if (request != null) {
            request.setApproved(isApproved);
            permissionRequestRepository.delete(request);
            return permissionRequestRepository.save(request);
        }
        return null;
    }

    public List<PermissionRequest> getAllRequests() {
        return permissionRequestRepository.findAll();
    }

}
