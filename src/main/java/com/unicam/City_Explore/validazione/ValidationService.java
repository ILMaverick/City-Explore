package com.unicam.City_Explore.validazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.contenuti.MultimediaContentRepository;
import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.eliminazione.DeletionService;
import com.unicam.City_Explore.poi.POIRepository;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.segnalazione.MediaReport;
import com.unicam.City_Explore.segnalazione.MediaReportService;
import com.unicam.City_Explore.tour.Tour;
import com.unicam.City_Explore.tour.TourRepository;
import com.unicam.City_Explore.user.User;
import com.unicam.City_Explore.notifica.NotificationListener;

import java.time.LocalDateTime;
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
    @Autowired
    private MediaReportService reportService;
    @Autowired
    private NotificationListener notificationListener;


    public ValidationService() {
        // Inizializza lo scanner (non lo chiudiamo perché chiudere System.in potrebbe causare problemi se usato in seguito)
        scanner = new Scanner(System.in);
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
        poi.setStatus(Status.PENDING);
        notificationListener.handleValidationPOI(poi);
        poiRepository.save(poi);
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        if(poi != null) {
            poi.setStatus(Status.APPROVED);
            notificationListener.handleApprovePOI(poi);
            poiRepository.save(poi);
        }
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        if(poi != null) {
            poi.setStatus(Status.REJECTED);
            notificationListener.handleRejectPOI(poi, reason);
            deletionService.deletePOI(idPOI);
        }
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(Status.PENDING);
        notificationListener.handleValidationTour(tour);
        tourRepository.save(tour);
    }

    public void approveTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        if(tour != null) {
            tour.setStatus(Status.APPROVED);
            notificationListener.handleApproveTour(tour);
            tourRepository.save(tour);
        }
    }

    public void rejectTour(int idTour, String reason) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        if(tour != null) {
            tour.setStatus(Status.REJECTED);
            notificationListener.handleRejectTour(tour, reason);
            deletionService.deleteTour(idTour);
        }
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(Status.PENDING);
        notificationListener.handleValidationMultimediaContent(multimediaContent);
        multimediaContentRepository.save(multimediaContent);
    }

    public void approveMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(multimediaContent != null) {
            if(multimediaContent.getStatus().equals(Status.UPDATED)) {
                updateMultimediaContent(idMC, multimediaContent);
            }
            multimediaContent.setStatus(Status.APPROVED);
            notificationListener.handleApproveMultimediaContent(multimediaContent);
            multimediaContentRepository.save(multimediaContent);
        }
    }

    public void rejectMultimediaContent(int idMC, String reason) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if(multimediaContent != null) {
            multimediaContent.setStatus(Status.REJECTED);
            notificationListener.handleRejectMultimediaContent(multimediaContent, reason);
            deletionService.deleteContest(idMC);
        }
    }

    public void updateMultimediaContent(int idMC, MultimediaContent multimediaContent) {
        MultimediaContent multimediaContentSelected = multimediaContentRepository.findById(idMC).orElse(null);
        if(multimediaContentSelected != null) {
            multimediaContentSelected.setName(multimediaContent.getName());
            multimediaContentSelected.setDescription(multimediaContent.getDescription());
            multimediaContentSelected.setFormatFileEnum(multimediaContent.getFormatFileEnum());
            multimediaContentSelected.setDuration(multimediaContent.getDimension());
            multimediaContentSelected.setResolution(multimediaContent.getResolution());
            multimediaContentSelected.setDataCreation(LocalDateTime.now());
            multimediaContentRepository.save(multimediaContentSelected);
        }
    }

    public void handleReportMultimediaContent(Status status, String message) {
        List<MediaReport> reportList = reportService.getMediaReportList();
        for(MediaReport report: reportList) {
            MultimediaContent multimediaContentReported = report.getMultimediaContent();
            if(multimediaContentReported != null) {
                if(status.equals(Status.UPDATED)) {
                    multimediaContentReported.setStatus(Status.UPDATED);
                    updateMultimediaContent(multimediaContentReported.getId(), multimediaContentReported);
                } else if(status.equals(Status.REJECTED)){
                    rejectMultimediaContent(multimediaContentReported.getId(), message);
                }
            }
            reportService.deleteReport(report);
        }
    }

    public List<PointOfInterest> getAllPendingPOI() {
        return poiRepository.searchByStatus(Status.PENDING);
    }

    public List<Tour> getAllPendingTour() {
        return tourRepository.searchByStatus(Status.PENDING);
    }

    public List<MultimediaContent> getAllPendingMultimediaContent() {
        return multimediaContentRepository.findByStatus(Status.PENDING);
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
