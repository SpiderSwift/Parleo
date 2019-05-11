package com.leathersoft.parleo.network.model;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Objects;

public class ChatMember implements IUser {

    private String id;
    private String image;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMember that = (ChatMember) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ChatMember() {
    }

    public ChatMember(String id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return image;
    }

    public String getImage() {
        return image;
    }
}
