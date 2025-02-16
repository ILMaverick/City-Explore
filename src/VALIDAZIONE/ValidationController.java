package VALIDAZIONE;

import CONTENUTI.InMemoryMultimediaContent;
import CONTENUTI.MultimediaContent;
import POI.InMemoryPOIRepository;
import POI.PointOfInterest;
import TOUR.InMemoryTourRepository;
import TOUR.Tour;
import USER.User;

import java.util.List;
import java.util.Scanner;

public class ValidationController {

    // Scanner condiviso per l'input da riga di comando.
    private Scanner scanner;
    private ValidationService validationService;

    public ValidationController() {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.validationService = new ValidationService(new InMemoryPOIRepository(), new InMemoryTourRepository(), new InMemoryMultimediaContent());
    }
    /**
    public void sendPOIForValidation(PointOfInterest poi) {

        validationService.sendPOIForValidation(poi);
    }

    public void approvePOI(String idPOI) {
        validationService.approvePOI(idPOI);
    }

    public void rejectPOI(String idPOI, String reason) {
        validationService.rejectPOI(idPOI, reason);
    }

    public void sendTourForValidation(Tour tour) {
        validationService.sendTourForValidation(tour);
    }

    public void approveTour(String idTour) {
        validationService.approveTour(idTour);
    }

    public void rejectTour(String idTour, String reason) {
        validationService.rejectTour(idTour, reason);
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
       validationService.sendMultimediaContentForValidation(multimediaContent);
    }

    public void approveMultimediaContent(String idMultimediaContent) {
        validationService.approveMultimediaContent(idMultimediaContent);
    }

    public void rejectMultimediaContent(String idMultimediaContent, String reason) {
        validationService.rejectMultimediaContent(idMultimediaContent, reason);
    }
     */

    public void validation() {
        System.out.println("=== Validazione Punti di Interesse ===");
        System.out.println("Seleziona 1 per approvare o 2 per rifiutare: ");

        List<PointOfInterest> pointOfInterestList = validationService.getAllPendingPOI();
        for(PointOfInterest p: pointOfInterestList) {
            if(scanner.nextLine().equals("1")) {
                validationService.approvePOI(p);
            } else if(scanner.nextLine().equals("2")) {
                String reason = scanner.nextLine();
                validationService.rejectPOI(p, reason);
            }
        }

        System.out.println("=== Validazione Itinerari ===");
        List<Tour> tourList = validationService.getAllPendingTour();
        for(Tour t: tourList) {
            if(scanner.nextLine().equals("1")) {
                validationService.approveTour(t);
            } else if(scanner.nextLine().equals("2")) {
                String reason = scanner.nextLine();
                validationService.rejectTour(t, reason);
            }
        }

        System.out.println("=== Validazione Contenuti Multimediali ===");
        List<MultimediaContent> multimediaContentList = validationService.getAllPendingMultimediaContent();
        for(MultimediaContent mc: multimediaContentList) {
            if(scanner.nextLine().equals("1")) {
                validationService.approveMultimediaContent(mc);
            } else if(scanner.nextLine().equals("2")) {
                String reason = scanner.nextLine();
                validationService.rejectMultimediaContent(mc, reason);
            }
        }

        System.out.println("\nValidazioni effettuate con successo:");
    }


    public void displayAllPOiPending() {
        List<PointOfInterest> pointOfInterestList = validationService.getAllPendingPOI();
        if (pointOfInterestList == null || pointOfInterestList.isEmpty()) {
            System.out.println("Nessun Punto di Interesse da validare.");
        } else {
            System.out.println("Elenco dei Punti di Interesse da validare:");
            for (PointOfInterest p : pointOfInterestList) {
                System.out.println(p);
            }
        }
    }

    public void displayAllTourPending() {
        List<Tour> tourList = validationService.getAllPendingTour();
        if (tourList == null || tourList.isEmpty()) {
            System.out.println("Nessun Itinerario da validare.");
        } else {
            System.out.println("Elenco degli Itinerari da validare:");
            for (Tour t : tourList) {
                System.out.println(t);
            }
        }
    }

    public void displayAllMultimediaContentPending() {
        List<MultimediaContent> multimediaContentList = validationService.getAllPendingMultimediaContent();
        if (multimediaContentList == null || multimediaContentList.isEmpty()) {
            System.out.println("Nessun Contenuto Multimediale da validare.");
        } else {
            System.out.println("Elenco dei Contenuti da validare:");
            for (MultimediaContent mc : multimediaContentList) {
                System.out.println(mc);
            }
        }
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
