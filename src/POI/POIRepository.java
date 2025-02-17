package POI;

import ELEMENT.ElementStatus;

import java.util.List;

public interface POIRepository {
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
}
