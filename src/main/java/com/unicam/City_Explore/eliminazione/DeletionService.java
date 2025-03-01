package com.unicam.City_Explore.eliminazione;

import com.unicam.City_Explore.contest.ContestParticipation;
import com.unicam.City_Explore.tour.Tappa;
import com.unicam.City_Explore.tour.Way;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentRepository;
import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.contest.ContestRepository;
import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.evento.EventRepository;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourRepository;
import com.unicam.City_Explore.notifica.NotificationListener;

import java.util.List;
import java.util.Scanner;

@Service
public class DeletionService {
    private Scanner scanner;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MultimediaContentRepository multimediaContentRepository;
    @Autowired
    private NotificationListener notificationListener;

    public DeletionService() {
    }

    public void deletePOI() {
        System.out.println("=== Eliminazione Punto di Interesse ===");
        System.out.print("Inserisci l'ID del POI da eliminare: ");
        int idPOI = scanner.nextInt();

        PointOfInterest poi = poiRepository.findById(idPOI).get();
        List<Event> eventList = poi.getEvents();
        List<MultimediaContent> multimediaContentList = poi.getMultimediaContentList();
        if(eventList.isEmpty()) {
            System.out.println("Non ci sono Eventi associati a questo Punto di Interesse.");
        } else {
            System.out.print("Il Punto di Interesse da eliminare si trova in questi eventi: ");
            for(Event e: eventList) {
                System.out.println(e);
            }
        }
        if(multimediaContentList.isEmpty()) {
            System.out.println("Non ci sono Contenuti associati a questo Punto di Interesse.");
        } else {
            System.out.print("Il Punto di Interesse da eliminare si trova in questi Contenuti: ");
            for(MultimediaContent mc: multimediaContentList) {
                System.out.println(mc);
            }
        }
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deletePOI(idPOI, reason);

        System.out.println("Il Punto di Interesse e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteTour() {
        System.out.println("=== Eliminazione Itinerario ===");
        System.out.print("Inserisci l'ID dell'Itinerario da eliminare: ");
        int idTour = scanner.nextInt();

        Tour tour = tourRepository.findById(idTour).get();
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deleteTour(tour.getId(), reason);
        System.out.println("L'Itinerario e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteContest() {
        System.out.println("=== Eliminazione Contest ===");
        System.out.print("Inserisci l'ID del Contest da eliminare: ");
        int idContest = scanner.nextInt();

        Contest contest = contestRepository.findById(idContest).get();
        List<Event> eventList = contest.getEventList();
        if(eventList.isEmpty()) {
            System.out.println("Non ci sono Eventi associati a questo Contest.");
        } else {
            System.out.print("Il Contest da eliminare si trova in questi eventi: ");
            for(Event e: eventList) {
                System.out.println(e);
            }
        }

        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deleteContest(idContest, reason);

        System.out.println("Il Contest e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteEvent() {
        System.out.println("=== Eliminazione Evento ===");
        System.out.print("Inserisci l'ID dell'Evento da eliminare: ");
        int idEvent = scanner.nextInt();

        Event event = eventRepository.findById(idEvent).get();
        List<PointOfInterest> pointOfInterestList = event.getPointOfInterestList();
        List<Contest> contestList = event.getContestList();
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non ci sono Punti di Interesse associati a questo Evento.");
        } else {
            System.out.print("L'Evento da eliminare si trova in questi Punti di Interesse: ");
            for(PointOfInterest p: pointOfInterestList) {
                System.out.println(p);
            }
        }

        if(contestList.isEmpty()) {
            System.out.println("Non ci sono Contest associati a questo Evento.");
        } else {
            System.out.print("L'Evento da eliminare si trova in questi Contest: ");
            for(Contest c: contestList) {
                System.out.println(c);
            }
        }
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione:");
        String reason = scanner.nextLine();
        deleteEvent(idEvent, reason);

        System.out.println("L'Evento e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteMultimediaContent() {
        System.out.println("=== Eliminazione Contenuto ===");

        System.out.print("Inserisci l'ID del Contenuto da eliminare: ");
        int idMC = scanner.nextInt();

        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).get();
        AbstractElement element = multimediaContent.getAttachedElement();
        if(element == null) {
            System.out.println("Non ci sono Punti di Interesse associati a questo Contenuto.");
        } else {
            System.out.print("Il Contenuto da eliminare si trova in questo Punto di Interesse: ");
            System.out.println(element);
        }
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deleteMultimediaContent(idMC, reason);

        System.out.println("Il Contest e' stato eliminato con successo, perche' " + reason);
    }

    public void deletePOI(int idPOI, String reason) {
            PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
            if (poi != null) {

                for (Event event : poi.getEvents()) {
                    event.getPointOfInterestList().remove(poi);
                    eventRepository.save(event);
                }

                for(Tappa tappa: poi.getTappe()) {
                    tappa.setPointOfInterest(null);
                }

                for (MultimediaContent multimediaContent : poi.getMultimediaContentList()) {
                	multimediaContentRepository.delete(multimediaContent);
                }
                poiRepository.deleteById(idPOI);
                notificationListener.handleDeletePOI(poi, reason);
            }
    }

    public void deleteTour(int idTour, String reason) {
            Tour tour = tourRepository.findById(idTour).orElse(null);
            if (tour != null) {
                for(Way way: tour.getWayList()) {
                    tour.getWayList().remove(way);
                    way.setTappe(null);
                }

                for (MultimediaContent multimediaContent : tour.getMultimediaContentList()) {
                	multimediaContentRepository.delete(multimediaContent);
                }
                tourRepository.deleteById(tour.getId());
                notificationListener.handleDeleteTour(tour, reason);
            }
    }

    public void deleteContest(int idContest, String reason) {
            Contest contest = contestRepository.findById(idContest).orElse(null);
            if (contest != null) {
                for (Event event : contest.getEventList()) {
                    event.getContestList().remove(contest);
                    eventRepository.save(event);
                }
                for(ContestParticipation participant: contest.getParticipationContestList()) {
                    contest.getParticipationContestList().remove(participant);
                }
                for (MultimediaContent multimediaContent : contest.getMultimediaContentList()) {
                	multimediaContentRepository.delete(multimediaContent);
                }
                contestRepository.deleteById(idContest);
                notificationListener.handleDeleteContest(contest, reason);
            }
    }

    public void deleteEvent(int idEvent, String reason) {
            Event event = eventRepository.findById(idEvent).orElse(null);
            if (event != null) {
                for (PointOfInterest poi : event.getPointOfInterestList()) {
                    poi.getEvents().remove(event);
                    poiRepository.save(poi);
                }
                for (Contest contest : event.getContestList()) {
                    contest.getEventList().remove(event);
                    contestRepository.save(contest);
                }
                for (MultimediaContent multimediaContent : event.getMultimediaContentList()) {
                	multimediaContentRepository.delete(multimediaContent);
                }
                eventRepository.deleteById(idEvent);
                notificationListener.handleDeleteEvent(event, reason);
            }
    }

    public void deleteMultimediaContent(int idMC, String reason) {
            MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
            if (multimediaContent != null) {
            	AbstractElement element = multimediaContent.getAttachedElement();
                if(element instanceof PointOfInterest) {
                	PointOfInterest poi = (PointOfInterest) element;
                	poi.getMultimediaContentList().remove(multimediaContent);
                    poiRepository.save(poi);
                } else if (element instanceof Tour) {
                	Tour tour = (Tour) element;
                	tour.getMultimediaContentList().remove(multimediaContent);
                    tourRepository.save(tour);
                } else if (element instanceof Event) {
                	Event evento = (Event) element;
                	evento.getMultimediaContentList().remove(multimediaContent);
                    eventRepository.save(evento);
                } else if (element instanceof Contest) {
                	Contest contest = (Contest) element;
                	contest.getMultimediaContentList().remove(multimediaContent);
                    contestRepository.save(contest);
                }
                multimediaContentRepository.deleteById(idMC);
                notificationListener.handleDeleteMultimediaContent(multimediaContent, reason);
            }
        }
    
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
