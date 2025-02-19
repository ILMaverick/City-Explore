package com.speriamochemelacavo.City_Explore.NOTIFICA;

import com.speriamochemelacavo.City_Explore.USER.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private List<Notification> notificationList;

    private void sendNotification(Notification notification) {
        // Logica per inviare la notifica
        System.out.println("Notifica per " + notification.getUser() + ": " + notification.getMessage());
    }

    public Notification createNotification(String message, NotificationType notificationType, User user) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setNotificationType(notificationType);
        notification.setUser(user);
        notification.setLocalDateTime(LocalDateTime.now());
        notificationList.add(notification);
        return notification;
    }

    public List<Notification> getAllNotifications() {
        return notificationList;
    }

    public void deleteNotification(Notification notification) {
        notificationList.remove(notification);
    }

    public List<Notification> getNotificationsForUser(String user) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (notification.getUser().equals(user)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
}
