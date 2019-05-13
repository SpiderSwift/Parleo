package com.leathersoft.parleo.messaging;

import java.io.Serializable;

public class Interest implements Serializable {

    private String name;
    private int chosen;

    public Interest(String name) {
        this.name = name;
        chosen = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isChosen() {
        return chosen;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
    }


    @Override
    public String toString() {
        return "Interest{" +
                "name='" + name + '\'' +
                ", chosen=" + chosen +
                '}';
    }
}
