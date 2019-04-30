package com.leathersoft.parleo.network.model;

import java.util.List;

public class AccountResponse {

    private List<User> entities;

    private int pageNumber;
    private int pageSize;
    private int totalAmount;

    public List<User> getEntities() {
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
