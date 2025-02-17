package POI;

import java.util.List;
import java.util.Scanner;

import EVENTO.Event;
import OSM.OSMSearchService;
import OSM.OverpassElement;
import USER.User;

public class POIService {
    // Scanner condiviso per l'input da riga di comando.
    private Scanner scanner;
    private POIRepository poiRepository;

    public POIService(POIRepository poiRepository) {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.poiRepository = poiRepository;
    }

    //Inizializza dei Punti di Interesse
    public void initializer() {
        User user = new User();
        user.setUsername("SilverSimon");

        createPOIFromScratch("primo", "primo", 1, 1, user, POIType.Turismo);
        createPOIFromScratch("secondo", "secondo", 2, 2, user, POIType.Alloggio);
        createPOIFromScratch("terzo", "terzo", 3, 3, user, POIType.Natura);
    }

    public PointOfInterest createPOIFromScratch(String name, String description, double lat, double lon, User author, POIType type) {
        PointOfInterest poi = PointOfInterestFactory.create(name, description, lat, lon, author, type);
        poiRepository.save(poi);
        return poi;
    }
    
    public PointOfInterest createPOIFromOSM(OverpassElement element, User author, POIType type) {
        PointOfInterest poi = PointOfInterestFactory.createFromOverpassElement(element, author, type);
        poiRepository.save(poi);
        return poi;
    }

    /**
     * Crea un PointOfInterest partendo da zero, con input forniti dall'utente.
     */
    public void createPOIFromScratch() {
        System.out.println("=== Creazione di un nuovo PointOfInterest da zero ===");

        System.out.print("Inserisci il nome: ");
        String name = scanner.nextLine();

        System.out.print("Inserisci la descrizione: ");
        String description = scanner.nextLine();

        System.out.print("Inserisci la latitudine: ");
        double lat = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci la longitudine: ");
        double lon = Double.parseDouble(scanner.nextLine());

        // In questo esempio, open_time e close_time sono lasciati null
        // Chiediamo anche il tipo di POI (da un enum: Turismo, Alloggio, Servizio, Natura, Altro)
        System.out.print("Inserisci il tipo di POI (Turismo, Alloggio, Servizio, Natura, Altro): ");
        String typeInput = scanner.nextLine().trim();
        POIType poiType;
        try {
            poiType = POIType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo non valido. Verrà usato 'Altro'.");
            poiType = POIType.Altro;
        }

        // Ottieni l'utente corrente (dummy)
        User currentUser = getCurrentUser();

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = createPOIFromScratch(name, description, lat, lon, currentUser, poiType );


        System.out.println("\nPointOfInterest creato da zero:");
        System.out.println(newPoi);
    }

    /**
     * Crea un PointOfInterest a partire dai dati ottenuti dalla ricerca OSM.
     * Richiama il metodo search() di OSMSearchService, mostra i risultati numerati
     * e consente all'utente di selezionarne uno per creare il POI.
     */
    public void createPOIFromOSM() {
        System.out.println("=== Creazione di un PointOfInterest a partire da OSM ===");

        // Esegui la ricerca tramite il servizio
        OSMSearchService searchService = new OSMSearchService();

        System.out.print("Inserisci il nome della città: ");
        String city = scanner.nextLine();
        System.out.print("Inserisci il tipo di POI:\n"
                + "-TURISMO: monumenti, musei, quartieri_storici, teatri, luoghi_culto, zone_pedonali, planetari\n"
                + "-ALLOGGI: hotels, motels, ostelli, guest_house\n"
                + "-SERVIZI: scuole, università, ospedali, farmacie, cinema, mercati, ristoranti\n"
                + "-NATURA: parchi, foreste, vette, vigneti, spiagge");
        String poi = scanner.nextLine();

        List<OverpassElement> results = searchService.search(city, poi);

        if (results == null || results.isEmpty()) {
            System.out.println("Nessun elemento trovato dalla ricerca OSM.");
            return;
        }

        // Mostra la lista degli elementi trovati con indice
        System.out.println("\nElementi trovati:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }

        // Chiedi all'utente di selezionare un elemento dalla lista
        System.out.print("\nSeleziona l'elemento da utilizzare (inserisci il numero): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        if (selection < 1 || selection > results.size()) {
            System.out.println("Selezione non valida.");
            return;
        }

        OverpassElement selectedElement = results.get(selection - 1);
        POIType poiType = POIType.fromOSMTag(poi);

        // Ottieni l'utente corrente (dummy)
        User currentUser = getCurrentUser();

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = createPOIFromOSM(selectedElement, currentUser, poiType);

        System.out.println("\nPointOfInterest creato dalla ricerca OSM:");
        System.out.println(newPoi);
    }

    public List<PointOfInterest> getAllPOIs() {
        return poiRepository.findAll();
    }

    public PointOfInterest getPOIById(int id) {
        return poiRepository.findById(id);
    }

    public void save(PointOfInterest pointOfInterest) {
        poiRepository.save(pointOfInterest);
    }

        // Metodo dummy per ottenere l'utente attuale
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
