package com.leathersoft.parleo.messaging;

import android.os.Parcel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class LanguageModel implements Serializable {

    private String code;
    private String name;
    private int level;
    private int chosen;

    public static final Map<Integer, String> langLevelMap = new HashMap<>();

    static {
        langLevelMap.put(0, "Beginner");
        langLevelMap.put(1, "Elementary");
        langLevelMap.put(2, "Intermediate");
        langLevelMap.put(3, "Advanced");
        langLevelMap.put(4, "Native");
    }

    public LanguageModel() {
    }

    public LanguageModel(String code, String name, int level, int chosen) {
        this.code = code;
        this.name = name;
        this.level = level;
        this.chosen = chosen;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int isChosen() {
        return chosen;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
    }

    @Override
    public String toString() {
        return "LanguageModel{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", chosen=" + chosen +
                '}';
    }
}
