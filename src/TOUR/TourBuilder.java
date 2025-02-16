package TOUR;

import java.util.List;
import USER.User;

public class TourBuilder {
	 private String name;
	    private String description;
	    private User author;
	    private List<Percorso> percorsi;
	    
	 public void withName(String name) {
	        this.name = name;
	        
	    }

	    public void withDescription(String description) {
	        this.description = description;
	    }

	    public void withAuthor(User author) {
	        this.author = author;    
	    }

	    public void addPercorso(List<Percorso> percorsi) {
	        this.percorsi = percorsi;
	    }

	    public Tour build() {
	        Tour tour = new Tour(name, description, author, percorsi);
	        return tour;
	    }
	}
