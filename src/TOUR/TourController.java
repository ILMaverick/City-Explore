package TOUR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import POI.POIService;
import POI.PointOfInterest;
import USER.User;

public class TourController {
    private Scanner scanner;
    private POIService poiService;
    private TourService tourService;
    
    public TourController(POIService poiService) {
        scanner = new Scanner(System.in);
        this.poiService = poiService;
        TourRepository repository = new InMemoryTourRepository();
        this.tourService = new TourService(repository);
    }

    public TourService getTourService() {
        return tourService;
    }
    
    /**
     * Crea un Tour a partire da una lista di POI.
     * L'utente seleziona i POI da includere e inserisce le informazioni aggiuntive per la creazione dei percorsi.
     * Il Tour viene poi costruito tramite il TourService (che usa il TourBuilder) e salvato.
     */
    public void createTourFromPOIs() {
        System.out.println("=== Creazione di un Tour a partire da POI ===");
        
        // Recupera la lista dei POI già salvati
        List<PointOfInterest> poiList = poiService.getAllPOIs();
        if (poiList == null || poiList.isEmpty()) {
            System.out.println("Nessun POI disponibile per creare un Tour.");
            return;
        }
        
        // Visualizza la lista dei POI con indice
        System.out.println("Elenco dei POI disponibili:");
        for (int i = 0; i < poiList.size(); i++) {
            System.out.println((i + 1) + ". " + poiList.get(i));
        }
        
        // L'utente seleziona i POI da includere, separati da virgola (es. "1,3,5")
        System.out.print("Seleziona i POI da includere nel Tour (inserisci i numeri separati da virgola): ");
        String input = scanner.nextLine();
        String[] tokens = input.split(",");
        List<PointOfInterest> selectedPOIs = new ArrayList<>();
        for (String token : tokens) {
            try {
                int index = Integer.parseInt(token.trim());
                if (index >= 1 && index <= poiList.size()) {
                    selectedPOIs.add(poiList.get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido: " + token);
            }
        }
        
        if (selectedPOIs.isEmpty()) {
            System.out.println("Nessun POI selezionato.");
            return;
        }
        
        // Recupera l'utente corrente (dummy)
        User currentUser = getCurrentUser();
        
        // Costruisce il Tour a partire dai POI selezionati.
        // Il metodo buildTourFromPOIs chiederà all'utente informazioni aggiuntive per la creazione dei percorsi.
        Tour tour = tourService.buildTourFromPOIs(selectedPOIs, currentUser);
        
        // Salva il Tour
        tourService.saveTour(tour);
        
        System.out.println("\nTour creato e salvato con successo:");
        System.out.println(tour);
    }
    
    /**
     * Visualizza tutti i Tour salvati.
     */
    public void displayAllTours() {
        List<Tour> tours = tourService.getAllTours();
        if (tours == null || tours.isEmpty()) {
            System.out.println("Nessun Tour salvato.");
        } else {
            System.out.println("Elenco dei Tour salvati:");
            for (Tour tour : tours) {
                System.out.println(tour);
            }
        }
    }
    
    // Metodo dummy per ottenere l'utente attuale (da sostituire con logica reale)
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
