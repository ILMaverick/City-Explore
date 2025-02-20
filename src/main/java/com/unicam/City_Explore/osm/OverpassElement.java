package com.unicam.City_Explore.osm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverpassElement {
    public String type;
    public long id;
    public double lat;
    public double lon;
    public Tags tags;

    @Override
    public String toString() {
        String name = (tags != null && tags.name != null) ? tags.name : "N/A";
        return "ID: " + id + " | Name: " + name + " | Lat: " + lat + " | Lon: " + lon + " | tipo: " + type;
    }
}
