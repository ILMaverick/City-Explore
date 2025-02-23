package com.unicam.City_Explore.validazione;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserRepository;
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
    @Autowired
    private UserRepository userRepository;


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
        poiRepository.save(poi);
        notificationListener.handleValidationPOI(poi);
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (poi != null && poi.getStatus()==Status.PENDING) {
                poi.setStatus(Status.APPROVED);
                poiRepository.save(poi);
                notificationListener.handleApprovePOI(poi);
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void updatePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (poi != null && poi.getStatus()==Status.PENDING) {
                poi.setStatus(Status.UPDATED);
                poiRepository.save(poi);
                notificationListener.handleUpdatePOIStatus(poi);
            } else {
                throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (poi != null && poi.getStatus()==Status.PENDING) {
                poi.setStatus(Status.REJECTED);
                deletionService.deletePOI(idPOI);
                notificationListener.handleRejectPOI(poi, reason);
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(Status.PENDING);
        tourRepository.save(tour);
        notificationListener.handleValidationTour(tour);
    }

    public void approveTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (tour != null && tour.getStatus()==Status.PENDING) {
                tour.setStatus(Status.APPROVED);
                tourRepository.save(tour);
                notificationListener.handleApproveTour(tour);
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void updateTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (tour != null && tour.getStatus()==Status.PENDING) {
                tour.setStatus(Status.UPDATED);
                tourRepository.save(tour);
                notificationListener.handleUpdateTourStatus(tour);
            } else {
                throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void rejectTour(int idTour, String reason) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (tour != null && tour.getStatus()==Status.PENDING) {
                tour.setStatus(Status.REJECTED);
                deletionService.deleteTour(idTour);
                notificationListener.handleRejectTour(tour, reason);
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(Status.PENDING);
        multimediaContentRepository.save(multimediaContent);
        notificationListener.handleValidationMultimediaContent(multimediaContent);
    }

    public void approveMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
                multimediaContent.setStatus(Status.APPROVED);
                multimediaContentRepository.save(multimediaContent);
                notificationListener.handleApproveMultimediaContent(multimediaContent);
            } else {
                throw new RuntimeException("Contenuto non in stato PENDENTE per approvazione");
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void updateMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
                multimediaContent.setStatus(Status.UPDATED);
                multimediaContentRepository.save(multimediaContent);
                notificationListener.handleUpdateMultimediaContentStatus(multimediaContent);
            } else {
                throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void rejectMultimediaContent(int idMC, String reason) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
                multimediaContent.setStatus(Status.REJECTED);
                deletionService.deleteContest(idMC);
                notificationListener.handleRejectMultimediaContent(multimediaContent, reason);
            } else {
                throw new RuntimeException("Contenuto non in stato PENDENTE per rifiuto");
            }
        } else {
            notificationListener.handleDenialPermission(curator);
        }
    }

    public void handleReportMultimediaContent(Status status, String message) {
        User curator = userRepository.searchUsersByRole(Role.CURATOR).stream().findAny().orElse(null);
        if(checkCurator(curator)) {
            List<MediaReport> reportList = reportService.getMediaReportList();
            for(MediaReport report: reportList) {
                MultimediaContent multimediaContentReported = report.getMultimediaContent();
                if(multimediaContentReported != null && multimediaContentReported.getStatus()==Status.REPORTED) {
                    if(status == Status.UPDATED) {
                        updateMultimediaContent(multimediaContentReported.getId());
                    } else if(status == Status.REJECTED){
                        rejectMultimediaContent(multimediaContentReported.getId(), message);
                    }
                }
                reportService.deleteReport(report);
            }
        } else {
            notificationListener.handleDenialPermission(curator);
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

    private boolean checkCurator(User user) {
        return user != null && user.getRole()== Role.CURATOR;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
