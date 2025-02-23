package com.unicam.City_Explore.poi;

import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.validazione.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.osm.OSMSearchService;
import com.unicam.City_Explore.osm.OverpassElement;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.notifica.NotificationListener;

@Service
public class POIService {
    // Scanner condiviso per l'input da riga di comando.
    private Scanner scanner;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;

    public POIService() {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        
    }

    //Inizializza dei Punti di Interesse
    public void initializer() {
        createPOIFromUser("primo", "primo", 1, 1, POIType.Turismo);
        createPOIFromUser("secondo", "secondo", 2, 2, POIType.Alloggio);
        createPOIFromUser("terzo", "terzo", 3, 3, POIType.Natura);
    }

    /**
     * Crea un PointOfInterest partendo da zero, con input forniti dall'utente.
     */
    public void createPOIFromUser() {
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

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = createPOIFromUser(name, description, lat, lon, poiType );


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

        // Crea il PointOfInterest utilizzando la factory
        PointOfInterest newPoi = createPOIFromOSM(selectedElement, poiType);

        System.out.println("\nPointOfInterest creato dalla ricerca OSM:");
        System.out.println(newPoi);
    }

    public void searchPOIByName() {
        System.out.println("=== Ricerca Punti di Interesse tramite nome ===");
        System.out.print("Inserisci il nome: ");

        String name = scanner.nextLine();
        List<PointOfInterest> pointOfInterestList = searchPOIByName(name);
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questo nome.");
        } else {
            System.out.println("Elenco Punti di Interesse con il nome cercato:");
            for(PointOfInterest pointOfInterest: pointOfInterestList) {
                System.out.println(pointOfInterest);
            }
        }
    }

    public void searchPOIByDescription() {
        System.out.println("=== Ricerca Punti di Interesse tramite descrizione ===");
        System.out.print("Inserisci la descrizione: ");

        String description = scanner.nextLine();
        List<PointOfInterest> pointOfInterestList = searchPOIByDescription(description);
        if(pointOfInterestList.isEmpty()) {
            System.out.println("Non e' presente un Punto di Interesse con questa descrizione.");
        } else {
            System.out.println("Elenco Punti di Interesse con la descrizione cercata:");
            for(PointOfInterest pointOfInterest: pointOfInterestList) {
                System.out.println(pointOfInterest);
            }
        }
    }

    public PointOfInterest createPOIFromUser(String name, String description, double lat, double lon, POIType type) {
    	User author = this.userService.getCurrentUser();
    	PointOfInterest poi = PointOfInterestFactory.create(name, description, lat, lon, author, type);
        notificationListener.handleCreatePOI(poi);
        if(author.getRole() == Role.CONTRIBUTOR) {
            validationService.sendPOIForValidation(poi);
        } else {
            validationService.approvePOI(poi.getId());
        }
        return poiRepository.save(poi);
    }

    public PointOfInterest createPOIFromOSM(OverpassElement element, POIType type) {
    	User author = this.userService.getCurrentUser();
        if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTORIZED_CONTRIBUTOR) {
            PointOfInterest poi = PointOfInterestFactory.createFromOverpassElement(element, author, type);
            poiRepository.save(poi);
            notificationListener.handleCreatePOI(poi);
            if(author.getRole() == Role.CONTRIBUTOR) {
                validationService.sendPOIForValidation(poi);
            } else {
                validationService.approvePOI(poi.getId());
            }
            return poi;
        } else {
            notificationListener.handleDenialPermission(author);
        }
        return null;
    }

    public PointOfInterest updatePOI(int idPOI, PointOfInterest pointOfInterest) {
        User newPoiAuthor = pointOfInterest.getAuthor();
        if(newPoiAuthor.getRole() == Role.CURATOR || newPoiAuthor.getRole() == Role.ADMINISTRATOR) {
            PointOfInterest pointOfInterestSelected = getPOIById(idPOI);
            if (pointOfInterestSelected != null && pointOfInterestSelected.getStatus()== Status.UPDATED) {
                pointOfInterestSelected.setName(pointOfInterest.getName());
                pointOfInterestSelected.setDescription(pointOfInterest.getDescription());
                pointOfInterestSelected.setLatitude(pointOfInterest.getLatitude());
                pointOfInterestSelected.setLongitude(pointOfInterest.getLongitude());
				/*
				 * pointOfInterestSelected.setOpen_time(pointOfInterest.getOpen_time());
				 * pointOfInterestSelected.setClose_time(pointOfInterest.getClose_time());
				 */
                pointOfInterestSelected.setType(pointOfInterest.getType());
                pointOfInterestSelected.setStatus(Status.APPROVED);
                poiRepository.save(pointOfInterest);
                notificationListener.handleUpdatePOI(pointOfInterestSelected);
            }
            return pointOfInterestSelected;
        } else {
            notificationListener.handleDenialPermission(newPoiAuthor);
        }
        return null;
    }

    public List<PointOfInterest> searchPOIByName(String name) {
        return poiRepository.searchByName(name);
    }

    public List<PointOfInterest> searchPOIByDescription(String description) {
        return poiRepository.searchByDescription(description);
    }

    public List<PointOfInterest> searchPOIByType(POIType type) {
        return poiRepository.searchByType(type);
    }

    public List<PointOfInterest> getAllPOIs() {
        return poiRepository.findAll();
    }

    public PointOfInterest getPOIById(int id) {
        return poiRepository.findById(id).orElse(null);
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
