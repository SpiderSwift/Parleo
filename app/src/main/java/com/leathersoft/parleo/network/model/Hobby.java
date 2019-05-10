package com.leathersoft.parleo.network.model;

import java.io.Serializable;

public class Hobby implements Serializable {

    String name;
    String category;


    public Hobby() {
    }


    public Hobby(String name, String category) {
        this.name = name;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
