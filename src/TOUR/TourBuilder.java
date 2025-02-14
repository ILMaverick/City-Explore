package TOUR;

import java.util.ArrayList;
import java.util.List;
import USER.User;

public class TourBuilder {
	 private String name;
	    private String description;
	    private User author;
	    private List<Percorso> percorsi = new ArrayList<>();
	    
	 public TourBuilder withName(String name) {
	        this.name = name;
	        return this;
	    }

	    public TourBuilder withDescription(String description) {
	        this.description = description;
	        return this;
	    }

	    public TourBuilder withAuthor(User author) {
	        this.author = author;
	        return this;
	    }

	    public TourBuilder addPercorso(Percorso percorso) {
	        this.percorsi.add(percorso);
	        return this;
	    }

	    public Tour build() {
	        Tour tour = new Tour(name, description, author);
	        tour.setPercorsi(percorsi);
	        return tour;
	    }
	}
