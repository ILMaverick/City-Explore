package TOUR;

import java.util.List;

public interface TourRepository {
    /**
     * Salva un oggetto PointOfInterest.
     */
    void save(Tour tour);

    /**
     * Restituisce tutti gli oggetti PointOfInterest salvati.
     */
    List<Tour> findAll();

    /**
     * Restituisce il PointOfInterest con l'id specificato, oppure null se non presente.
     */
    Tour findById(String id);

}
