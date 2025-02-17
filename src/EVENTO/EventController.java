package EVENTO;

import java.util.List;

public class EventController {

    private EventService eventService; // Service per la gestione degli eventi

    public EventController() {
        this.eventService = new EventService();
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

    public void close() {
        eventService.close();
    }
}
