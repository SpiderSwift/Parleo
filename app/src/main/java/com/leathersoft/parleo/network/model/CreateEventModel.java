package com.leathersoft.parleo.network.model;

import java.util.Date;
import java.util.List;

public class CreateEventModel {

    String name;
    String description;
    int maxParticipants = 10;
    float latitude  = 22f;
    float longitude = 35f;
    boolean isFinished = false;

    //TODO
    Date startTime;
    Date endDate;

    String creatorId = "4377a0f9-f4d8-45b0-b263-0149dda2d634";

    String languageCode = "aa";

    public CreateEventModel() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
