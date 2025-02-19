package com.speriamochemelacavo.City_Explore.TOUR;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {
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

    void deleteByID(int id);

}
