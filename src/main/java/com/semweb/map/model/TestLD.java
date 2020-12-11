package com.semweb.map.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestLD {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("@graph")
    private HospitalLD[] graph;

    public TestLD() {
    }

    public TestLD(HospitalLD[] graph) {
        this.graph = graph;
    }

    public HospitalLD[] getGraph() {
        return graph;
    }

    public void setGraph(HospitalLD[] graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "TestLD [graph=" + Arrays.toString(graph) + "]";
    }

    public static class HospitalLD {

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("@id")
        private String id;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("name")
        private String name;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("wikipedia")
        private String[] wikipedia;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("latitude")
        private String latitude;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("longitude")
        private String longitude;

        public HospitalLD() {
        }

        public HospitalLD(String id, String name, String[] wikipedia, String latitude, String longitude) {
            this.id = id;
            this.name = name;
            this.wikipedia = wikipedia;
            this.latitude = latitude;
            this.longitude = longitude;
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

        public String[] getWikipedia() {
            return wikipedia;
        }

        public void setWikipedia(String[] wikipedia) {
            this.wikipedia = wikipedia;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "HospitalLD [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
                    + ", wikipedia=" + Arrays.toString(wikipedia) + "]";
        }

        
    }

    

    
    
}
