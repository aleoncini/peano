package org.peano.core.model;

import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="timetables")
public class Timetable extends PanacheMongoEntity {

    public String name;
    public String owner;
    public String description;

    public static List<Timetable> getTimetablesByOwner(String owner){
        return Timetable.find("owner = ?1", owner).list();
    }

}
