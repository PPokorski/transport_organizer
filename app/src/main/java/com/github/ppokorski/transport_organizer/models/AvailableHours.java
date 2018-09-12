package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class AvailableHours {
    @Id
    private long id;
    private Date begin;
    private Date end;

    private ToOne<Volunteer> volunteer;
    private ToOne<Transport> transport;

    public AvailableHours() {
        id = 0;
        begin = new Date();
        end = new Date();
    }

    public AvailableHours(long id, Date begin, Date end) {
        this.id = id;
        this.begin = begin;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ToOne<Volunteer> getVolunteer() {
        return volunteer;
    }

    public Volunteer getVolunteerObject() {
        return volunteer.getTarget();
    }

    public ToOne<Transport> getTransport() {
        return transport;
    }

    public Transport getTransportObject() {
        return transport.getTarget();
    }
}
