package com.unicam.City_Explore.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
public class TourController {
    @Autowired
    private TourService tourService;

    public TourController() {
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
