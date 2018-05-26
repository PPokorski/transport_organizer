package com.github.ppokorski.transport_organizer.models;

public class Identificable {
    protected long id;

    public Identificable() {

    }

    public Identificable(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
