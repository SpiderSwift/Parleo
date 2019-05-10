package com.leathersoft.parleo.network.model;

import java.io.Serializable;

public class Language implements Serializable {
    String code;
    int level;


    public Language() {
    }

    public Language(String code, int level) {
        this.code = code;
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Language{" +
                "code='" + code + '\'' +
                ", level=" + level +
                '}';
    }
}
