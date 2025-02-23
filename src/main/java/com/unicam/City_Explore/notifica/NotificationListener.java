package com.unicam.City_Explore.notifica;

import com.unicam.City_Explore.contest.ContestParticipation;
import com.unicam.City_Explore.contest.QuoteCriterion;
import com.unicam.City_Explore.elementi.AbstractElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.segnalazione.MediaReport;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.user.PermissionRequest;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserRepository;

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
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleUpdateContest(Contest contest) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Contest esistente " + contest.getName(), NotificationType.UPDATE);
        Notification notificationForAuthor = notificationService.createNotification("Il Contest e' stato aggiornato " + contest.getName(), NotificationType.UPDATE);
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleDeleteContest(Contest contest) {
        Notification notification = notificationService.createNotification("E' stato elimianto un Contest " + contest.getName(), NotificationType.DELETION);
        Notification notificationForAuthor = notificationService.createNotification("Il Contest e' stato eliminato " + contest.getName(), NotificationType.DELETION);
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
        }
        notificationService.sendNotification(notificationForAuthor, contest.getAuthor());
    }

    public void handleParticipationContest(ContestParticipation participation) {
        Notification notification = notificationService.createNotification("Ti sei iscritto all Contest: " + participation.getContest().getName() + ", "
                + "con questi Contenuti: " + participation.getMultimediaContentList() +
                "\n. " + "Il Contest avrà scadenza in data:" + participation.getContest().getDeadline(), NotificationType.PARTICIPATION);
        notificationService.sendNotification(notification, participation.getUser());
    }

    public void handleDeleteParticipationContest(ContestParticipation participation, String reason) {
        Notification notification = notificationService.createNotification("Ti sei cancellato dal Contest: " + participation.getContest().getName() +
                ".\nPuò fornirci una motivazione? " + reason, NotificationType.DELETION);
        notificationService.sendNotification(notification, participation.getUser());
    }

    public void handleEvaluateParticipantContest(ContestParticipation participation, QuoteCriterion quoteCriterion) {
        List<User> animators = userRepository.searchUsersByRole(Role.ANIMATOR);
        Notification notification = notificationService.createNotification("Il partecipante " + participation.getUser().getUsername() +
                " e' stato valutato con punteggio: " + quoteCriterion.getVote(), NotificationType.UPDATE);
        for(User animator: animators) {
            notificationService.sendNotification(notification, animator);
        }
    }

    public void handleAlreadyQuote(ContestParticipation participation) {
        List<User> animators = userRepository.searchUsersByRole(Role.ANIMATOR);
        Notification notification = notificationService.createNotification("Questo partecipante" + participation.getUser()
                + "ha gia' ricevuto una valutazione.", NotificationType.ALERT);
        for(User animator: animators) {
            notificationService.sendNotification(notification, animator);
        }
    }

    public void handleWinnersParticipantContest(List<User> winners, Contest contest) {
        Notification notificationWinner = notificationService.createNotification("Il Contest e' terminato. Sei uno dei vincitori del Contest. Congratulazioni. Hai vinto il premio di: "
                + contest.getPrize() , NotificationType.UPDATE);
        Notification notificationForParticipant = notificationService.createNotification("Il Contest e' terminato. Grazie per aver partecipato al Contest.", NotificationType.UPDATE);
        for(User winner: winners) {
            notificationService.sendNotification(notificationWinner, winner);
        }
        List<User> participantContest = contest.getParticipationContestList().stream().map(ContestParticipation::getUser).toList();
        for(User participant: participantContest) {
            notificationService.sendNotification(notificationForParticipant, participant);
        }
    }

    public void handleNewEvent(Event event) {
        Notification notification = notificationService.createNotification("E' stato creato un nuovo Evento " + event.getName(), NotificationType.CREATION);
        Notification notificationForAuthor = notificationService.createNotification("L'Evento e' stato creato " + event.getName(), NotificationType.CREATION);
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
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
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
        }
        notificationService.sendNotification(notificationForAuthor, event.getAuthor());
    }

    public void handleDeleteEvent(Event event) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Evento " + event.getName(), NotificationType.DELETION);
        Notification notificationForAuthor = notificationService.createNotification("L'Evento e' stato eliminato " + event.getName(), NotificationType.DELETION);
        for(User participant: userRepository.findAll()) {
            notificationService.sendNotification(notification, participant);
        }
        notificationService.sendNotification(notificationForAuthor, event.getAuthor());
    }

    public void handleParticipationEvent(Event event, User user) {
        Notification notification = notificationService.createNotification("Ti sei iscritto all'Evento: " + event.getName() +
                ", " + "che si terra' in data e orario:" + event.getTime(), NotificationType.PARTICIPATION);
        notificationService.sendNotification(notification, user);
    }

    public void handleDeleteParticipationEvent(Event event, User user, String reason) {
        Notification notification = notificationService.createNotification("Ti sei cancellato dall'Evento: " + event.getName() +
                ".\nPuò fornirci una motivazione? " + reason, NotificationType.DELETION);
        notificationService.sendNotification(notification, user);
    }

    public void handleCreateMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato creato un Contenuto " + multimediaContent, NotificationType.CREATION);
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

    public void handleLoadMultimediaContentToTour(Tour tour, MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("Un Contenuto e' stato aggiunto ad un Punto di Interesse "
                + tour.getName() + ", " + multimediaContent, NotificationType.UPDATE);
        if(tour.getAuthor().equals(multimediaContent.getAuthor())) {
            notificationService.sendNotification(notification, tour.getAuthor());
        } else {
            notificationService.sendNotification(notification, tour.getAuthor());
            notificationService.sendNotification(notification, multimediaContent.getAuthor());
        }
    }

    public void handleUpdateMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato aggiornato un Contenuto esistente "
                + multimediaContent, NotificationType.UPDATE);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleDeleteMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("E' stato eliminato un Contenuto esistente "
                + multimediaContent, NotificationType.DELETION);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
    }

    public void handleValidationPOI(PointOfInterest pointOfInterest) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        Notification notification = notificationService.createNotification("Il Punto di Interesse " + pointOfInterest.getName()
                + " e' in attesa di essere validato.", NotificationType.VALIDATION);
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }
    public void handleApprovePOI(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("Il Punto di Interesse " + pointOfInterest.getName()
                + "e' stato approvato.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleUpdatePOIStatus(PointOfInterest pointOfInterest) {
        Notification notification = notificationService.createNotification("Il Punto di Interesse " + pointOfInterest.getName() +  "e' stato marcato come aggiornato.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleRejectPOI(PointOfInterest pointOfInterest, String reason) {
        Notification notification = notificationService.createNotification("Il Punto di Interesse " + pointOfInterest.getName() +" e' stato rifiutato e sara' cancellato, perche' " + reason, NotificationType.DELETION);
        notificationService.sendNotification(notification, pointOfInterest.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleValidationTour(Tour tour) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        Notification notification = notificationService.createNotification("Un nuovo Itinerario " + tour.getName()
                + " e' in attesa di essere validato." , NotificationType.VALIDATION);
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }
    public void handleApproveTour(Tour tour) {
        Notification notification = notificationService.createNotification("L'Itinerario e' stato approvato " + tour.getName(), NotificationType.UPDATE);
        notificationService.sendNotification(notification, tour.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleUpdateTourStatus(Tour tour) {
        Notification notification = notificationService.createNotification("L'Itinerario " + tour.getName() + " e' stato marcato come aggiornato.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, tour.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleRejectTour(Tour tour, String reason) {
        Notification notification = notificationService.createNotification("L'Itinerario "+ tour.getName() + " e' stato rifiutato e sara' cancellato, perche' " + reason, NotificationType.DELETION);
        notificationService.sendNotification(notification, tour.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleValidationMultimediaContent(MultimediaContent multimediaContent) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        Notification notification = notificationService.createNotification("Il Contenuto " + multimediaContent + " e' in attesa di essere validato.", NotificationType.VALIDATION);
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleApproveMultimediaContent(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("Il Contenuto e' stato approvato " + multimediaContent, NotificationType.UPDATE);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleRejectMultimediaContent(MultimediaContent multimediaContent, String reason) {
        Notification notification = notificationService.createNotification("Il Contenuto " + multimediaContent + " e' stato rifiutato e sara' cancellato, perche' " + reason , NotificationType.DELETION);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleUpdateMultimediaContentStatus(MultimediaContent multimediaContent) {
        Notification notification = notificationService.createNotification("Il Contenuto " + multimediaContent + " e' stato marcato come aggiornato.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, multimediaContent.getAuthor());
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleReportedMultimediaContent(MediaReport report) {
        List<User> curators = userRepository.findAll().stream().filter(u -> u.getRole() == Role.CURATOR).toList();
        Notification notification = notificationService.createNotification("Segnalazione per il Contenuto " + report.getMultimediaContent()
                + "\nda parte di " + report.getReporter()
                + ": " + report.getReason(), NotificationType.ALERT);
        for(User curator: curators) {
            notificationService.sendNotification(notification, curator);
        }
    }

    public void handleApprovedRequest(PermissionRequest request) {
        Notification notification = notificationService.createNotification("La sua richiesta: " + request.getRequestMessage()
                + " e' stata concessa.", NotificationType.ALERT);
        notificationService.sendNotification(notification, request.getAuthor());
    }

    public void handleRejectRequest(PermissionRequest request) {
        Notification notification = notificationService.createNotification("La sua richiesta: " + request.getRequestMessage()
                + " e' stata negata.", NotificationType.ALERT);
        notificationService.sendNotification(notification, request.getAuthor());
    }

    public void handleCreateUser(User user) {
        Notification notification = notificationService.createNotification("L'Utente " + user.getName() + " " + user.getSurname() +
                ", con username " + user.getUsername() + " e' stato aggiunto.", NotificationType.CREATION);
        notificationService.sendNotification(notification, user);
    }

    public void handleUpdateUser(User user) {
        Notification notification = notificationService.createNotification("I dati dell'Utente " + user.getUsername()
                + "sono stati aggiornati.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, user);
    }

    public void handleUpdateUserRole(User user, Role role) {
        Notification notification = notificationService.createNotification("Il ruolo dell'Utente " + user.getUsername()
                + "e' stato aggiornato in " + role.name(), NotificationType.UPDATE);
        notificationService.sendNotification(notification, user);
    }

    public void handleDeleteUser(User user, String reason) {
        Notification notification = notificationService.createNotification("L'Utente " + user.getUsername()
                + " e' stato eliminato. \nPerche': " + reason, NotificationType.UPDATE);
        notificationService.sendNotification(notification, user);
    }

    public void handleDenialPermission(User author) {
        Notification notification = notificationService.createNotification("Non hai i permessi per eseguire questa operazione.", NotificationType.DENIAL);
        notificationService.sendNotification(notification, author);
    }

    public void handleUpdateContributor(User contributor) {
        Notification notification = notificationService.createNotification("Sei stato promosso a Contributor Autorizzato.", NotificationType.UPDATE);
        notificationService.sendNotification(notification, contributor);
    }

    public void handleNewElementInPreferList(AbstractElement element, User authenticatedTourist) {
        Notification notification = notificationService.createNotification("Hai un nuovo elemento nella lista dei preferiti. " + element.getName(), NotificationType.CREATION);
        notificationService.sendNotification(notification, authenticatedTourist);
    }
}
