package com.speriamochemelacavo.City_Explore.EVENTO;

import com.speriamochemelacavo.City_Explore.POI.PointOfInterest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class EventController {
	@Autowired
    private EventService eventService; // Service per la gestione degli eventi

    public EventController() {
    }

    public void initializer() {
        eventService.initializer();
    }

    public void createEventFromInput() {
        eventService.createEventFromInput();
    }

    public void addEventToPOI() {
        eventService.addEventToPOI();
    }

    public void updateEvent() {
        eventService.updateEvent();
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

    public void searchEventByName() {
        eventService.searchEventByName();
    }

    public void searchEventByDescription() {
        eventService.searchEventByDescription();
    }

    public void close() {
        eventService.close();
    }
}
