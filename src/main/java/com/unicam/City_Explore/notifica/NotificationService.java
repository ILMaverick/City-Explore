package com.unicam.City_Explore.notifica;

import org.springframework.stereotype.Service;

import com.unicam.City_Explore.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
	
    private final List<Notification> notificationList;

    public NotificationService(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Notification createNotification(String message, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setNotificationType(notificationType);
        notification.setLocalDateTime(LocalDateTime.now());
        notificationList.add(notification);
        return notification;
    }

    public List<Notification> getNotificationsForUser(User user) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (notification.getUser().equals(user)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
    public void sendNotification(Notification notification, User user) {
        notification.setUser(user);
        user.getNotificationList().add(notification);
        System.out.println("Hai una nuova Notifica: " + user + ": " + notification.getMessage());
    }

    public Notification openNotification(int idNotification) {
        for(Notification notification: getAllNotifications()) {
            if(notification.getId()==idNotification) {
                notification.setRead(true);
                return notification;
            }
        }
        return null;
    }

    public List<Notification> getAllNotifications() {
        return notificationList;
    }

    public void deleteNotification(Notification notification) {
        notificationList.remove(notification);
    }
}
