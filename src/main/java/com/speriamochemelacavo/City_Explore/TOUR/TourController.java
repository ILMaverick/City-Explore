package com.speriamochemelacavo.City_Explore.TOUR;

import java.util.List;

public class TourController {
    private TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
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

    public void searchTourByName() {
        tourService.searchTourByName();
    }

    public void searchTourByDescription() {
        tourService.searchTourByDescription();
    }
    
    public void close() {
        tourService.close();
    }
}
