package com.semweb.map.model;

import lombok.Data;

@Data
public class Coordinate {

    private double lon;
    private double lat;

    public Coordinate() {
    }

    public Coordinate(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

}
