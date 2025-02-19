package validazione;

import contenuti.MultimediaContent;
import contenuti.MultimediaContentRepository;
import element.Status;
import eliminazione.DeletionService;
import notifica.NotificationListener;
import poi.POIRepository;
import poi.PointOfInterest;
import tour.Tour;
import tour.TourRepository;
import user.User;

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
    }

    public void approvePOI(int idPOI) {
        PointOfInterest poi = poiRepository.findById(idPOI).get();
        poi.setStatus(Status.APPROVED);
        System.out.println("Il Punto di Interesse e' stato accettato");
        poiRepository.save(poi);
    }

    public void rejectPOI(int idPOI, String reason) {
        PointOfInterest poi = poiRepository.findById(idPOI).get();
        poi.setStatus(Status.REJECTED);
        System.out.println("Il Punto di Interesse e' stato rifiutato, " + reason);
        deletionService.deletePOI(idPOI);
    }

    public void sendTourForValidation(Tour tour) {
        tour.setStatus(Status.PENDING);
        tourRepository.save(tour);
    }

    public void approveTour(int idTour) {
        Tour tour = tourRepository.findById(idTour).get();
        tour.setStatus(Status.APPROVED);
        System.out.println("L'Itinerario e' stato accettato");
        tourRepository.save(tour);
    }

    public void rejectTour(int idTour, String reason) {
        Tour tour = tourRepository.findById(idTour).get();
        tour.setStatus(Status.REJECTED);
        System.out.println("L'Itinerario e' stato rifiutato, " + reason);
        deletionService.deleteTour(idTour);
    }

    public void sendMultimediaContentForValidation(MultimediaContent multimediaContent) {
        multimediaContent.setStatus(Status.PENDING);
        multimediaContentRepository.save(multimediaContent);
    }

    public void approveMultimediaContent(int idMC) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).get();
        multimediaContent.setStatus(Status.APPROVED);
        System.out.println("Il Contenuto Multimediale e' stato accettato");
        multimediaContentRepository.save(multimediaContent);
    }

    public void rejectMultimediaContent(int idMC, String reason) {
        MultimediaContent multimediaContent = multimediaContentRepository.findById(idMC).get();
        multimediaContent.setStatus(Status.REJECTED);
        System.out.println("Il Contenuto Multimediale e' stato rifiutato, " + reason);
        deletionService.deleteContest(idMC);
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
