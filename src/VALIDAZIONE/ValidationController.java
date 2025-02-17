package VALIDAZIONE;

import CONTENUTI.InMemoryMultimediaContent;
import CONTENUTI.MultimediaContent;
import POI.InMemoryPOIRepository;
import POI.PointOfInterest;
import TOUR.InMemoryTourRepository;
import TOUR.Tour;

import java.util.List;

public class ValidationController {

    private ValidationService validationService;

    public ValidationController() {
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
        validationService.validation();
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

    public void close() {
        validationService.close();
    }
}
