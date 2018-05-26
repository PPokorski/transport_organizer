package com.github.ppokorski.transport_organizer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Identificable implements Parcelable {
    private String name;
    private String surname;
    private String phone_number;
    private String email;
    private ArrayList<Car> cars;
    private ArrayList<AvailableHours> available_hours;

    public Volunteer() {
        super();

        cars = new ArrayList<>();
        available_hours = new ArrayList<>();
    }

    public Volunteer(String name, String surname, String phone_number, String email, ArrayList<Car> cars, ArrayList<AvailableHours> available_hours) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
        this.cars = cars;
        this.available_hours = available_hours;
    }

    private Volunteer(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.surname = in.readString();
        this.phone_number = in.readString();
        this.email = in.readString();
        cars = new ArrayList<>();
        in.readTypedList(cars, Car.CREATOR);
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
        out.writeString(surname);
        out.writeString(phone_number);
        out.writeString(email);
        out.writeTypedList(cars);
        out.writeTypedList(available_hours);
    }

    public static final Parcelable.Creator<Volunteer> CREATOR = new Parcelable.Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel in) {
            return new Volunteer(in);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public ArrayList<AvailableHours> getAvailableHours() {
        return available_hours;
    }

    public void setAvailableHours(ArrayList<AvailableHours> available_hours) {
        this.available_hours = available_hours;
    }
}
