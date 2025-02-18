package VALIDAZIONE;

import CONTENUTI.MultimediaContent;
import CONTENUTI.MultimediaContentRepository;
import ELEMENT.ElementStatus;
import POI.POIRepository;
import POI.PointOfInterest;
import TOUR.Tour;
import TOUR.TourRepository;
import USER.User;

import java.util.List;
import java.util.Scanner;

public class ValidationService {
    // Scanner condiviso per l'input da riga di comando.
    private Scanner scanner;
    private POIRepository poiRepository;
    private TourRepository tourRepository;
    private MultimediaContentRepository multimediaContentRepository;

    public ValidationService(POIRepository poiRepository, TourRepository tourRepository, MultimediaContentRepository multimediaContentRepository) {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.poiRepository = poiRepository;
        this.tourRepository = tourRepository;
        this.multimediaContentRepository = multimediaContentRepository;
    }

    public void validation() {
        System.out.println("=== Validazione Punti di Interesse ===");

        List<PointOfInterest> pointOfInterestList = getAllPendingPOI();
        for(PointOfInterest p: pointOfInterestList) {
            System.out.println(p);
            System.out.print("Scrivi 1 per approvare o 2 per rifiutare: ");
            int num = scanner.nextInt();
            if(num == 1) {
                approvePOI(p.getId());
            } else if(num == 2) {
                String reason = scanner.nextLine();
                rejectPOI(p.getId(), reason);
            } else {
                System.out.println("Opzione inesistente, il Punto di Interesse e' ancora pendente.");
            }
        }

        System.out.println("=== Validazione Itinerari ===");

        List<Tour> tourList = getAllPendingTour();
        for(Tour t: tourList) {
            System.out.println(t);
            System.out.print("Scrivi 1 per approvare o 2 per rifiutare: ");
            int num = scanner.nextInt();
            if(num == 1) {
                approveTour(t);
            } else if(num == 2) {
                String reason = scanner.nextLine();
                rejectTour(t, reason);
            } else {
                System.out.println("Opzione inesistente, l'Itinerario e' ancora pendente.");
            }
        }

        System.out.println("=== Validazione Contenuti Multimediali ===");

        List<MultimediaContent> multimediaContentList = getAllPendingMultimediaContent();
        for(MultimediaContent mc: multimediaContentList) {
            System.out.println(mc);
            System.out.print("Scrivi 1 per approvare o 2 per rifiutare: ");
            int num = scanner.nextInt();
            if(num == 1) {
                approveMultimediaContent(mc);
            } else if(num == 2) {
                String reason = scanner.nextLine();
                rejectMultimediaContent(mc, reason);
            } else {
                System.out.println("Opzione inesistente, il Contenuto e' ancora pendente.");
                continue;
            }
        }

        System.out.println("\nValidazioni effettuate con successo:");
    }

    public void sendPOIForValidation(PointOfInterest poi) {
        poi.setStatus(ElementStatus.Pending);
        poiRepository.save(poi);
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        poi.setStatus(ElementStatus.Approved);
        poi.setPublished(true);
        System.out.println("Il Punto di Interesse e' stato accettato");
        poiRepository.save(poi);
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        poi.setStatus(ElementStatus.Rejected);
        System.out.println("Il Punto di Interesse e' stato rifiutato, " + reason);
        poiRepository.save(poi);
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(ElementStatus.Pending);
        tourRepository.save(tour);
    }

    public void approveTour(Tour tour) {
        tour.setStatus(ElementStatus.Approved);
        tour.setPublished(true);
        System.out.println("L'Itinerario e' stato accettato");
        tourRepository.save(tour);
    }

    public void rejectTour(Tour tour, String reason) {
        tour.setStatus(ElementStatus.Rejected);
        System.out.println("L'Itinerario e' stato rifiutato, " + reason);
        tourRepository.save(tour);
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(ElementStatus.Pending);
        multimediaContentRepository.save(multimediaContent);
    }

    public void approveMultimediaContent(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(ElementStatus.Approved);
        multimediaContent.setPublished(true);
        System.out.println("Il Contenuto Multimediale e' stato accettato");
        multimediaContentRepository.save(multimediaContent);
    }

    public void rejectMultimediaContent(MultimediaContent multimediaContent, String reason) {
        multimediaContent.setStatus(ElementStatus.Rejected);
        System.out.println("Il Contenuto Multimediale e' stato rifiutato, " + reason);
        multimediaContentRepository.save(multimediaContent);
    }

    public List<PointOfInterest> getAllPendingPOI() {
        return poiRepository.findByStatus(ElementStatus.Pending);
    }

    public List<Tour> getAllPendingTour() {
        return tourRepository.findByStatus(ElementStatus.Pending);
    }

    public List<MultimediaContent> getAllPendingMultimediaContent() {
        return multimediaContentRepository.findByStatus(ElementStatus.Pending);
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
