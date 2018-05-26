package com.github.ppokorski.transport_organizer.models;

import java.util.Date;

public class Event extends Identificable {
    private String name;
    private String address;
    private Date date;

    public Event() {
        super();
    }

    public Event(String name, String address, Date date) {
        this.name = name;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
