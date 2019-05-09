package com.leathersoft.parleo.network.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Event implements Serializable {

    String id;
    String name;
    String description;

    String image;
    int maxParticipants;
    float latitude;
    float longitude;
    boolean isFinished;

    Date startTime;
    Date endDate;

    int distanceFromCurrentUser;
    String email;


    List<Language> languages;
    List<Hobby> hobbies;

//     "id": "9f8886ec-b6a6-4a9b-80c5-a05e10afe83c",
//      "name": "Awesome event",
//      "description": "Event",
//      "maxParticipants": 5,
//      "latitude": 53.8852533,
//      "longitude": 27.5394921,
//      "isFinished": true,

//      "startTime": "2019-04-27T20:05:02.706+00:00",
//      "endDate": "2019-04-27T20:05:02.707+00:00",

//      "creator": {
//        "id": "d7b781ca-07d1-4709-b514-1fbe0f9022e9",
//        "image": null,
//        "name": null
//      },
//      "language": {
//        "id": "ru"
//      },
//      "participants": []


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return maxParticipants == event.maxParticipants &&
                Float.compare(event.latitude, latitude) == 0 &&
                Float.compare(event.longitude, longitude) == 0 &&
                isFinished == event.isFinished &&
                distanceFromCurrentUser == event.distanceFromCurrentUser &&
                Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(description, event.description) &&
                Objects.equals(email, event.email) &&
                Objects.equals(languages, event.languages) &&
                Objects.equals(hobbies, event.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, maxParticipants, latitude, longitude, isFinished, distanceFromCurrentUser, email, languages, hobbies);
    }
}
