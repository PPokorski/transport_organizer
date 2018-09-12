package com.github.ppokorski.transport_organizer.models;

import java.util.Date;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Event {
    @Id
    private long id;
    private String name;
    private String address;
    private Date date;

    @Backlink
    private ToMany<Volunteer> volunteers;
    @Backlink
    private ToMany<Donor> donors;
    @Backlink
    private ToMany<Guest> guests;

    public Event() {
        id = 0;
    }

    public Event(long id, String name, String address, Date date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ToMany<Volunteer> getVolunteers() {
        return volunteers;
    }

    public List<Volunteer> getVolunteersList() {
        return volunteers.getListFactory().createList();
    }

    public ToMany<Donor> getDonors() {
        return donors;
    }

    public List<Donor> getDonorsList() {
        return donors.getListFactory().createList();
    }

    public ToMany<Guest> getGuests() {
        return guests;
    }

    public List<Guest> getGuestsList() {
        return guests.getListFactory().createList();
    }
}
