package com.github.ppokorski.transport_organizer.models;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class Transport {
    @Id
    private long id;
    private String name;
    private String pickupAddress;
    private String targetAddress;
    @Convert(converter = Size.SizeConverter.class, dbType = Integer.class)
    private Size size;
    @Convert(converter = Status.StatusConverter.class, dbType = Integer.class)
    private Status status;

    private ToOne<Volunteer> volunteer;
    private ToOne<Donor> donor;
    private ToOne<Guest> guest;

    @Backlink
    private ToMany<AvailableHours> availableHours;

    public Transport() {
        id = 0;
        size = Size.SMALL;
        status = Status.UNASSIGNED;
    }

    public Transport(long id, String name, String pickup_address, String target_address, Size size, Status status) {
        this.id = id;
        this.name = name;
        this.pickupAddress = pickup_address;
        this.targetAddress = target_address;
        this.size = size;
        this.status = status;
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

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickup_address) {
        this.pickupAddress = pickup_address;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String target_address) {
        this.targetAddress = target_address;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ToOne<Volunteer> getVolunteer() {
        return volunteer;
    }

    public Volunteer getVolunteerObject() {
        return volunteer.getTarget();
    }

    public ToOne<Donor> getDonor() {
        return donor;
    }

    public Donor getDonorObject() {
        return donor.getTarget();
    }

    public void setDonorObject(Donor donor) {
        this.donor.setTarget(donor);
    }

    public ToOne<Guest> getGuest() {
        return guest;
    }

    public Guest getGuestObject() {
        return guest.getTarget();
    }

    public void setGuestObject(Guest guest) {
        this.guest.setTarget(guest);
    }

    public ToMany<AvailableHours> getAvailableHours() {
        return availableHours;
    }

    public List<AvailableHours> getAvailableHoursList() {
        return availableHours.getListFactory().createList();
    }
}
