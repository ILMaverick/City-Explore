package TOUR;

import ELEMENT.ElementStatus;

import java.util.List;

public interface TourRepository {
    /**
     * Salva un oggetto Tour.
     */
    void save(Tour tour);

    /**
     * Restituisce tutti gli oggetti Tour salvati.
     */
    List<Tour> findAll();

    /**
     * Restituisce il Tour con l'id specificato, oppure null se non presente.
     */
    Tour findById(int id);

    List<Tour> findByStatus(ElementStatus status);

    List<Tour> searchByName(String name);

    List<Tour> searchByDescription(String description);

}
