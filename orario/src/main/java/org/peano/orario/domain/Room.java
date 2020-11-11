package org.peano.orario.domain;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Room extends PanacheMongoEntity {

    private String name;

    public Room() {
    }

    public Room(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
