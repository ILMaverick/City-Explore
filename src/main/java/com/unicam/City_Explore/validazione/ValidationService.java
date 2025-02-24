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
import com.unicam.City_Explore.notifica.NotificationListener;

import java.util.List;

@Service
public class ValidationService {

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

    }

    public void sendPOIForValidation(PointOfInterest poi) {
        poi.setStatus(Status.PENDING);
        poiRepository.save(poi);
        notificationListener.handleValidationPOI(poi);
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        if (poi != null && poi.getStatus()==Status.PENDING) {
            poi.setStatus(Status.APPROVED);
            poiRepository.save(poi);
            notificationListener.handleApprovePOI(poi);
        }
    }

    public void updatePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        if (poi != null && poi.getStatus()==Status.PENDING) {
            poi.setStatus(Status.UPDATED);
            poiRepository.save(poi);
            notificationListener.handleUpdatePOIStatus(poi);
        } else {
            throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
        }
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI).orElse(null);
        if (poi != null && poi.getStatus()==Status.PENDING) {
            poi.setStatus(Status.REJECTED);
            deletionService.deletePOI(idPOI, reason);
            notificationListener.handleRejectPOI(poi, reason);
        }
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(Status.PENDING);
        tourRepository.save(tour);
        notificationListener.handleValidationTour(tour);
    }

    public void approveTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        if (tour != null && tour.getStatus()==Status.PENDING) {
            tour.setStatus(Status.APPROVED);
            tourRepository.save(tour);
            notificationListener.handleApproveTour(tour);
        }
    }

    public void updateTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        if (tour != null && tour.getStatus()==Status.PENDING) {
            tour.setStatus(Status.UPDATED);
            tourRepository.save(tour);
            notificationListener.handleUpdateTourStatus(tour);
        } else {
            throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
        }
    }

    public void rejectTour(int idTour, String reason) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        if (tour != null && tour.getStatus()==Status.PENDING) {
            tour.setStatus(Status.REJECTED);
            deletionService.deleteTour(idTour, reason);
            notificationListener.handleRejectTour(tour, reason);
        }
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(Status.PENDING);
        multimediaContentRepository.save(multimediaContent);
        notificationListener.handleValidationMultimediaContent(multimediaContent);
    }

    public void approveMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
            multimediaContent.setStatus(Status.APPROVED);
            multimediaContentRepository.save(multimediaContent);
            notificationListener.handleApproveMultimediaContent(multimediaContent);
        } else {
            throw new RuntimeException("Contenuto non in stato PENDENTE per approvazione");
        }
    }

    public void updateMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
            multimediaContent.setStatus(Status.UPDATED);
            multimediaContentRepository.save(multimediaContent);
            notificationListener.handleUpdateMultimediaContentStatus(multimediaContent);
        } else {
            throw new RuntimeException("Contenuto non in stato PENDENTE per modifica");
        }
    }

    public void rejectMultimediaContent(int idMC, String reason) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).orElse(null);
        if (multimediaContent != null && multimediaContent.getStatus()==Status.PENDING) {
            multimediaContent.setStatus(Status.REJECTED);
            deletionService.deleteContest(idMC, reason);
            notificationListener.handleRejectMultimediaContent(multimediaContent, reason);
        } else {
            throw new RuntimeException("Contenuto non in stato PENDENTE per rifiuto");
        }
    }

    public void handleReportMultimediaContent(MediaReport report, Status status, String message) {
        MultimediaContent multimediaContentReported = report.getMultimediaContent();
        if(multimediaContentReported != null && multimediaContentReported.getStatus()==Status.REPORTED) {
            if(status == Status.UPDATED) {
                updateMultimediaContent(multimediaContentReported.getId());
            } else if(status == Status.REJECTED){
                rejectMultimediaContent(multimediaContentReported.getId(), message);
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

    public List<MediaReport> getAllReportedMultimediaContent() {
        return reportService.getMediaReportList();
    }

}
