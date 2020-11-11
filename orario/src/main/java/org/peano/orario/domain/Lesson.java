package org.peano.orario.domain;

import java.util.List;
import java.util.Map;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@PlanningEntity
@MongoEntity(collection="lessons")
public class Lesson extends PanacheMongoEntity {

    @PlanningId
    private int instance;

    private String subject;
    private String teacher;
    private String studentGroup;
    private String timetableId;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public Lesson() {
    }

    public Lesson(String subject, String teacher, String studentGroup) {
        this.subject = subject.trim();
        this.teacher = teacher.trim();
        this.studentGroup = studentGroup.trim();
    }

    public static List<Lesson> find(){
        return School.listAll();
    }

    public static List<Lesson> findByTeacher(String timetableId, String teacher){
        return list("timetableId = ?1 and teacher = ?2", timetableId, teacher);
    }

    public static List<Lesson> findByRoom(String timetableId, String roomName){
        return list("timetableId = ?1 and room.name = ?2", timetableId, roomName);
    }

    public static List<Lesson> findByTimetable(String timetableId){
        return list("timetableId", timetableId);
    }

    public static void deleteTimetable(String timetableId){
        delete("timetableId", timetableId);
    }

    public Lesson setInstance(int instance) {
        this.instance = instance;
        return this;
    }

    public int getInstance() {
        return instance;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher){
        this.teacher = teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup){
        this.studentGroup = studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getTimetableId() {
        return this.timetableId;
    }

    public Lesson setTimetableId(String id) {
        this.timetableId = id;
        return this;
    }

    @Override
    public String toString() {
        return subject + " - " + teacher + " - " + timeslot + " - " + room;
    }

}
