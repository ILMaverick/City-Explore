package EVENTO;

import java.util.List;

import POI.PointOfInterest;
import USER.User;

public class EventoService {
    private EventoRepository eventoRepository;

    public EventoService(EventoRepository repository) {
        this.eventoRepository = repository;
    }
    
    /**
     * Crea un nuovo Evento a partire dai dati forniti.
     */
    public Evento createEvento(String name, String description, User author, String scope,
                                 String activity, String organization, String teme, String category,
                                 double price, PointOfInterest location, double orario) {
        Evento evento = new Evento(name, description, author, scope, activity, organization, teme, category, price, location, orario);
        return evento;
    }
    
    /**
     * Salva l'Evento nella repository.
     */
    public void saveEvento(Evento evento) {
        eventoRepository.save(evento);
    }
    
    /**
     * Restituisce tutti gli eventi salvati.
     */
    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }
    
    /**
     * Restituisce un Evento dato il suo id.
     */
    public Evento getEventoById(String id) {
        return eventoRepository.findById(id);
    }
}

