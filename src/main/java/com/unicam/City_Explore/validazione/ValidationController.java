package com.unicam.City_Explore.validazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.tour.Tour;

import java.util.List;
@RestController
public class ValidationController {
    @Autowired
    private ValidationService validationService;

    public ValidationController() {
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
        if (pointOfInterestList.isEmpty()) {
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
        if (tourList.isEmpty()) {
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
        if (multimediaContentList.isEmpty()) {
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
