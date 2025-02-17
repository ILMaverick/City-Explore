package ELEMENT;

import USER.User;

public abstract class AbstractElement implements Element {
	private int id;
	private String name;
	private String description;
	private User author;
	private boolean published;
	private ElementStatus status;
	
	public AbstractElement(String name, String description, User author) {
		this.name = (name != null ) ? name : "Senza nome";
		this.description = description;
		this.author = author;
		this.published = false;
		this.status = ElementStatus.Pending;
	}
	
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
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
	public ElementStatus getStatus() {
		return status;
	}
	public void setStatus(ElementStatus status) {
		this.status = status;
	}
}
