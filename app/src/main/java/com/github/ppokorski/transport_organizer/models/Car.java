package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Car extends Identificable implements Parcelable {
    private String name;
    private Size size;

    public Car() {
        super();
        size = Size.SMALL;
    }

    public Car(String name, Size size) {
        this.name = name;
        this.size = size;
    }

    private Car(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.size = Size.values()[in.readInt()];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
        out.writeInt(size.ordinal());
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

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
}
