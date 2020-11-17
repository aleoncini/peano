package org.peano.orario.domain;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="schools")
public class School extends PanacheMongoEntity {
    public String name;
    public String admin;
    public int rooms;
    public String instituteId;

    public static List<School> find(){
        return listAll();
    }
    public static List<School> findByAdmin(String admin){
        return list("admin", admin);
    }
}
