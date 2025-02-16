package EVENTO;

import ELEMENT.AbstractElement;
import POI.PointOfInterest;
import USER.User;

public class Evento extends AbstractElement {
	private String scope;
	private String activity;
	private String organization;
	private String theme;
	private String category;
	private double price;
	private PointOfInterest location;
	private double orario;
	
	public Evento(String name, String description, User author, String scope,
			String activity, String organization, String theme, String category,
			double price, PointOfInterest location, double orario) {
		super(name, description, author);
		this.scope = scope;
		this.activity = activity;
		this.organization = organization;
		this.theme = theme;
		this.category = category;
		this.price = price;
		this.location = location;
		this.orario = orario;
		super.setPublished(true);
	}
	
	@Override
    public String toString() {
        return "Evento {" +
                "\n  id='" + super.getId() + '\'' +
                ",\n  name='" + super.getName() + '\'' +
                ",\n  description='" + super.getDescription() + '\'' +
                ",\n  scopo=" + scope +
                ",\n  activity=" + activity +
                ",\n  organization=" + organization +
                ",\n  tema=" + theme +
                ",\n  categoria=" + category +
                ",\n  price=" + price +
                ",\n  luogo=" + location.toString() +
                ",\n  author=" + super.getAuthor() +
                ",\n  orario=" + orario +
                ",\n  published=" + super.isPublished() +
                "\n}";
    }

	public PointOfInterest getLocation() {
		return location;
	}

	public double getOrario() {
		return orario;
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
