package com.speriamochemelacavo.City_Explore.POI;

import com.speriamochemelacavo.City_Explore.ELEMENT.ElementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface POIRepository extends JpaRepository<POIController, Integer> {
    /**
     * Salva un oggetto PointOfInterest.
     *
     * @return
     */
    void save(PointOfInterest poi);

    /**
     * Restituisce tutti gli oggetti PointOfInterest salvati.
     */
    List<PointOfInterest> findAll();

    /**
     * Restituisce il PointOfInterest con l'id specificato, oppure null se non presente.
     */
    PointOfInterest findById(int id);

    List<PointOfInterest> findByStatus(ElementStatus status);

    List<PointOfInterest> searchByName(String name);

    List<PointOfInterest> searchByDescription(String description);

    void deleteByID(int id);
}
