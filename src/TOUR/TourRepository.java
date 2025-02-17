package TOUR;

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

}
