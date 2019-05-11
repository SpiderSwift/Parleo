package com.leathersoft.parleo.network.model;


import java.io.Serializable;
import java.util.Date;

public class MessageViewModel implements Serializable {

    int id;
    String text;
    String status;
    Date createdOn;
    Date viewedOn;
    boolean isDeleted;
    String senderId;
    String chatId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getViewedOn() {
        return viewedOn;
    }

    public void setViewedOn(Date viewedOn) {
        this.viewedOn = viewedOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }


    @Override
    public String toString() {
        return "MessageViewModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", createdOn=" + createdOn +
                ", viewedOn=" + viewedOn +
                ", isDeleted=" + isDeleted +
                ", senderId='" + senderId + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
