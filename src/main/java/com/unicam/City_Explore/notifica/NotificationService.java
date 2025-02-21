package com.unicam.City_Explore.notifica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(String message, NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setNotificationType(notificationType);
        notification.setLocalDateTime(LocalDateTime.now());
        notificationRepository.save(notification);
        return notification;
    }

    public List<Notification> getNotificationsForUser(User user) {
        List<Notification> userNotifications = user.getNotificationList();
        for (Notification notification : getAllNotifications()) {
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
        return notificationRepository.findAll();
    }

    public void deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
    }
}
