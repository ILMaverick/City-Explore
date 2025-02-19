package com.speriamochemelacavo.City_Explore.VALIDAZIONE;

import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContent;
import com.speriamochemelacavo.City_Explore.CONTENUTI.MultimediaContentRepository;
import com.speriamochemelacavo.City_Explore.ELEMENT.ElementStatus;
import com.speriamochemelacavo.City_Explore.ELIMINAZIONE.DeletionService;
import com.speriamochemelacavo.City_Explore. POI.POIRepository;
import com.speriamochemelacavo.City_Explore.POI.PointOfInterest;
import com.speriamochemelacavo.City_Explore.TOUR.Tour;
import com.speriamochemelacavo.City_Explore.TOUR.TourRepository;
import com.speriamochemelacavo.City_Explore.USER.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
@Service
public class ValidationService {
    // Scanner condiviso per l'input da riga di comando.
    private Scanner scanner;
    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private MultimediaContentRepository multimediaContentRepository;
    @Autowired
    private DeletionService deletionService;

    public ValidationService(POIRepository poiRepository, TourRepository tourRepository, MultimediaContentRepository multimediaContentRepository, DeletionService deletionService) {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
        this.poiRepository = poiRepository;
        this.tourRepository = tourRepository;
        this.multimediaContentRepository = multimediaContentRepository;
        this.deletionService = deletionService;
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
                approveTour(t.getId());
            } else if(num == 2) {
                String reason = scanner.nextLine();
                rejectTour(t.getId(), reason);
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
                approveMultimediaContent(mc.getId());
            } else if(num == 2) {
                String reason = scanner.nextLine();
                rejectMultimediaContent(mc.getId(), reason);
            } else {
                System.out.println("Opzione inesistente, il Contenuto e' ancora pendente.");
            }
        }

        System.out.println("\nValidazioni effettuate con successo:");
    }

    public void sendPOIForValidation(PointOfInterest poi) {
        poi.setStatus(ElementStatus.PENDING);
        poiRepository.save(poi);
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        poi.setStatus(ElementStatus.APPROVED);
        poi.setPublished(true);
        System.out.println("Il Punto di Interesse e' stato accettato");
        poiRepository.save(poi);
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI);
        poi.setStatus(ElementStatus.REJECTED);
        System.out.println("Il Punto di Interesse e' stato rifiutato, " + reason);
        deletionService.deletePOI(idPOI);
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(ElementStatus.PENDING);
        tourRepository.save(tour);
    }

    public void approveTour(int idTour) {
        Tour tour = tourRepository.findById(idTour);
        tour.setStatus(ElementStatus.APPROVED);
        tour.setPublished(true);
        System.out.println("L'Itinerario e' stato accettato");
        tourRepository.save(tour);
    }

    public void rejectTour(int idTour, String reason) {
        Tour tour = tourRepository.findById(idTour);
        tour.setStatus(ElementStatus.REJECTED);
        System.out.println("L'Itinerario e' stato rifiutato, " + reason);
        deletionService.deleteTour(idTour);
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(ElementStatus.PENDING);
        multimediaContentRepository.save(multimediaContent);
    }

    public void approveMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC);
        multimediaContent.setStatus(ElementStatus.APPROVED);
        multimediaContent.setPublished(true);
        System.out.println("Il Contenuto Multimediale e' stato accettato");
        multimediaContentRepository.save(multimediaContent);
    }

    public void rejectMultimediaContent(int idMC, String reason) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC);
        multimediaContent.setStatus(ElementStatus.REJECTED);
        System.out.println("Il Contenuto Multimediale e' stato rifiutato, " + reason);
        deletionService.deleteContest(idMC);
    }

    public List<PointOfInterest> getAllPendingPOI() {
        return poiRepository.findByStatus(ElementStatus.PENDING);
    }

    public List<Tour> getAllPendingTour() {
        return tourRepository.findByStatus(ElementStatus.PENDING);
    }

    public List<MultimediaContent> getAllPendingMultimediaContent() {
        return multimediaContentRepository.findByStatus(ElementStatus.PENDING);
    }

    private User getCurrentUser() {
        User user = new User();
        user.setName("utente");
        user.setSurname("demo");
        user.setUsername("utente_demo");
        user.setEmail("utente_demo.mail@gmail.com");
        user.setPassword("1234567890");
        return user;
    }

    // Se necessario, aggiungi un metodo per chiudere lo scanner quando il controller non serve più.
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
