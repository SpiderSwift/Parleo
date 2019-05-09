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
        startTime = new Date();
        endDate = new Date();
    }

    public CreateEventModel(String name,
                            String description,
                            int maxParticipants,
                            float latitude,
                            float longitude,
                            Date startTime,
                            Date endDate,
                            String languageCode) {
        this.name = name;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.endDate = endDate;
        this.languageCode = languageCode;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
