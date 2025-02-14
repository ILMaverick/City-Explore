package TOUR;

import java.util.ArrayList;
import java.util.List;

import INTERFACES.AbstractElement;
import USER.User;

public class Tour extends AbstractElement {
	private List<Percorso> percorsi;

	public Tour(String name, String description, User author) {
		super(name, description, author);
		this.percorsi = new ArrayList<>();
	}

	public List<Percorso> getPercorsi() {
		return percorsi;
	}

	public void setPercorsi(List<Percorso> percorsi) {
		this.percorsi = percorsi;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Tour {")
	      .append("\n  id='").append(super.getId()).append('\'')
	      .append(",\n  name='").append(super.getName()).append('\'')
	      .append(",\n  description='").append(super.getDescription()).append('\'')
	      .append(",\n  percorsi=");
	    
	    if (percorsi == null || percorsi.isEmpty()) {
	        sb.append("Nessun percorso");
	    } else {
	        // Itera la lista dei percorsi e chiama il loro toString()
	        for (Percorso p : percorsi) {
	            sb.append("\n    ").append(p.toString());
	        }
	    }
	    
	    sb.append(",\n  author=").append(super.getAuthor())
	      .append(",\n  published=").append(super.isPublished())
	      .append("\n}");
	    
	    return sb.toString();
	}

}
