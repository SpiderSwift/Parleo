package com.leathersoft.parleo.network.model;

import java.util.List;

public class MessageListModel {

    private List<MessageViewModel> entities;


    public MessageListModel(List<MessageViewModel> entities) {
        this.entities = entities;
    }

    public MessageListModel() {
    }


    public List<MessageViewModel> getEntities() {
        return entities;
    }

    public void setEntities(List<MessageViewModel> entities) {
        this.entities = entities;
    }
}
