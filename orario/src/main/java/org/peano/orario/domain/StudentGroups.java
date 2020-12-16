package org.peano.orario.domain;

import java.util.Set;
import java.util.TreeSet;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class StudentGroups extends PanacheMongoEntity {

    private String schoolId;
    private Set<String> groups = new TreeSet<String>();

    public static StudentGroups findBySchool(String schoolId){
        return find("schoolId", schoolId).firstResult();
    }

    public StudentGroups addGroup(String group) {
        groups.add(group);
        return this;
    }

    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }

    public String getSchoolId(){
        return this.schoolId;
    }
    
    public void setGroups(Set<String> list){
        this.groups = list;
    }

    public Set<String> getGroups(){
        return groups;
    }

}
