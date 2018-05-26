package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class AvailableHours extends Identificable implements Parcelable {
    private Date begin;
    private Date end;

    public AvailableHours() {
        super();
    }

    public AvailableHours(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    private AvailableHours(Parcel in) {
        this.id = in.readLong();
        this.begin = new Date(in.readLong());
        this.end = new Date(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeLong(begin.getTime());
        out.writeLong(begin.getTime());
    }

    public static final Parcelable.Creator<AvailableHours> CREATOR = new Parcelable.Creator<AvailableHours>() {
        @Override
        public AvailableHours createFromParcel(Parcel in) {
            return new AvailableHours(in);
        }

        @Override
        public AvailableHours[] newArray(int size) {
            return new AvailableHours[size];
        }
    };

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
}
