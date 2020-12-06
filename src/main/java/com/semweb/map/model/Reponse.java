package com.semweb.map.model;

public class Reponse {

    private String reference;

    private String label;

    private String description;

    public Reponse() {
    }

    public Reponse(String reference, String label, String description) {
        this.reference = reference;
        this.label = label;
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
