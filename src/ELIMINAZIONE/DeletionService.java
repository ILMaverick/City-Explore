package ELIMINAZIONE;

import EVENTO.Event;
import EVENTO.EventRepository;
import POI.POIRepository;
import POI.PointOfInterest;
import USER.User;

import java.util.List;
import java.util.Scanner;

public class DeletionService {
    private Scanner scanner;
    private POIRepository poiRepository;
    private EventRepository eventRepository;

    public DeletionService(POIRepository poiRepository, EventRepository eventRepository) {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.poiRepository = poiRepository;
        this.eventRepository = eventRepository;
    }

    public void deletePOI() {
        System.out.println("=== Eliminazione Punti di Interesse ===");
        System.out.print("Inserisci l'ID del POI da eliminare: ");
        int idPOI = scanner.nextInt();

        PointOfInterest poi = poiRepository.findById(idPOI);
        List<Event> eventList = getEventsAssociatedToPOI(poi);
        if(eventList.isEmpty()) {
            System.out.println("Non ci sono Eventi associati a questo Punto di Interesse.");
        } else {
            System.out.print("Il Punto di Interesse da eliminare si trova in questi eventi: ");
            for(Event e: eventList) {
                System.out.println(e);
            }
        }
        deletePOI(idPOI);
        System.out.println("Il Punto di Interesse e' stato eliminato con successo");
    }

    public void deletePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        List<Event> eventList = getEventsAssociatedToPOI(poi);
        for (Event event : eventList) {
            event.setLocation(null);
            eventRepository.save(event);
        }
        poiRepository.deleteByID(idPOI);
    }

    public List<Event> getEventsAssociatedToPOI(PointOfInterest pointOfInterest) {
        return eventRepository.findByPOI(pointOfInterest);
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
