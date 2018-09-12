package com.github.ppokorski.transport_organizer.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Donor {
    @Id
    private long id;
    private String contactName;
    private String companyName;
    private String address;
    private String phoneNumber;

    private ToOne<Event> event;

    public Donor() {
        id = 0;
    }

    public Donor(long id, String contact_name, String company_name, String address, String phone_number) {
        this.id = id;
        this.contactName = contact_name;
        this.companyName = company_name;
        this.address = address;
        this.phoneNumber = phone_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contact_name) {
        this.contactName = contact_name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company_name) {
        this.companyName = company_name;
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
