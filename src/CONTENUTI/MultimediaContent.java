package CONTENUTI;

import POI.PointOfInterest;

import USER.User;

import java.time.LocalDateTime;

public class MultimediaContent {

    private String id;
    private String name;
    private String description;
    private FormatFileEnum formatFileEnum;
    private float duration;
    private float dimension;
    private float resolution;
    private LocalDateTime dataCreation;
    private User author;
    private boolean published;
    private PointOfInterest pointOfInterest;

    public MultimediaContent(String name, String description, User author) {
        this.id = "custom_" + System.currentTimeMillis();
        this.name = (name != null ) ? name : "Senza nome";
        this.description = description;
        this.author = author;
        this.published = false;
    }

    @Override
    public String toString() {
        return "MultimediaContent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", formatFileEnum=" + formatFileEnum +
                ", duration=" + duration +
                ", dimension=" + dimension +
                ", resolution=" + resolution +
                ", dataCreation=" + dataCreation +
                ", author=" + author +
                ", poi=" + pointOfInterest +
                ", published=" + published +
                '}';
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }
    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }

}
