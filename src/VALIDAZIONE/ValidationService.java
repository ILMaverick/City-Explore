package VALIDAZIONE;

import CONTENUTI.MultimediaContent;
import CONTENUTI.MultimediaContentRepository;
import ELEMENT.ElementStatus;
import POI.POIRepository;
import POI.PointOfInterest;
import TOUR.Tour;
import TOUR.TourRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationService {
    private POIRepository poiRepository;
    private TourRepository tourRepository;
    private MultimediaContentRepository multimediaContentRepository;

    public ValidationService(POIRepository poiRepository, TourRepository tourRepository, MultimediaContentRepository multimediaContentRepository) {
        this.poiRepository = poiRepository;
        this.tourRepository = tourRepository;
        this.multimediaContentRepository = multimediaContentRepository;
    }

    public void sendPOIForValidation(PointOfInterest poi) {
        poi.setStatus(ElementStatus.Pending);
        poiRepository.save(poi);
    }

    public void approvePOI(PointOfInterest poi) {
        poi.setStatus(ElementStatus.Approved);
        poi.setPublished(true);
        System.out.println("Il Punto di Interesse e' stato accettato");
        poiRepository.save(poi);
    }

    public void rejectPOI(PointOfInterest poi, String reason) {
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
        return poiRepository.findAll().stream().filter(pointOfInterest -> pointOfInterest.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

    public List<Tour> getAllPendingTour() {
        return tourRepository.findAll().stream().filter(tour -> tour.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

    public List<MultimediaContent> getAllPendingMultimediaContent() {
        return multimediaContentRepository.findAll().stream().filter(multimediaContent -> multimediaContent.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

}
