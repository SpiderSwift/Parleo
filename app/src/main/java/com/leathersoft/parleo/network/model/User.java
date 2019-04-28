package com.leathersoft.parleo.network.model;


import com.leathersoft.parleo.network.model.Hobby;
import com.leathersoft.parleo.network.model.Language;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    String id;
    String accountImage;
    String name;
    String about;
    //birthdate
    boolean gender;
    int distanceFromCurrentUser;
    String email;


    List<Language> languages;
    List<Hobby> hobbies;

//     "id": "a7727de6-8583-4479-99dc-00fa25972ac1",
//             "accountImage": null,
//             "name": null,
//             "about": null,
//             "birthdate": "0001-01-01T00:00:00",
//             "gender": false,
//             "distanceFromCurrentUser": 0,
//             "email": "npofa4ok@mail.ru",
//             "createdEvents": [],
//             "languages": [],
//             "friends": [],
//             "attendingEvents": [],
//             "hobbies": []


    public String getId() {
        return id;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public boolean isGender() {
        return gender;
    }

    public int getDistanceFromCurrentUser() {
        return distanceFromCurrentUser;
    }

    public String getEmail() {
        return email;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return gender == user.gender &&
                distanceFromCurrentUser == user.distanceFromCurrentUser &&
                Objects.equals(id, user.id) &&
                Objects.equals(accountImage, user.accountImage) &&
                Objects.equals(name, user.name) &&
                Objects.equals(about, user.about) &&
                Objects.equals(email, user.email) &&
                Objects.equals(languages, user.languages) &&
                Objects.equals(hobbies, user.hobbies);
    }
}
