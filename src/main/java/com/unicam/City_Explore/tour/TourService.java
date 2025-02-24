package com.unicam.City_Explore.tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.validazione.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.user.UserService;
import com.unicam.City_Explore.notifica.NotificationListener;

@Service
public class TourService {
    private Scanner scanner;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationListener notificationListener;
    @Autowired
    private ValidationService validationService;

    public TourService() {

        scanner = new Scanner(System.in);
     }

    /**
     * Metodo interattivo per costruire un Tour a partire da una lista di PointOfInterest.
     */
    public Tour buildTourFromPOIs(List<PointOfInterest> poiList) {
    	User author = this.userService.getCurrentUser();
        if(author.getRole() == Role.CONTRIBUTOR || author.getRole() == Role.AUTHORIZED_CONTRIBUTOR) {
            // 1. Crea le tappe a partire dai POI
            List<Tappa> tappe = new ArrayList<>();
            int count = 1;
            for (PointOfInterest poi : poiList) {
                Tappa tappa = new Tappa(poi.getName(), poi.getDescription(), poi.getLatitude(), poi.getLongitude(), poi.getAuthor(), poi.getType(), count++);
                tappe.add(tappa);
            }

            // 2. Raggruppa le tappe in gruppi per formare i percorsi
            List<List<Tappa>> gruppiTappe = groupTappe(tappe);

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
            notificationListener.handleCreateTour(tour);
            if(author.getRole() == Role.CONTRIBUTOR) {
                validationService.sendTourForValidation(tour);
            } else {
                validationService.approveTour(tour.getId());
            }

            // scanner.close(); // Attenzione a non chiudere System.in se usato altrove
            return tour;
        } else {
            notificationListener.handleDenialPermission(author);
        }
        return null;
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
        User newTourAuthor = tour.getAuthor();
        if(newTourAuthor.getRole() == Role.CURATOR || newTourAuthor.getRole() == Role.ADMINISTRATOR) {
            Tour tourSelected = getTourById(idTour);
            if (tourSelected != null && tourSelected.getStatus()== Status.UPDATED) {
                tourSelected.setName(tour.getName());
                tourSelected.setDescription(tour.getDescription());
                tourSelected.setWayList(tour.getWayList());
                tourSelected.setStatus(Status.APPROVED);
                tourRepository.save(tourSelected);
                notificationListener.handleUpdateTour(tour);
            }
            return tourSelected;
        } else {
            notificationListener.handleDenialPermission(newTourAuthor);
        }
        return null;
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
        return tourRepository.findById(id).orElse(null);
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
