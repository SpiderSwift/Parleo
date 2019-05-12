package com.leathersoft.parleo.network.model;

import java.io.Serializable;
import java.util.Objects;

public class Hobby implements Serializable {

    String name;
    String category;


    public Hobby() {
    }


    public Hobby(String name, String category) {
        this.name = name;
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return Objects.equals(name, hobby.name) &&
                Objects.equals(category, hobby.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
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
