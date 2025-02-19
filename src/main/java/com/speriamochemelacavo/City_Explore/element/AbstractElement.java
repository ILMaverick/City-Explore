package element;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import user.User;

@Entity
public abstract class AbstractElement implements Element {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User author;

	private Status status;
	
	public AbstractElement(String name, String description, User author) {
		this.name = (name != null ) ? name : "Senza nome";
		this.description = description;
		this.author = author;
		this.status = Status.PENDING;
	}
	
	public boolean isPublished() {
		if(status==Status.APPROVED || status==Status.REPORTED)
			return true;
		else return false;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
