package org.peano.orario.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@PlanningSolution
@MongoEntity(collection = "timetables")
public class TimeTable extends PanacheMongoEntity {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Timeslot> timeslotList;
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<Room> roomList;
    @PlanningEntityCollectionProperty
    private List<Lesson> lessonList;

    private String schoolId;
    private String description;

    @PlanningScore
    private HardSoftScore score;

    // Ignored by OptaPlanner, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;

    public static List<TimeTable> findBySchool(String schoolId){
        return list("schoolId", schoolId);
    }

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public TimeTable setTimeslotList(List<Timeslot> timeslotList) {
        this.timeslotList = timeslotList;
        return this;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public TimeTable setRoomList(List<Room> roomList) {
        this.roomList = roomList;
        return this;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public TimeTable setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
        return this;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public String getSchoolId() {
        return this.schoolId;
    }

    public TimeTable setSchoolId(String schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public TimeTable setDescription(String description) {
        this.description = description;
        return this;
    }

}
