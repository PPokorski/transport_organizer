package com.github.ppokorski.transport_organizer.models;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class Volunteer {
    @Id
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    private ToOne<Event> event;

    @Backlink
    private ToMany<Car> cars;

    @Backlink
    private ToMany<AvailableHours> availableHours;

    public Volunteer() {
        id = 0;
    }

    public Volunteer(long id, String name, String surname, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phone_number;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ToOne<Event> getEvent() {
        return event;
    }

    public Event getEventObject() {
        return event.getTarget();
    }

    public ToMany<Car> getCars() {
        return cars;
    }

    public List<Car> getCarsList() {
        return cars.getListFactory().createList();
    }

    public ToMany<AvailableHours> getAvailableHours() {
        return availableHours;
    }

    public List<AvailableHours> getAvailableHoursList() {
        return availableHours.getListFactory().createList();
    }
}
