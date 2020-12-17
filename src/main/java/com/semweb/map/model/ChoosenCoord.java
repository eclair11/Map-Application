package com.semweb.map.model;

public class ChoosenCoord {

    private String lat;

    private String lon;

    public ChoosenCoord() {
    }

    public ChoosenCoord(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "ChoosenCoord [lat=" + lat + ", lon=" + lon + "]";
    }

    
}
