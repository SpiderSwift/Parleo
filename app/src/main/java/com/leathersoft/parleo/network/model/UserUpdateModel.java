package com.leathersoft.parleo.network.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserUpdateModel implements Serializable {

    String name;
    String about;
    Date birthdate;
    boolean gender;


    List<Language> languages;
    List<String> hobbies;

//    public UserUpdateModel(User user) {
//        this.name = user.getName();
//        this.about = user.getAbout();
//        this.birthdate = user.getBirthdate();
//        this.gender = user.isGender();
//        this.languages = user.getLanguages();
//        this.hobbies = user.getHobbies();
//    }


    public UserUpdateModel() {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
