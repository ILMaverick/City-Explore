package EVENTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import POI.POIRepository;
import POI.PointOfInterest;
import USER.User;

public class EventService {
    private Scanner scanner;
    private EventRepository eventRepository;
    private POIRepository poiRepository;

    public EventService(EventRepository eventRepository, POIRepository poiRepository) {
        // Inizializza lo scanner (non lo chiudiamo subito, per evitare di chiudere System.in)
        scanner = new Scanner(System.in);
        this.eventRepository = eventRepository;
        this.poiRepository = poiRepository;
    }

    public void initializer() {
        User user = new User();
        user.setUsername("SilverSimon");

        LocalDateTime time = LocalDateTime.of(2025,02,17, 15,48);

        createEvent("nome", "descrizione", user, "Raccolta fondi", "Gioco libero",
                "SilverSimonCorp", "Fantasy", "Giochi in presenza", 0.0, time);

    }
    
    /**
     * Crea un nuovo Evento a partire dai dati forniti.
     */
    public Event createEvent(String name, String description, User author, String scope,
                             String activity, String organization, String theme, String category,
                             double price, LocalDateTime time) {

        Event event = new Event(name, description, author, scope, activity, organization, theme, category, price, time);
        eventRepository.save(event);
        return event;
    }

    /**
     * Aggiungi un Evento a un Punto di Interesse
     */

    public PointOfInterest addEventToPOI(int idPOI, int idEvent) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        Event event = eventRepository.findById(idEvent);
        if (poi != null) {
            System.out.println("Punto di interesse trovato: ");
            event.setLocation(poi);
            eventRepository.save(event);
            poi.setEvent(event);
            poiRepository.save(poi);
        } else {
            System.out.println("Punto di interesse non trovato.");
        }
        return poi;
    }

    /**
     * Crea un Evento a partire dai dati inseriti dall'utente.
     * L'utente visualizza la lista dei POI presenti (recuperati dal POIService),
     * seleziona il POI da utilizzare come location per l'evento, poi inserisce i campi richiesti.
     */
    public void createEventFromInput() {
        System.out.println("=== Creazione di un nuovo Evento ===");

        System.out.print("Inserisci il nome dell'evento: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione dell'evento: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci lo scope: ");
        String scope = scanner.nextLine();

        System.out.print("Inserisci l'attività: ");
        String activity = scanner.nextLine();

        System.out.print("Inserisci l'organizzazione: ");
        String organization = scanner.nextLine();

        System.out.print("Inserisci il tema: ");
        String theme = scanner.nextLine();

        System.out.print("Inserisci la categoria: ");
        String category = scanner.nextLine();

        System.out.print("Inserisci il prezzo (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci l'orario formato: dd-MM-yyyy HH:mm): ");
        //Formato data e ora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // Legge l'input dell'utente
        String timeString = scanner.nextLine();
        // Converte la stringa in un oggetto LocalDateTime
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        // Ottieni l'utente corrente (dummy, o da un servizio di autenticazione)
        User currentUser = getCurrentUser();

        // Crea l'evento tramite il service
        Event event = createEvent(name, description, currentUser, scope, activity, organization, theme, category, price, time);

        System.out.println("\nEvento creato e salvato con successo:");
        System.out.println(event);
    }


    public void addEventToPOI() {
        System.out.println("=== Aggiungi Evento ad un Punto di Interesse ===");

        System.out.print("Inserisci l'ID del Punto di Interesse: ");
        int idPOI = scanner.nextInt();

        System.out.print("Inserisci l'ID dell'Evento: ");
        int idEvent = scanner.nextInt();

        PointOfInterest poi = addEventToPOI(idPOI, idEvent);

        System.out.println("Evento aggiunto al Punto di Interesse: ");
        //System.out.print(poi);
    }

    public void updateEvent() {
        System.out.println("=== Aggiorna Evento ===");

        System.out.print("Inserisci l'ID dell'Evento: ");
        int idEvent = scanner.nextInt();

        Event selectedEvent = getEventById(idEvent);
        System.out.println(selectedEvent);

        System.out.print("Inserisci il nome dell'evento: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione dell'evento: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci lo scope: ");
        String scope = scanner.nextLine();

        System.out.print("Inserisci l'attività: ");
        String activity = scanner.nextLine();

        System.out.print("Inserisci l'organizzazione: ");
        String organization = scanner.nextLine();

        System.out.print("Inserisci il tema: ");
        String theme = scanner.nextLine();

        System.out.print("Inserisci la categoria: ");
        String category = scanner.nextLine();

        System.out.print("Inserisci il prezzo (double): ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci l'orario formato: dd-MM-yyyy HH:mm): ");
        //Formato data e ora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // Legge l'input dell'utente
        String timeString = scanner.nextLine();
        // Converte la stringa in un oggetto LocalDateTime
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        // Ottieni l'utente corrente (dummy, o da un servizio di autenticazione)
        User currentUser = getCurrentUser();

        Event newEvent = new Event(name, description, currentUser, scope, activity, organization, theme, category, price, time);

        selectedEvent = updateEvent(idEvent, newEvent);

        System.out.println("Evento aggiornato: ");
        System.out.println(selectedEvent);
    }

    /**
     * Salva l'Evento nella repository.
     */
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
    
    /**
     * Restituisce tutti gli eventi salvati.
     */
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }
    
    /**
     * Restituisce un Evento dato il suo id.
     */
    public Event getEventById(int id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(int id, Event event) {
        Event eventSelected = eventRepository.findById(id);
        if(eventSelected != null) {
            eventSelected.setName(event.getName());
            eventSelected.setDescription(event.getDescription());
            eventSelected.setScope(event.getScope());
            eventSelected.setActivity(event.getActivity());
            eventSelected.setOrganization(event.getOrganization());
            eventSelected.setTheme(event.getTheme());
            eventSelected.setCategory(event.getCategory());
            eventSelected.setTime(event.getTime());
            eventSelected.setPrice(event.getPrice());
            eventRepository.save(eventSelected);
            return eventSelected;
        }
        return null;
    }

    // Metodo dummy per ottenere l'utente corrente (da sostituire con logica reale)
    private User getCurrentUser() {
        User user = new User();
        user.setUsername("utente_demo");
        return user;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Restituisce tutti i poi.
     */
    public List<PointOfInterest> getAllPoiFromEventRepository() {
        return poiRepository.findAll();
    }
}

