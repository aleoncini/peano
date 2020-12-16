package org.peano.orario.domain;

import java.util.ArrayList;
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

    public String getName(){
        return this.name;
    }
    public List<String> getTopics(){
        return this.topics;
    }
    public Department setName(String name){
        this.name = name;
        return this;
    }
    public Department setTopics(List<String> topics){
        this.topics = topics;
        return this;
    }
    public Department addTopic(String topic){
        if(topics == null){
            topics = new ArrayList<String>();
        }
        topics.add(topic);
        return this;
    }
}
