package com.speriamochemelacavo.City_Explore.TOUR;

import java.util.ArrayList;
import java.util.List;

import com.speriamochemelacavo.City_Explore.ELEMENT.AbstractElement;
import com.speriamochemelacavo.City_Explore.USER.User;
import jakarta.persistence.Entity;

@Entity
public class Tour extends AbstractElement {
	private List<Way> wayList;

	public Tour(String name, String description, User author) {
		super(name, description, author);
		this.wayList = new ArrayList<>();
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
	      .append("\n  id='").append(super.getId()).append('\'')
	      .append(",\n  name='").append(super.getName()).append('\'')
	      .append(",\n  description='").append(super.getDescription()).append('\'')
	      .append(",\n  percorsi=");
	    
	    if (wayList == null || wayList.isEmpty()) {
	        sb.append("Nessun percorso");
	    } else {
	        // Itera la lista dei percorsi e chiama il loro toString()
	        for (Way p : wayList) {
	            sb.append("\n    ").append(p.toString());
	        }
	    }
	    
	    sb.append(",\n  author=").append(super.getAuthor())
	      .append(",\n  published=").append(super.isPublished())
	      .append("\n}");
	    
	    return sb.toString();
	}

}
