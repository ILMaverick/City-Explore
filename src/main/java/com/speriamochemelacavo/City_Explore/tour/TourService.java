package tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import poi.POIRepository;
import poi.PointOfInterest;
import user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private Scanner scanner;
    @Autowired
	 private TourRepository tourRepository;
    @Autowired
     private POIRepository poiRepository;

     public TourService() {
            scanner = new Scanner(System.in);
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

    public void searchTourByName() {
        System.out.println("=== Ricerca Itinerario tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<Tour> tourList = searchTourByName(name);
        if(tourList.isEmpty()) {
            System.out.println("Non e' presente un Itinerario con questo nome.");
        } else {
            System.out.println("Elenco Itinerari con il nome cercato:");
            for(Tour tour: tourList) {
                System.out.println(tour);
            }
        }
    }


    public void searchTourByDescription() {
        System.out.println("=== Ricerca Itinerario tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<Tour> tourList = searchTourByName(description);
        if(tourList.isEmpty()) {
            System.out.println("Non e' presente un Itinerario con questa descrizione.");
        } else {
            System.out.println("Elenco Itinerari con la descrizione cercata:");
            for(Tour tour: tourList) {
                System.out.println(tour);
            }
        }
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
        
        for (int i = 0; i < total - 1; i++) {
            gruppi.add(Arrays.asList(tappe.get(i), tappe.get(i + 1)));
        }
        
        return gruppi;
        }


    public Tour updateTour(int idTour, Tour tour) {
        Tour tourSelected = getTourById(idTour);
        if(tourSelected != null) {
            tourSelected.setName(tour.getName());
            tourSelected.setDescription(tour.getDescription());
            tourSelected.setWayList(tour.getWayList());
            tourRepository.save(tourSelected);
        }
        return tourSelected;
    }

    public List<Tour> searchTourByName(String name) {
        return tourRepository.searchByName(name);
    }

    public List<Tour> searchTourByDescription(String description) {
        return tourRepository.searchByDescription(description);
    }

    public void save(Tour tour) {
        tourRepository.save(tour);
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourById(int id) {
        return tourRepository.findById(id).get();
    }

    // Metodo dummy per ottenere l'utente attuale (da sostituire con logica reale)
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
}
