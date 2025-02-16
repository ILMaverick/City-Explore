package EVENTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEventoRepository implements EventoRepository{
	 private Map<String, Evento> storage = new HashMap<>();

	    @Override
	    public void save(Evento evento) {
	        if (evento != null) {
	            storage.put(evento.getId(), evento);
	        }
	    }

	    @Override
	    public List<Evento> findAll() {
	        return new ArrayList<>(storage.values());
	    }

	    @Override
	    public Evento findById(String id) {
	        return storage.get(id);
	    }
}
