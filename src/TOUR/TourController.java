package TOUR;

import java.util.List;

public class TourController {
    private TourService tourService;

    public TourController() {
        this.tourService = new TourService();
    }

    public void createTourFromPOIs() {
        tourService.createTourFromPOIs();
    }

    /**
     * Visualizza tutti i Tour salvati.
     */
    public void displayAllTours() {
        List<Tour> tours = tourService.getAllTours();
        if (tours == null || tours.isEmpty()) {
            System.out.println("Nessun Tour salvato.");
        } else {
            System.out.println("Elenco dei Tour salvati:");
            for (Tour tour : tours) {
                System.out.println(tour);
            }
        }
    }
    
    public void close() {
        tourService.close();
    }
}
