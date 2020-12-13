package com.semweb.map.model;

import lombok.Data;

@Data
public class ReponseVille {

    private String name;

    public ReponseVille() {
    }

    public ReponseVille(String name) {
        this.name = name;
    }

}
