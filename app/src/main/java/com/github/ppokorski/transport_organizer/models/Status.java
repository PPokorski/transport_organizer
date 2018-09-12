package com.github.ppokorski.transport_organizer.models;

import java.util.ArrayList;

import io.objectbox.converter.PropertyConverter;

public enum Status {
    UNASSIGNED(0, "Unassigned"),
    ASSIGNED(1, "Assigned"),
    CONFIRMED(2, "Confirmed");

    public static class StatusConverter implements PropertyConverter<Status, Integer> {
        @Override
        public Status convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            for (Status status : Status.values())
            {
                if (status.key == databaseValue)
                {
                    return status;
                }
            }
            return Status.UNASSIGNED;
        }

        @Override
        public Integer convertToDatabaseValue(Status entityProperty) {
            return entityProperty == null ? null : entityProperty.key;
        }
    }

    public static ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        for (Status status : Status.values())
        {
            values.add(status.value);
        }
        return values;
    }

    private int key;
    private String value;

    Status(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
