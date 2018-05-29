package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Transport extends Identificable implements Parcelable {
    private String name;
    private String pickup_address;
    private String target_address;
    private Size size;
    private Status status;

    private Volunteer volunteer;
    private Donor donor;
    private Guest guest;

     private ArrayList<AvailableHours> available_hours;

    public Transport() {
        super();

        size = Size.SMALL;
        status = Status.UNASSIGNED;
        available_hours = new ArrayList<>();
    }

    public Transport(String name, String pickup_address, String target_address, Size size, Status status, ArrayList<AvailableHours> available_hours) {
        this.name = name;
        this.pickup_address = pickup_address;
        this.target_address = target_address;
        this.size = size;
        this.status = status;
        this.available_hours = available_hours;
    }

    private Transport(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.pickup_address = in.readString();
        this.target_address = in.readString();
        this.size = Size.values()[in.readInt()];
        this.status = Status.values()[in.readInt()];
        this.volunteer = in.readParcelable(getClass().getClassLoader());
        this.donor = in.readParcelable(getClass().getClassLoader());
        this.guest = in.readParcelable(getClass().getClassLoader());
        available_hours = new ArrayList<>();
        in.readTypedList(available_hours, AvailableHours.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
        out.writeString(pickup_address);
        out.writeString(target_address);
        out.writeInt(size.ordinal());
        out.writeInt(status.ordinal());
        out.writeParcelable(volunteer, flags);
        out.writeParcelable(donor, flags);
        out.writeParcelable(guest, flags);
        out.writeTypedList(available_hours);
    }

    public static final Parcelable.Creator<Transport> CREATOR = new Parcelable.Creator<Transport>() {
        public Transport createFromParcel(Parcel in) {
            return new Transport(in);
        }

        public Transport[] newArray(int size) {
            return new Transport[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickupAddress() {
        return pickup_address;
    }

    public void setPickupAddress(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getTargetAddress() {
        return target_address;
    }

    public void setTargetAddress(String target_address) {
        this.target_address = target_address;
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

    public String getReporterPhoneNumber() {
        if (donor == null && guest == null)
        {
            throw new IllegalStateException("Either Donor or Guest needs to be initialized");
        }

        if (donor != null)
        {
            return donor.getPhoneNumber();
        }
        else
        {
            return guest.getPhoneNumber();
        }
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        if (this.guest != null && donor != null)
        {
            throw new IllegalStateException("The Donor cannot be set if Guest is not null");
        }
        this.donor = donor;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        if (this.donor != null && guest != null)
        {
            throw new IllegalStateException("The Guest cannot be set if Donor is not null");
        }
        this.guest = guest;
    }

    public ArrayList<AvailableHours> getAvailableHours() {
        return available_hours;
    }

    public void setAvailableHours(ArrayList<AvailableHours> available_hours) {
        this.available_hours = available_hours;
    }
}
