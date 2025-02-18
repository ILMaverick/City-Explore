package com.speriamochemelacavo.City_Explore.OSM;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverpassResponse {
    private OverpassElement[] elements;

    public OverpassElement[] getElements() {
        return elements;
    }
    public void setElements(OverpassElement[] elements) {
        this.elements = elements;
    }
}
