package EVENTO;

import ELEMENT.AbstractElement;
import POI.PointOfInterest;
import USER.User;

import java.time.LocalDateTime;

public class Event extends AbstractElement {
	private String scope;
	private String activity;
	private String organization;
	private String theme;
	private String category;
	private double price;
	private PointOfInterest location;
	private LocalDateTime time;
	
	public Event(String name, String description, User author, String scope,
				 String activity, String organization, String theme, String category,
				 double price, LocalDateTime time) {
		super(name, description, author);
		this.scope = scope;
		this.activity = activity;
		this.organization = organization;
		this.theme = theme;
		this.category = category;
		this.price = price;
		this.time = time;
		super.setPublished(true);
	}
	
	@Override
    public String toString() {
        return "Evento {" +
                "\n  id='" + super.getId() +
                ",\n  name='" + super.getName() +
                ",\n  description='" + super.getDescription() +
                ",\n  scope=" + scope +
                ",\n  activity=" + activity +
                ",\n  organization=" + organization +
                ",\n  theme=" + theme +
                ",\n  category=" + category +
                ",\n  price=" + price +
//                ",\n  location=" + location +
                ",\n  author=" + super.getAuthor() +
                ",\n  time=" + time +
                ",\n  published=" + super.isPublished() +
                "\n}";
    }

	public PointOfInterest getLocation() {
		return location;
	}
	public void setLocation(PointOfInterest location) { this.location = location; }

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String teme) {
		this.theme = teme;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
