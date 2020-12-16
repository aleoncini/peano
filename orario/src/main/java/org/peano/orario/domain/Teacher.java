package org.peano.orario.domain;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="teachers")
public class Teacher extends PanacheMongoEntity {
    public String name;
    public String subject;
    public String schoolId;

    public static Teacher findByName(String name){
        return find("name", name).firstResult();
    }
    public static List<Teacher> findBySubject(String subject){
        return list("subject", subject);
    }
    public static List<Teacher> findBySchool(String schoolId){
        return list("schoolId", schoolId);
    }

    public String getName(){
        return this.name;
    }
    public String getSubject(){
        return this.subject;
    }
    public String getSchoolId(){
        return this.schoolId;
    }
    public Teacher setName(String name){
        this.name = name;
        return this;
    }
    public Teacher setSubject(String subject){
        this.subject = subject;
        return this;
    }
    public Teacher setSchoolId(String id){
        this.schoolId = id;
        return this;
    }
}
