package com.semweb.map.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SparqlHospitalRequestModel {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String item;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String nbLits;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String image;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String siteWeb;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String ville;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String sitelinkEn;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String sitelinkFr;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String coord;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String coordinate;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String coordinate_node;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String lat;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private String lon;

    public SparqlHospitalRequestModel() {

    }

    public SparqlHospitalRequestModel(String item, String nbLits, String image, String siteWeb, String ville,
            String sitelinkEn, String sitelinkFr, String coord, String coordinate, String coordinate_node, String lat,
            String lon) {
        this.item = item;
        this.nbLits = nbLits;
        this.image = image;
        this.siteWeb = siteWeb;
        this.ville = ville;
        this.sitelinkEn = sitelinkEn;
        this.sitelinkFr = sitelinkFr;
        this.coord = coord;
        this.coordinate = coordinate;
        this.coordinate_node = coordinate_node;
        this.lat = lat;
        this.lon = lon;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNbLits() {
        return nbLits;
    }

    public void setNbLits(String nbLits) {
        this.nbLits = nbLits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSitelinkEn() {
        return sitelinkEn;
    }

    public void setSitelinkEn(String sitelinkEn) {
        this.sitelinkEn = sitelinkEn;
    }

    public String getSitelinkFr() {
        return sitelinkFr;
    }

    public void setSitelinkFr(String sitelinkFr) {
        this.sitelinkFr = sitelinkFr;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate_node() {
        return coordinate_node;
    }

    public void setCoordinate_node(String coordinate_node) {
        this.coordinate_node = coordinate_node;
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
        return "SparqlHospitalRequestModel [coord=" + coord + ", coordinate=" + coordinate + ", coordinate_node="
                + coordinate_node + ", image=" + image + ", item=" + item + ", lat=" + lat + ", lon=" + lon
                + ", nbLits=" + nbLits + ", siteWeb=" + siteWeb + ", sitelinkEn=" + sitelinkEn + ", sitelinkFr="
                + sitelinkFr + ", ville=" + ville + "]";
    }

    
    

   

    

}
