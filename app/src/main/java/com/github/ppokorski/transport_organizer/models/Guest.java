package com.github.ppokorski.transport_organizer.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Guest {
    @Id
    private long id;
    private String name;
    private String address;
    private String phoneNumber;

    private ToOne<Event> event;

    public Guest() {
        id = 0;
    }

    public Guest(long id, String name, String address, String phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phone_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public ToOne<Event> getEvent() {
        return event;
    }

    public Event getEventObject() {
        return event.getTarget();
    }
}
