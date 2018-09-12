package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Car {
    @Id
    private long id;
    private String name;
    @Convert(converter = Size.SizeConverter.class, dbType = Integer.class)
    private Size size;

    private ToOne<Volunteer> volunteer;

    public Car() {
        id = 0;
        size = Size.SMALL;
    }

    public Car(long id, String name, Size size, Volunteer volunteer) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.volunteer.setTarget(volunteer);
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ToOne<Volunteer> getVolunteer() {
        return volunteer;
    }

    public Volunteer getVolunteerObject() {
        return volunteer.getTarget();
    }
}
