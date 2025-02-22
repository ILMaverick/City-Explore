package com.unicam.City_Explore.tour;

import java.util.ArrayList;
import java.util.List;

import com.unicam.City_Explore.user.User;

public class TourBuilder {
	 private String name;
	 private String description;
	 private User author;
	 private final List<Way> ways = new ArrayList<>();
	    
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

	    public TourBuilder addWay(Way way) {
	        this.ways.add(way);
	        return this;
	    }

	    public Tour build() {
	        Tour tour = new Tour(name, description, author);
	        tour.setWayList(ways);
	        return tour;
	    }
	}
