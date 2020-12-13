package com.semweb.map.model;

import lombok.Data;

@Data
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

}
