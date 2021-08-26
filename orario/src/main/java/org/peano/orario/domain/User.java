package org.peano.orario.domain;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity {

    private String name;
    private String code;
    private String role;

    public static User findByCode(String code){
        return find("code", code).firstResult();
    }

    public User setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }
    public User setCode(String code){
        this.code = code;
        return this;
    }
    public String getCode(){
        return this.code;
    }
    public User setRole(String role){
        this.role = role;
        return this;
    }
    public String getRole(){
        return this.role;
    }
}
