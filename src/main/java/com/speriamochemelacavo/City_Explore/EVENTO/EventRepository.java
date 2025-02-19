package com.speriamochemelacavo.City_Explore.EVENTO;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {
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

    void deleteByID(int id);
}
