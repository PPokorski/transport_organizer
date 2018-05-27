package com.github.ppokorski.transport_organizer.models;

import java.util.ArrayList;

public enum Status {
    UNASSIGNED(0, "Unassigned"),
    ASSIGNED(1, "Assigned"),
    CONFIRMED(2, "Confirmed");

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
