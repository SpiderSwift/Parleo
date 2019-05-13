package com.leathersoft.parleo.network.model;


import com.leathersoft.parleo.network.model.Hobby;
import com.leathersoft.parleo.network.model.Language;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    String id;
    String accountImage;
    String name;
    String about;
    Date birthdate;
    boolean gender;
    int distanceFromCurrentUser;
    String email;
    boolean isFriend;

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    List<Language> languages;
    List<Hobby> hobbies;

    List<AttendingEvent> attendingEvents;
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


    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getDistanceFromCurrentUser() {
        return distanceFromCurrentUser;
    }

    public void setDistanceFromCurrentUser(int distanceFromCurrentUser) {
        this.distanceFromCurrentUser = distanceFromCurrentUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AttendingEvent> getAttendingEvents() {
        return attendingEvents;
    }

    public void setAttendingEvents(List<AttendingEvent> attendingEvents) {
        this.attendingEvents = attendingEvents;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, accountImage, name, about, gender, distanceFromCurrentUser, email, languages, hobbies);
    }

    public class AttendingEvent implements Serializable{
         String id;
         String image;
         String name;

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "AttendingEvent{" +
                    "id='" + id + '\'' +
                    ", image='" + image + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
