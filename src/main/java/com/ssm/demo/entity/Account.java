package com.ssm.demo.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private Double scroe;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getScroe() {
        return scroe;
    }

    public void setScroe(Double scroe) {
        this.scroe = scroe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
