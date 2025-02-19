package com.speriamochemelacavo.City_Explore.CONTEST;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Integer> {
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

    List<Contest> searchByName(String name);

    List<Contest> searchByDescription(String description);

    void deleteByID(int id);
}