package EVENTO;

import java.time.LocalDateTime;
import java.util.List;

import POI.InMemoryPOIRepository;
import POI.POIRepository;
import POI.POIService;
import POI.PointOfInterest;
import USER.User;

public class EventService {

    private EventRepository eventRepository;

    private POIRepository poiRepository;

    public EventService() {
        this.eventRepository = new InMemoryEventRepository();
        this.poiRepository = new InMemoryPOIRepository();
    }
    
    /**
     * Crea un nuovo Evento a partire dai dati forniti.
     */
    public Event createEvent(String name, String description, User author, String scope,
                             String activity, String organization, String theme, String category,
                             double price, PointOfInterest location, LocalDateTime time) {

        Event event = new Event(name, description, author, scope, activity, organization, theme, category, price, location, time);
        return event;
    }
    
    /**
     * Salva l'Evento nella repository.
     */
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
    
    /**
     * Restituisce tutti gli eventi salvati.
     */
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }
    
    /**
     * Restituisce un Evento dato il suo id.
     */
    public Event getEventById(int id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(int id, Event event) {
        Event eventSelected = eventRepository.findById(id);
        if(eventSelected != null) {
            eventSelected.setName(event.getName());
            eventSelected.setDescription(event.getDescription());
            eventSelected.setScope(event.getScope());
            eventSelected.setActivity(event.getActivity());
            eventSelected.setOrganization(event.getOrganization());
            eventSelected.setTheme(event.getTheme());
            eventSelected.setCategory(event.getCategory());
            eventSelected.setTime(event.getTime());
            eventSelected.setPrice(event.getPrice());
            eventRepository.save(eventSelected);
            return eventSelected;
        }
        return null;
    }
}

