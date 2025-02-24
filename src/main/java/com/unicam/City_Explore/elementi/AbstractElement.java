package com.unicam.City_Explore.elementi;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.contenuti.MultimediaContent;
import com.unicam.City_Explore.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@Component
public abstract class AbstractElement implements Element {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER)
	private final List<MultimediaContent> multimediaContentList = new ArrayList<>();
	
	@ManyToOne
	private User author;

	private Status status = Status.PENDING;
	
	public AbstractElement() {
	}

	public AbstractElement(String name, String description, User author) {
		this.name = (name != null ) ? name : "Senza nome";
		this.description = description;
		this.author = author;
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
	
	public List<MultimediaContent> getMultimediaContentList() {
        return multimediaContentList;
    }
}
