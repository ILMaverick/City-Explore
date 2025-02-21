package com.unicam.City_Explore.elementi;

import jakarta.persistence.Entity;

public interface Element {
	int getId();
	String getName();
	String getDescription();
	boolean isPublished();
}
