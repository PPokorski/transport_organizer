package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Guest extends Identificable implements Parcelable {
    private String name;
    private String address;
    private String phone_number;

    public Guest() {
    }

    public Guest(String name, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    private Guest(Parcel in) {
        this.id = in.readLong();
        name = in.readString();
        address = in.readString();
        phone_number = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
        out.writeString(address);
        out.writeString(phone_number);
    }

    public static final Parcelable.Creator<Guest> CREATOR = new Parcelable.Creator<Guest>() {
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

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
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
}
