package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Transport extends Identificable implements Parcelable {
    private String name;
    private String pickup_address;
    private String target_address;
    private Size size;

    private Volunteer volunteer;
    private Donor donor;
    private Guest guest;

    public Transport() {
        super();
    }

    public Transport(String name, String pickup_address, String target_address, Size size) {
        this.name = name;
        this.pickup_address = pickup_address;
        this.target_address = target_address;
        this.size = size;
    }

    private Transport(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.pickup_address = in.readString();
        this.target_address = in.readString();
        this.size = Size.values()[in.readInt()];
        this.volunteer = in.readParcelable(getClass().getClassLoader());
        this.donor = in.readParcelable(getClass().getClassLoader());
        this.guest = in.readParcelable(getClass().getClassLoader());
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
        out.writeParcelable(volunteer, flags);
        out.writeParcelable(donor, flags);
        out.writeParcelable(guest, flags);
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
        this.donor = donor;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
