package com.leathersoft.parleo.network.model;

import java.util.List;

public class EventResponse {

    private List<Event> entities;

    private int pageNumber;
    private int pageSize;
    private int totalAmount;

    public List<Event> getEntities() {
        return entities;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
