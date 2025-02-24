package com.unicam.City_Explore.evento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.unicam.City_Explore.poi.PointOfInterest;

@RestController
public class EventController {
	@Autowired
    private EventService eventService; // Service per la gestione degli eventi

    public EventController() {
    }

    
    /**
     * Visualizza tutti gli eventi salvati.
     */
    public void displayAllEvents() {
        List<Event> eventList = eventService.getAllEvent();
        if (eventList == null || eventList.isEmpty()) {
            System.out.println("Nessun evento salvato.");
        } else {
            System.out.println("Elenco degli eventi salvati:");
            for (Event e : eventList) {
                System.out.println(e);
            }
        }
    }

    public void displayAllPoI() {
        List<PointOfInterest> pointOfInterestList = eventService.getAllPoiFromEventRepository();
        if (pointOfInterestList == null || pointOfInterestList.isEmpty()) {
            System.out.println("Nessun poi salvato.");
        } else {
            System.out.println("Elenco dei poi salvati:");
            for (PointOfInterest p : pointOfInterestList) {
                System.out.println(p);
            }
        }
    }


}
