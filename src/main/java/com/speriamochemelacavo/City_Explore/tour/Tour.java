package tour;

import java.util.ArrayList;
import java.util.List;

import contenuti.MultimediaContent;
import element.AbstractElement;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import user.User;

@Entity
public class Tour extends AbstractElement {
	@OneToMany
	private List<Way> wayList;
	
	@OneToMany
	private final List<MultimediaContent> multimediaContentList;

	public Tour(String name, String description, User author) {
		super(name, description, author);
		this.wayList = new ArrayList<>();
		this.multimediaContentList = new ArrayList<>();
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
