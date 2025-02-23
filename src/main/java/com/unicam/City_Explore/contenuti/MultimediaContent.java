package com.unicam.City_Explore.contenuti;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.unicam.City_Explore.tour.Tour;
import jakarta.persistence.*;

import com.unicam.City_Explore.elementi.Status;
import com.unicam.City_Explore.poi.PointOfInterest;
import com.unicam.City_Explore.user.User;


@Entity
@Component
public class MultimediaContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private FormatFileEnum formatFileEnum;
    private float duration;
    private float dimension;
    private float resolution;
    private LocalDateTime dataCreation;
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
    private Status status = Status.PENDING;
    @ManyToOne
    private PointOfInterest pointOfInterest;
    @ManyToOne
    private Tour tour;
    
    public MultimediaContent() {
		super();
	}

	public MultimediaContent(String name, String description, User author) {
        this.name = (name != null ) ? name : "Senza nome";
        this.description = description;
        this.author = author;
    }

    @Override
    public String toString() {
        return "MultimediaContent {" +
                "\n  id='" + id + '\'' +
                ",\n  name='" + name + '\'' +
                ",\n  description='" + description + '\'' +
                ",\n  formatFileEnum=" + formatFileEnum +
                ",\n  duration=" + duration +
                ",\n  dimension=" + dimension +
                ",\n  resolution=" + resolution +
                ",\n  dataCreation=" + dataCreation +
                ",\n  author=" + author +
                ",\n  poi=" + pointOfInterest +
                ",\n  elementStatus=" + status +
                "\n}";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public FormatFileEnum getFormatFileEnum() { return formatFileEnum;}
    public void setFormatFileEnum(FormatFileEnum formatFileEnum) { this.formatFileEnum = formatFileEnum; }
    public float getDuration() { return duration; }
    public void setDuration(float duration) { this.duration = duration; }
    public float getDimension() { return dimension; }
    public void setDimension(float dimension) { this.dimension = dimension; }
    public float getResolution() { return resolution; }
    public void setResolution(float resolution) { this.resolution = resolution; }
    public LocalDateTime getDataCreation() { return dataCreation; }
    public void setDataCreation(LocalDateTime dataCreation) { this.dataCreation = dataCreation; }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }
    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }
    public Tour getTour() { return tour;}
    public void setTour(Tour tour) { this.tour = tour; }
}
