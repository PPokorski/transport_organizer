package com.github.ppokorski.transport_organizer.models;

import java.util.ArrayList;

import io.objectbox.converter.PropertyConverter;

public enum Size {
    SMALL(0, "Small"),
    MEDIUM(1, "Medium"),
    LARGE(2, "Large");

    public static class SizeConverter implements PropertyConverter<Size, Integer> {
        @Override
        public Size convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            for (Size size : Size.values())
            {
                if (size.key == databaseValue)
                {
                    return size;
                }
            }
            return Size.SMALL;
        }

        @Override
        public Integer convertToDatabaseValue(Size entityProperty) {
            return entityProperty == null ? null : entityProperty.key;
        }
    }

    public static ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        for (Size size : Size.values())
        {
            values.add(size.value);
        }
        return values;
    }

    private int key;
    private String value;

    Size(int key, String value) {
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
