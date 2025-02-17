package TOUR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import POI.InMemoryPOIRepository;
import POI.POIRepository;
import POI.POIService;
import POI.PointOfInterest;
import USER.User;

public class TourService {
    private Scanner scanner;
	 private TourRepository tourRepository;
     private POIRepository poiRepository;
     public TourService(TourRepository tourRepository, POIRepository poiRepository) {
            scanner = new Scanner(System.in);
	        this.tourRepository = tourRepository;
            this.poiRepository = poiRepository;
     }


    /**
     * Crea un Tour a partire da una lista di POI.
     * L'utente seleziona i POI da includere e inserisce le informazioni aggiuntive per la creazione dei percorsi.
     * Il Tour viene poi costruito tramite il TourService (che usa il TourBuilder) e salvato.
     */
    public void createTourFromPOIs() {
        System.out.println("=== Creazione di un Tour a partire da POI ===");

        // Recupera la lista dei POI già salvati
        List<PointOfInterest> poiList = poiRepository.findAll();
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
        Tour tour = buildTourFromPOIs(selectedPOIs, currentUser);

        // Salva il Tour
        save(tour);

        System.out.println("\nTour creato e salvato con successo:");
        System.out.println(tour);
    }
    
    /**
     * Metodo interattivo per costruire un Tour a partire da una lista di PointOfInterest.
     */
    public Tour buildTourFromPOIs(List<PointOfInterest> poiList, User author) {
        // 1. Crea le tappe a partire dai POI
        List<Tappa> tappe = new ArrayList<>();
        int count = 1;
        for (PointOfInterest poi : poiList) {
            Tappa tappa = new Tappa(poi.getName(), poi.getDescription(), poi.getLatitude(), poi.getLongitude(), poi.getAuthor(), poi.getType(), count++);
            tappe.add(tappa);
        }
        
        // 2. Raggruppa le tappe in gruppi per formare i percorsi
        List<List<Tappa>> gruppiTappe = groupTappe(tappe);
        
        Scanner scanner = new Scanner(System.in);
        List<Way> percorsi = new ArrayList<>();
        
        // 3. Per ogni gruppo, chiedi i dettagli e crea il Percorso
        for (List<Tappa> gruppo : gruppiTappe) {
            System.out.println("\nCreazione di un Percorso per le seguenti tappe:");
            for (Tappa t : gruppo) {
                System.out.println("Tappa " + t.getNumeroTappa() + ": " + t.getName());
            }
            System.out.print("Inserisci la lunghezza del percorso (double): ");
            double lunghezza = Double.parseDouble(scanner.nextLine());
            System.out.print("Inserisci la durata del percorso (double): ");
            double durata = Double.parseDouble(scanner.nextLine());
            System.out.print("Inserisci la difficoltà del percorso (facile, medio, difficile): ");
            String diff = scanner.nextLine().trim();
            WayDifficultyType wayDifficultyType = WayDifficultyType.fromString(diff);
            
            Way way = new Way(lunghezza, durata, wayDifficultyType, gruppo);
            percorsi.add(way);
        }
        
        // 4. Raccogli nome e descrizione del Tour
        System.out.print("\nInserisci il nome del Tour: ");
        String tourName = scanner.nextLine();
        System.out.print("Inserisci la descrizione del Tour: ");
        String tourDescription = scanner.nextLine();
        
        // Usa il builder per costruire il Tour
        Tour tour = new TourBuilder()
                        .withName(tourName)
                        .withDescription(tourDescription)
                        .withAuthor(author)
                        .build();
        // Aggiungi i percorsi al Tour (puoi anche aggiungerli uno ad uno)
        for (Way p : percorsi) {
            // Supponendo che il builder non gestisca i percorsi, li settiamo direttamente sul tour
            tour.getWayList().add(p);
        }
        save(tour);
        // scanner.close(); // Attenzione a non chiudere System.in se usato altrove
        return tour;
    }
    
    /**
     * Raggruppa le tappe in gruppi per la creazione dei percorsi.
     */
    private List<List<Tappa>> groupTappe(List<Tappa> tappe) {
        List<List<Tappa>> gruppi = new ArrayList<>();
        int total = tappe.size();
        
        if (total >= 3 && total % 2 == 1) {
            for (int i = 0; i < total - 3; i += 2) {
                gruppi.add(new ArrayList<>(tappe.subList(i, i + 2)));
            }
            gruppi.add(new ArrayList<>(tappe.subList(total - 3, total)));
        } else {
            for (int i = 0; i < total; i += 2) {
                if (i + 1 < total) {
                    gruppi.add(new ArrayList<>(tappe.subList(i, i + 2)));
                } else {
                    gruppi.add(new ArrayList<>(tappe.subList(i, total)));
                }
            }
        }
        return gruppi;
    }

    public void save(Tour tour) {
        tourRepository.save(tour);
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourById(int id) {
        return tourRepository.findById(id);
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
