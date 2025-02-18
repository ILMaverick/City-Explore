package com.speriamochemelacavo.City_Explore.CONTENUTI;

import java.util.List;
import com.speriamochemelacavo.City_Explore.ELEMENT.ElementStatus;

public interface MultimediaContentRepository {
    /**
     * Salva un oggetto Contenuto.
     */
    void save(MultimediaContent multimediaContent);

    /**
     * Restituisce tutti gli oggetti Contenuti salvati.
     */
    List<MultimediaContent> findAll();

    /**
     * Restituisce il Contenuto con l'id specificato, oppure null se non presente.
     */
    MultimediaContent findById(int id);

    List<MultimediaContent> findByStatus(ElementStatus status);

    List<MultimediaContent> searchByName(String name);

    List<MultimediaContent> searchByDescription(String description);

    void deleteByID(int id);
}
