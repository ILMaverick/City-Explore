package com.unicam.City_Explore.tour;

import java.util.ArrayList;
import java.util.List;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Tour extends AbstractElement {
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Way> wayList = new ArrayList<>();
	
	public Tour() {
		
	}

	public Tour(String name, String description, User author) {
		super(name, description, author);
	}

	public List<Way> getWayList() {
		return wayList;
	}

	public void setWayList(List<Way> wayList) {
		this.wayList = wayList;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Tour {")
	      .append("\n  id=").append(super.getId()).append('\'')
	      .append(",\n  name=").append(super.getName()).append('\'')
	      .append(",\n  descr='").append(super.getDescription()).append('\'')
	      .append(",\n  way=");
	    
	    if (wayList == null || wayList.isEmpty()) {
	        sb.append("Nessun percorso");
	    } else {
	        // Itera la lista dei percorsi e chiama il loro toString()
	        for (Way p : wayList) {
	            sb.append("\n    ").append(p.toString());
	        }
	    }
	    
	    sb.append(",\n  author=").append(super.getAuthor().getUsername())
	      .append(",\n  Status=").append(super.getStatus())
	      .append("\n}");
	    
	    return sb.toString();
	}

    public List<MultimediaContent> getMultimediaContentList() {
        return super.getMultimediaContentList();
    }
}
