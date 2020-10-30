package org.peano.orario.domain;

public class Room {

    private String name;

    public Room() {
    }

    public Room(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
