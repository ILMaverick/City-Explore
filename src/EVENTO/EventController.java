package EVENTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import POI.InMemoryPOIRepository;
import POI.POIService;
import POI.PointOfInterest;
import USER.User;

public class EventController {
    private Scanner scanner;
    private POIService poiService;// Servizio che fornisce i POI già salvati
    private EventService eventService; // Service per la gestione degli eventi

    public EventController() {
        // Inizializza lo scanner (non lo chiudiamo subito, per evitare di chiudere System.in)
        scanner = new Scanner(System.in);
        this.eventService = new EventService();
    }
    
    /**
     * Crea un Evento a partire dai dati inseriti dall'utente.
     * L'utente visualizza la lista dei POI presenti (recuperati dal POIService),
     * seleziona il POI da utilizzare come location per l'evento, poi inserisce i campi richiesti.
     */
    public void createEventFromInput() {
        System.out.println("=== Creazione di un nuovo Evento ===");
        
        // Recupera la lista dei POI già salvati
        List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList == null || poiList.isEmpty()) {
            System.out.println("Nessun POI disponibile per creare l'evento.");
            return;
        }
        
        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println((i + 1) + ". " + poiList.get(i));
        }
        
        // L'utente seleziona il POI da utilizzare
        System.out.print("Seleziona il POI da utilizzare (inserisci il numero): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline
        
        if (selection < 1 || selection > poiList.size()) {
            System.out.println("Selezione non valida.");
            return;
        }
        PointOfInterest selectedPOI = poiList.get(selection - 1);
        
        // Richiedi gli altri dati per la creazione dell'evento
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
        Event event = eventService.createEvent(name, description, currentUser, scope, activity, organization, theme, category, price, selectedPOI, time);
        // Salva l'evento nella repository
        eventService.saveEvent(event);
        
        System.out.println("\nEvento creato e salvato con successo:");
        System.out.println(event);
    }
    
    /**
     * Visualizza tutti gli eventi salvati.
     */
    public void displayAllEvents() {
        List<Event> eventList = eventService.getAllEvent();
        if (eventList == null || eventList.isEmpty()) {
            System.out.println("Nessun evento salvato.");
        } else {
            System.out.println("Elenco degli eventi salvati:");
            for (Event e : eventList) {
                System.out.println(e);
            }
        }
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
}
