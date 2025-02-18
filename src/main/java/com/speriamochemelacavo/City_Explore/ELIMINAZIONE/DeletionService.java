package com.speriamochemelacavo.City_Explore.ELIMINAZIONE;

import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContent;
import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContentRepository;
import com.speriamochemelacavo.City_Explore.CONTEST.Contest;
import com.speriamochemelacavo.City_Explore.CONTEST.ContestRepository;
import com.speriamochemelacavo.City_Explore.EVENTO.Event;
import com.speriamochemelacavo.City_Explore.EVENTO.EventRepository;
import com.speriamochemelacavo.City_Explore.POI.POIRepository;
import com.speriamochemelacavo.City_Explore.POI.PointOfInterest;
import com.speriamochemelacavo.City_Explore.TOUR.Tour;
import com.speriamochemelacavo.City_Explore.TOUR.TourRepository;
import com.speriamochemelacavo.City_Explore.USER.User;

import java.util.List;
import java.util.Scanner;

public class DeletionService {
    private Scanner scanner;
    private POIRepository poiRepository;
    private TourRepository tourRepository;
    private ContestRepository contestRepository;
    private EventRepository eventRepository;
    private MultimediaContentRepository multimediaContentRepository;

    public DeletionService(POIRepository poiRepository, TourRepository tourRepository, ContestRepository contestRepository,
                           EventRepository eventRepository, MultimediaContentRepository multimediaContentRepository) {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.poiRepository = poiRepository;
        this.tourRepository = tourRepository;
        this.contestRepository = contestRepository;
        this.eventRepository = eventRepository;
        this.multimediaContentRepository = multimediaContentRepository;
    }

    public void deletePOI() {
        System.out.println("=== Eliminazione Punto di Interesse ===");
        System.out.print("Inserisci l'ID del POI da eliminare: ");
        int idPOI = scanner.nextInt();

        PointOfInterest poi = poiRepository.findById(idPOI);
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
        deletePOI(idPOI);

        System.out.println("Il Punto di Interesse e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteTour() {
        System.out.println("=== Eliminazione Itinerario ===");
        System.out.print("Inserisci l'ID dell'Itinerario da eliminare: ");
        int idTour = scanner.nextInt();

        Tour tour = tourRepository.findById(idTour);
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deleteTour(tour.getId());
        System.out.println("L'Itinerario e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteContest() {
        System.out.println("=== Eliminazione Contest ===");
        System.out.print("Inserisci l'ID del Contest da eliminare: ");
        int idContest = scanner.nextInt();

        Contest contest = contestRepository.findById(idContest);
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
        deleteContest(idContest);

        System.out.println("Il Contest e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteEvent() {
        System.out.println("=== Eliminazione Evento ===");
        System.out.print("Inserisci l'ID dell'Evento da eliminare: ");
        int idEvent = scanner.nextInt();

        Event event = eventRepository.findById(idEvent);
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
        deleteEvent(idEvent);

        System.out.println("L'Evento e' stato eliminato con successo, perche' " + reason);
    }

    public void deleteMultimediaContent() {
        System.out.println("=== Eliminazione Contenuto ===");

        System.out.print("Inserisci l'ID del Contenuto da eliminare: ");
        int idMC = scanner.nextInt();

        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC);
        PointOfInterest pointOfInterest = multimediaContent.getPointOfInterest();
        if(pointOfInterest == null) {
            System.out.println("Non ci sono Punti di Interesse associati a questo Contenuto.");
        } else {
            System.out.print("Il Contenuto da eliminare si trova in questo Punto di Interesse: ");
            System.out.println(pointOfInterest);
        }
        scanner.nextLine();
        System.out.print("Aggiungi una motivazione per l'eliminazione: ");
        String reason = scanner.nextLine();
        deleteMultimediaContent(idMC);

        System.out.println("Il Contest e' stato eliminato con successo, perche' " + reason);
    }

    public void deletePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        List<Event> eventList = poi.getEvents();
        //List<Tour> tourList = poi.getTourList();
        List<MultimediaContent> multimediaContentList = poi.getMultimediaContentList();
        for (Event event : eventList) {
            event.getPointOfInterestList().remove(poi);
            eventRepository.save(event);
        }
        //TODO: Aggiungere Controllo per il Tour

        for (MultimediaContent multimediaContent : multimediaContentList) {
            multimediaContent.setPointOfInterest(null);
            multimediaContentRepository.save(multimediaContent);
        }
        poiRepository.deleteByID(idPOI);
    }

    public void deleteTour(int idTour) {
        Tour tour = tourRepository.findById(idTour);
        tourRepository.deleteByID(idTour);
    }

    public void deleteContest(int idContest) {
        Contest contest = contestRepository.findById(idContest);
        List<Event> eventList = contest.getEventList();
        for (Event event : eventList) {
            event.getPointOfInterestList().remove(contest);
            eventRepository.save(event);
        }
        contestRepository.deleteByID(idContest);
    }

    public void deleteEvent(int idEvent) {
        Event event = eventRepository.findById(idEvent);
        List<PointOfInterest> pointOfInterestList = event.getPointOfInterestList();
        List<Contest> contestList = event.getContestList();
        for (PointOfInterest poi : pointOfInterestList) {
            poi.getEvents().remove(event);
            poiRepository.save(poi);
        }
        for (Contest contest : contestList) {
            contest.getEventList().remove(event);
            contestRepository.save(contest);
        }
        eventRepository.deleteByID(idEvent);
    }

    public void deleteMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC);
        PointOfInterest pointOfInterest = multimediaContent.getPointOfInterest();
        pointOfInterest.getMultimediaContentList().remove(multimediaContent);
        poiRepository.save(pointOfInterest);
        multimediaContentRepository.deleteByID(idMC);
    }

    private User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }

    // Se necessario, aggiungi un metodo per chiudere lo scanner quando il controller non serve più.
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
