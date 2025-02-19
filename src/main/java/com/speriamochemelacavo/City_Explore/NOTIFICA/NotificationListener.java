package com.speriamochemelacavo.City_Explore.NOTIFICA;

import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContent;
import com.speriamochemelacavo.City_Explore.CONTEST.Contest;
import com.speriamochemelacavo.City_Explore.EVENTO.Event;
import com.speriamochemelacavo.City_Explore.POI.PointOfInterest;
import com.speriamochemelacavo.City_Explore.SEGNALAZIONE.MediaReport;
import com.speriamochemelacavo.City_Explore.TOUR.Tour;
import com.speriamochemelacavo.City_Explore.USER.Role;
import com.speriamochemelacavo.City_Explore.USER.User;
import com.speriamochemelacavo.City_Explore.USER.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationListener {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;

    public NotificationListener(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void handleCreatePOI(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("E' stato creato un Punto di Interesse" + pointOfInterest, NotificationType.CREATION);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
    }

    public void handleUpdatePOI(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Punto di Interesse" + pointOfInterest, NotificationType.UPDATE);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
    }

    public void handleDeletePOI(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Punto di Interesse" + pointOfInterest, NotificationType.DELETION);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
    }

    public void handleCreateTour(Tour tour) {
        Notification notification = notificationService.createNotification("E' stato creato un Itinerario" + tour, NotificationType.CREATION);
        notificationService.sendNotification(notification, tour.getAuthor());
    }

    public void handleUpdateTour(Tour tour) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Itinerario" + tour, NotificationType.UPDATE);
        notificationService.sendNotification(notification, tour.getAuthor());
    }

    public void handleDeleteTour(Tour tour) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Itinerario" + tour, NotificationType.DELETION);
        notificationService.sendNotification(notification, tour.getAuthor());
    }

    public void handleNewContest(Contest contest) {
        Notification notification = notificationService.createNotification("E' stato creato un nuovo Contest " + contest.getName(), NotificationType.CREATION);
        Notification notificationForAuthor = notificationService.createNotification("Il Contest e' stato creato " + contest.getName(), NotificationType.CREATION);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleUpdateContest(Contest contest) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Contest esistente " + contest.getName(), NotificationType.UPDATE);
        Notification notificationForAuthor = notificationService.createNotification("Il Contest e' stato aggiornato " + contest.getName(), NotificationType.UPDATE);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleDeleteContest(Contest contest) {
        Notification notification = notificationService.createNotification("E' stato elimianto un Contest " + contest.getName(), NotificationType.DELETION);
        Notification notificationForAuthor = notificationService.createNotification("Il Contest e' stato eliminato " + contest.getName(), NotificationType.DELETION);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleNewEvent(Event event) {
        Notification notification = notificationService.createNotification("E' stato creato un nuovo Evento " + event.getName(), NotificationType.CREATION);
        Notification notificationForAuthor = notificationService.createNotification("L'Evento e' stato creato " + event.getName(), NotificationType.CREATION);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, event.getAuthor());
    }

    public void handleAddEventToPOI(Event event, PointOfInterest pointOfInterest) {
        Notification notificationForAuthor = notificationService.createNotification(
                "E' stato aggiunto al Punto di Interesse " + pointOfInterest.getName() + " questo Evento " + event.getName(), NotificationType.UPDATE);
        Notification notificationForAnimator = notificationService.createNotification("L'Evento e' stato creato " + event.getName(), NotificationType.UPDATE);
        notificationService.sendNotification(notificationForAnimator, event.getAuthor());
        notificationService.sendNotification(notificationForAuthor, pointOfInterest.getAuthor());
    }

    public void handleUpdateEvent(Event event) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Evento esistente " + event.getName(), NotificationType.UPDATE);
        Notification notificationForAuthor = notificationService.createNotification("L'Evento e' stato aggiornato " + event.getName(), NotificationType.UPDATE);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, event.getAuthor());
    }

    public void handleDeleteEvent(Event event) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Evento " + event.getName(), NotificationType.DELETION);
        Notification notificationForAuthor = notificationService.createNotification("L'Evento e' stato eliminato " + event.getName(), NotificationType.DELETION);
        for(User user: userRepository.findAll()) {
            notificationService.sendNotification(notification, user);
        }
        notificationService.sendNotification(notificationForAuthor, event.getAuthor());
    }

    public void handleCreateMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato creato un Contenuto" + multimediaContent, NotificationType.CREATION);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleLoadMultimediaContentToPOI(PointOfInterest pointOfInterest, MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("Un Contenuto e' stato aggiunto ad un Punto di Interesse "
                + pointOfInterest.getName() + ", " + multimediaContent, NotificationType.UPDATE);
        if(pointOfInterest.getAuthor().equals(multimediaContent.getAuthor())) {
            notificationService.sendNotification(notification, pointOfInterest.getAuthor());
        } else {
            notificationService.sendNotification(notification, pointOfInterest.getAuthor());
            notificationService.sendNotification(notification, multimediaContent.getAuthor());
        }
    }

    public void handleUpdateMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Contenuto esistente " + multimediaContent, NotificationType.UPDATE);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleDeleteMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Contenuto esistente " + multimediaContent, NotificationType.DELETION);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleValidationPOI(PointOfInterest pointOfInterest) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            Notification notification = notificationService.createNotification("Un nuovo Punto di Interesse e' in attesa di essere validato " + pointOfInterest.getName(), NotificationType.VALIDATION);
            notificationService.sendNotification(notification, curator);
        }
    }
    public void handleApprovePOI(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("Il Punto di Interesse e' stato approvato " + pointOfInterest.getName(), NotificationType.UPDATE);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
    }

    public void handleRejectPOI(PointOfInterest pointOfInterest, User author) {
        Notification notification = notificationService.createNotification("Il Punto di Interesse e' stato rifiutato e sara' cancellato " + pointOfInterest.getName(), NotificationType.DELETION);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
    }

    public void handleValidationTour(Tour tour) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            Notification notification = notificationService.createNotification("Un nuovo Itinerario e' in attesa di essere validato " + tour.getName(), NotificationType.VALIDATION);
            notificationService.sendNotification(notification, curator);
        }
    }
    public void handleApproveTour(Tour tour, User author) {
        Notification notification = notificationService.createNotification("L'Itinerario e' stato approvato " + tour.getName(), NotificationType.UPDATE);
        notificationService.sendNotification(notification, tour.getAuthor());
    }

    public void handleRejectTour(Tour tour, User author) {
        Notification notification = notificationService.createNotification("L'Itinerario e' stato rifiutato e sara' cancellato " + tour.getName(), NotificationType.DELETION);
        notificationService.sendNotification(notification, tour.getAuthor());
    }

    public void handleValidationMultimediaContent(MultimediaContent multimediaContent) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            Notification notification = notificationService.createNotification("Un nuovo Contenuto e' in attesa di essere validato " + multimediaContent, NotificationType.VALIDATION);
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleApproveMultimediaContent(MultimediaContent multimediaContent, User author) {
        Notification notification = notificationService.createNotification("Il Contenuto e' stato approvato " + multimediaContent, NotificationType.UPDATE);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleRejectMultimediaContent(MultimediaContent multimediaContent, User author) {
        Notification notification = notificationService.createNotification("Il Contenuto e' stato rifiutato e sara' cancellato " + multimediaContent, NotificationType.DELETION);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleReportedMultimediaContent(MediaReport mediaReport) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            Notification notification = notificationService.createNotification("Contenuto segnalato " + mediaReport, NotificationType.ALERT);
            notificationService.sendNotification(notification, curator);
        }
    }
}
