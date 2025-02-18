package EVENTO;

import POI.PointOfInterest;

import java.util.List;


public interface EventRepository {
	/**
     * Salva un oggetto Evento.
     */
    void save(Event event);

    /**
     * Restituisce tutti gli oggetti Evento salvati.
     */
    List<Event> findAll();

    /**
     * Restituisce il Evento con l'id specificato, oppure null se non presente.
     */
    Event findById(int id);

    List<Event> searchByName(String name);

    List<Event> searchByDescription(String description);

    List<Event> findByPOI(PointOfInterest pointOfInterest);
}
