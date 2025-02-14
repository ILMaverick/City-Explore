package INTERFACES;

import USER.User;

public abstract class AbstractElement implements Element {
	private String id;
	private String name;
	private String description;
	private User author;
	private boolean published;
	
	public AbstractElement(String name, String description, User author) {
		this.id = "custom_" + System.currentTimeMillis();
		this.name = (name != null ) ? name : "Senza nome";
		this.description = description;
		this.author = author;
		this.published = false;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
