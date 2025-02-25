package com.unicam.City_Explore.evento;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contest.Contest;
import com.unicam.City_Explore.elementi.AbstractElement;
import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;

@Entity
@Component
public class Event extends AbstractElement {
	private String scope;
	private String activity;
	private String organization;
	private String theme;
	private String category;
	private double price;
	private LocalDateTime time;
	private boolean isOpen;
	@ManyToMany(fetch = FetchType.EAGER)
	private final List<PointOfInterest> pointOfInterestList = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER)
	private final List<Contest> contestList = new ArrayList<>();
	@OneToMany(fetch = FetchType.EAGER)
	private final List<User> participants = new ArrayList<>();
	
	public Event() {
	}

	public Event(String name, String description, User author, String scope,
				 String activity, String organization, String theme, String category,
				 double price, LocalDateTime time, boolean isOpen) {
		super(name, description, author);
		this.scope = scope;
		this.activity = activity;
		this.organization = organization;
		this.theme = theme;
		this.category = category;
		this.price = price;
		this.time = time;
		this.isOpen = isOpen;
		super.setStatus(Status.APPROVED);
	}
	
	@Override
    public String toString() {
        return "Evento {" +
                "\n  id=" + super.getId() +
                ",\n  name=" + super.getName() +
                ",\n  description='" + super.getDescription() +
                ",\n  scope=" + scope +
                ",\n  activity=" + activity +
                ",\n  organization=" + organization +
                ",\n  theme=" + theme +
                ",\n  category=" + category +
                ",\n  price=" + price +
//                ",\n  pointOfInterestList=" + pointOfInterestList +
                ",\n  author=" + super.getAuthor() +
                ",\n  time=" + time +
                ",\n  Status=" + super.getStatus() +
                "\n}";
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

	public void setTheme(String theme) {
		this.theme = theme;
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

	public List<User> getParticipants() {
		return participants;
	}
	public List<PointOfInterest> getPointOfInterestList() { return pointOfInterestList; }

	public List<Contest> getContestList() { return contestList; }

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
