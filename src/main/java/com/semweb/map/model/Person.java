package com.semweb.map.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private String type;

    @JsonProperty("http://schema.org/name")
    private String name;

    @JsonProperty("http://schema.org/knows")
    private Link knows;

    public Person() {
    }

    public Person(String id, String type, String name, Link knows) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.knows = knows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getKnows() {
        return knows;
    }

    public void setKnows(Link knows) {
        this.knows = knows;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", knows=" + knows + ", name=" + name + ", type=" + type + "]";
    }

    public static class Link {
        @JsonProperty("@id")
        private String id;

        public Link() {
        }

        public Link(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Link [id=" + id + "]";
        }

    }

}