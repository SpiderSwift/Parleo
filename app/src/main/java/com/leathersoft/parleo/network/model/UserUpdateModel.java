package com.leathersoft.parleo.network.model;

import java.io.Serializable;
import java.util.List;

public class UserUpdateModel implements Serializable {

    String name;
    String about;
    String birthdate;
    boolean gender;


    List<Language> languages;
    List<Hobby> hobbies;

    public UserUpdateModel(User user) {
        this.name = user.getName();
        this.about = user.getAbout();
        this.birthdate = user.getBirthdate();
        this.gender = user.isGender();
        this.languages = user.getLanguages();
        this.hobbies = user.getHobbies();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
