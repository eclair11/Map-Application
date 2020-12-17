package com.semweb.map.model;

import lombok.Data;

@Data
public class ReponseVille {

    private String name;

    private String reset;

    public ReponseVille() {
    }

    public ReponseVille(String name, String reset) {
        this.name = name;
        this.reset = reset;
    }



}
