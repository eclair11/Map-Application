package com.semweb.map.model;

public class Bus {

    private String id;

    private String description;

    private String label;

    private Double latitude;
    
    private Double longitude;

    public Bus() {
    }

    public Bus(String id, String description, String label, Double latitude, Double longitude) {
        this.id = id;
        this.description = description;
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Bus [description=" + description + ", id=" + id + ", label=" + label + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }

}
