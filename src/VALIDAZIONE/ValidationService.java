package VALIDAZIONE;

import CONTENUTI.MultimediaContent;
import CONTENUTI.MultimediaContentService;
import ELEMENT.ElementStatus;
import POI.POIService;
import POI.PointOfInterest;
import TOUR.Tour;
import TOUR.TourService;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationService {
    private POIService poiService;
    private TourService tourService;
    private MultimediaContentService multimediaContentService;

    public ValidationService(POIService poiService, TourService tourService, MultimediaContentService multimediaContentService) {
        this.poiService = poiService;
        this.tourService = tourService;
        this.multimediaContentService = multimediaContentService;
    }

    public void sendPOIForValidation(PointOfInterest poi) {
        poi.setStatus(ElementStatus.Pending);
        poiService.savePOI(poi);
    }

    public void approvePOI(PointOfInterest poi) {
        poi.setStatus(ElementStatus.Approved);
        poi.setPublished(true);
        System.out.println("Il Punto di Interesse e' stato accettato");
        poiService.savePOI(poi);
    }

    public void rejectPOI(PointOfInterest poi, String reason) {
        poi.setStatus(ElementStatus.Rejected);
        System.out.println("Il Punto di Interesse e' stato rifiutato, " + reason);
        poiService.savePOI(poi);
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(ElementStatus.Pending);
        tourService.saveTour(tour);
    }

    public void approveTour(Tour tour) {
        tour.setStatus(ElementStatus.Approved);
        tour.setPublished(true);
        System.out.println("L'Itinerario e' stato accettato");
        tourService.saveTour(tour);
    }

    public void rejectTour(Tour tour, String reason) {
        tour.setStatus(ElementStatus.Rejected);
        System.out.println("L'Itinerario e' stato rifiutato, " + reason);
        tourService.saveTour(tour);
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(ElementStatus.Pending);
        multimediaContentService.saveMultimediaContent(multimediaContent);
    }

    public void approveMultimediaContent(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(ElementStatus.Approved);
        multimediaContent.setPublished(true);
        System.out.println("Il Contenuto Multimediale e' stato accettato");
        multimediaContentService.saveMultimediaContent(multimediaContent);
    }

    public void rejectMultimediaContent(MultimediaContent multimediaContent, String reason) {
        multimediaContent.setStatus(ElementStatus.Rejected);
        System.out.println("Il Contenuto Multimediale e' stato rifiutato, " + reason);
        multimediaContentService.saveMultimediaContent(multimediaContent);
    }

    public List<PointOfInterest> getAllPendingPOI() {
        return poiService.getAllPOIs().stream().filter(pointOfInterest -> pointOfInterest.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

    public List<Tour> getAllPendingTour() {
        return tourService.getAllTours().stream().filter(tour -> tour.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

    public List<MultimediaContent> getAllPendingMultimediaContent() {
        return multimediaContentService.getAllMultimediaContent().stream().filter(multimediaContent -> multimediaContent.getStatus().equals(ElementStatus.Pending)).collect(Collectors.toList());
    }

}
