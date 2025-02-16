package POI;

import CONTENUTI.MultimediaContent;

import java.util.List;

public interface POIRepository {
    /**
     * Salva un oggetto PointOfInterest.
     */
    void save(PointOfInterest poi);

    /**
     * Restituisce tutti gli oggetti PointOfInterest salvati.
     */
    List<PointOfInterest> findAll();

    /**
     * Restituisce il PointOfInterest con l'id specificato, oppure null se non presente.
     */
    PointOfInterest findById(String id);

}
