package EVENTO;

import java.util.List;


public interface EventoRepository {
	/**
     * Salva un oggetto Evento.
     */
    void save(Evento evento);

    /**
     * Restituisce tutti gli oggetti Evento salvati.
     */
    List<Evento> findAll();

    /**
     * Restituisce il Evento con l'id specificato, oppure null se non presente.
     */
    Evento findById(String id);

}
