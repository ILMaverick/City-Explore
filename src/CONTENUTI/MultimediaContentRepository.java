package CONTENUTI;

import java.util.List;

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
}
