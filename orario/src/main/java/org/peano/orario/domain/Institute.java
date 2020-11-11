package org.peano.orario.domain;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="institutes")
public class Institute extends PanacheMongoEntity {
    public String name;
    public List<String> topics;
    public List<String> hours; 

    public static List<Institute> find(){
        return Institute.listAll();
    }
    public static Institute findByName(String name){
        return find("name", name).firstResult();
    }

}
