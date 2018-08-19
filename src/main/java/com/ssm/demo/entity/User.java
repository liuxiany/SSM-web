package com.ssm.demo.entity;


import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private String id;
    private String name;

    private Date date;

    public User(){}

    public User(String name){
        this.name = name;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
