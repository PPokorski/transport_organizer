package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Donor extends Identificable implements Parcelable {
    private String contact_name;
    private String company_name;
    private String address;
    private String phone_number;

    public Donor() {
        super();
    }

    public Donor(String contact_name, String company_name, String address, String phone_number) {
        this.contact_name = contact_name;
        this.company_name = company_name;
        this.address = address;
        this.phone_number = phone_number;
    }

    private Donor(Parcel in) {
        this.id = in.readLong();
        this.contact_name = in.readString();
        this.company_name = in.readString();
        this.address = in.readString();
        this.phone_number = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(contact_name);
        out.writeString(company_name);
        out.writeString(address);
        out.writeString(phone_number);
    }

    public static final Parcelable.Creator<Donor> CREATOR = new Parcelable.Creator<Donor>() {
        @Override
        public Donor createFromParcel(Parcel in) {
            return new Donor(in);
        }

        @Override
        public Donor[] newArray(int size) {
            return new Donor[size];
        }
    };

    public String getContactName() {
        return contact_name;
    }

    public void setContactName(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
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
