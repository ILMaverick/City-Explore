package com.unicam.City_Explore.evento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.unicam.City_Explore.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.notifica.NotificationListener;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserRepository;

@Service
public class EventService {
    private Scanner scanner;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private UserRepository userRepository;

    public EventService() {
        // Inizializza lo scanner (non lo chiudiamo subito, per evitare di chiudere System.in)
        scanner = new Scanner(System.in);
    }

    public void initializer() {
        User user = new User();
        user.setName("Simone");
        user.setSurname("Stacchiotti");
        user.setUsername("SilverSimon");
        user.setEmail("simone.stacchiotti.email@gmail.com");
        user.setRole(Role.ADMINISTRATOR);
        LocalDateTime time = LocalDateTime.of(2025,02,17, 15,48);

        createEvent("nome", "descrizione", user, "Raccolta fondi", "Gioco libero",
                "SilverSimonCorp", "Fantasy", "Giochi in presenza", 0.0, time);

    }

    /**
     * Crea un Evento a partire dai dati inseriti dall'utente.
     * L'utente visualizza la lista dei POI presenti (recuperati dal POIService),
     * seleziona il POI da utilizzare come location per l'evento, poi inserisce i campi richiesti.
     */
    public void createEventFromInput() {
        System.out.println("=== Creazione di un nuovo Evento ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
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

        Event event = addEventToPOI(idPOI, idEvent);

        System.out.println("Evento aggiunto al Punto di Interesse: ");
        System.out.print(event);
    }

    public void updateEvent() {
        System.out.println("=== Aggiorna Evento ===");

        System.out.print("Inserisci l'ID dell'Evento: ");
        int idEvent = scanner.nextInt();

        Event selectedEvent = getEventById(idEvent);
        System.out.println(selectedEvent);
        if(selectedEvent != null) {

            System.out.println("Per aggiornare l'Evento, inserire i dati negli appositi campi, compresi quelli del precedente");

            scanner.nextLine();

            System.out.print("Inserisci il nome: ");
            String name = scanner.nextLine();

            System.out.print("Inserisci la descrizione: ");
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

        } else {
            System.out.println("L'Evento non e' presente ");
        }
    }

    public void searchEventByName() {

        System.out.println("=== Ricerca Contenuti Multimediali tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<Event> eventList = searchEventByName(name);
        if(eventList.isEmpty()) {
            System.out.println("Non e' presente un Evento con questo nome.");
        } else {
            System.out.println("Elenco Eventi con il nome cercato:");
            for(Event event: eventList) {
                System.out.println(event);
            }
        }
    }

    public void searchEventByDescription() {
        System.out.println("=== Ricerca Contenuti Multimediali tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Event> eventList = searchEventByDescription(description);
        if(eventList.isEmpty()) {
            System.out.println("Non e' presente un Evento con questa descrizione.");
        } else {
            System.out.println("Elenco Eventi con la descrizione cercata:");
            for(Event event: eventList) {
                System.out.println(event);
            }
        }
    }

    /**
     * Crea un nuovo Evento a partire dai dati forniti.
     */
    public Event createEvent(String name, String description, User author, String scope,
                             String activity, String organization, String theme, String category,
                             double price, LocalDateTime time) {
        if(author.getRole() == Role.ANIMATOR) {
            Event event = new Event(name, description, author, scope, activity, organization, theme, category, price, time);
            eventRepository.save(event);
            notificationListener.handleNewEvent(event);
            return event;
        } else {
            notificationListener.handleDenialPermission(author);
        }
        return null;
    }

    /**
     * Aggiungi un Evento a un Punto di Interesse
     */

    public Event addEventToPOI(int idPOI, int idEvent) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        Event event = getEventById(idEvent);
        User animator = event.getAuthor();
        if(animator.getRole() == Role.ANIMATOR) {
            if (poi != null) {
                event.getPointOfInterestList().add(poi);
                eventRepository.save(event);
                poi.getEvents().add(event);
                poiRepository.save(poi);
                notificationListener.handleAddEventToPOI(event, poi);
            } else {
                System.out.println("Punto di interesse non trovato.");
            }
            return event;
        } else {
            notificationListener.handleDenialPermission(animator);
        }
        return null;
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
        return eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(int idEvent, Event event) {
        User animator = event.getAuthor();
        if(animator.getRole() == Role.ANIMATOR) {
            Event eventSelected = eventRepository.findById(idEvent).orElse(null);
            if (eventSelected != null) {
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
                notificationListener.handleUpdateEvent(event);
            }
            return eventSelected;
        } else {
            notificationListener.handleDenialPermission(animator);
        }
        return null;
    }

    public List<PointOfInterest> getAllPoiFromEventRepository() {
        return poiRepository.findAll();
    }

    public List<Event> searchEventByName(String name) {
        return eventRepository.searchByName(name);
    }

    public List<Event> searchEventByDescription(String description) {
        return eventRepository.searchByDescription(description);
    }

    public void participateEvent(int idEvent, int idUser) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        Optional<User> optionalUser = userRepository.findById(idUser);

        if(optionalEvent.isPresent() && optionalUser.isPresent()) {
            Event event = optionalEvent.get();
            User user = optionalUser.get();
            if(user.getRole() == Role.TOURIST || user.getRole() == Role.AUTHENTICATED_TOURIST) {
                user.getEventList().add(event);
                event.getParticipants().add(user);
                eventRepository.save(event);
                notificationListener.handleParticipationEvent(event, user);
            } else {
                notificationListener.handleDenialPermission(user);
            }
        } else {
            throw new RuntimeException("Evento o Utente non trovato.");
        }
    }

    public void deleteParticipateEvent(int idEvent, int idUser, String reason) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        Optional<User> optionalUser = userRepository.findById(idUser);

        if(optionalEvent.isPresent() && optionalUser.isPresent()) {
            Event event = optionalEvent.get();
            User user = optionalUser.get();
            if(user.getRole() == Role.TOURIST || user.getRole() == Role.AUTHENTICATED_TOURIST) {
                user.getEventList().remove(event);
                event.getParticipants().remove(user);
                eventRepository.save(event);
                notificationListener.handleDeleteParticipationEvent(event, user, reason);
            } else {
                notificationListener.handleDenialPermission(user);
            }
        } else {
            throw new RuntimeException("Evento o Utente non trovato.");
        }
    }


    // Metodo dummy per ottenere l'utente corrente (da sostituire con logica reale)
    private User getCurrentUser() {
        User user = new User();
        user.setName("utente");
        user.setSurname("demo");
        user.setUsername("utente_demo");
        user.setEmail("utente_demo.mail@gmail.com");
        user.setPassword("1234567890");
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

}

