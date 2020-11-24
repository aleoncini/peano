package org.peano.orario.domain;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="departments")
public class Department extends PanacheMongoEntity {
    public String name;
    public List<String> topics;

    public static List<Department> find(){
        return listAll();
    }
    public static Department findByName(String name){
        return find("name", name).firstResult();
    }

}
