package CONTEST;

import java.util.List;

public interface ContestRepository {
    /**
     * Salva un oggetto Contest.
     */
    void save(Contest contest);

    /**
     * Restituisce tutti gli oggetti Contest salvati.
     */
    List<Contest> findAll();

    /**
     * Restituisce il Contest con l'id specificato, oppure null se non presente.
     */
    Contest findById(int id);
}
