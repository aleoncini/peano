package org.peano.orario.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.peano.orario.domain.Lesson;
import org.peano.orario.domain.TimeTable;

@Path("/timetables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeTableResource {

    @GET
    public List<TimeTable> list() {
        return TimeTable.listAll();
    }

    @GET
    @Path("/{id}")
    public TimeTable get(@PathParam("id") String id) {
        return TimeTable.findById(new ObjectId(id));
    }

    @POST
    public TimeTable create(TimeTable timeTable) {
        timeTable.persist();
        return timeTable;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, TimeTable timeTable) {
        timeTable.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        TimeTable timeTable = TimeTable.findById(new ObjectId(id));
        timeTable.delete();
    }

    @GET
    @Path("/school/{schoolId}")
    public List<TimeTable> searchBySchool(@PathParam("schoolId") String schoolId) {
        return TimeTable.findBySchool(schoolId);
    }

    /*
    @GET
    @Path("/{id}")
    public List<Lesson> get(@PathParam("id") String id, @QueryParam("t") String teacher, @QueryParam("r") String room, @QueryParam("s") String studentGroup) {
        if((teacher != null) && (teacher.length() > 0)){
            return Lesson.findByTeacher(id, teacher);
        }
        if((studentGroup != null) && (studentGroup.length() > 0)){
            return Lesson.findByStudentGroup(id, studentGroup);
        }
        if((room != null) && (room.length() > 0)){
            return Lesson.findByRoom(id, room);
        }
        return Lesson.findByTimetable(id);
    }
    */

    @GET
    @Path("/check/{id}")
    public Response check(@PathParam("id") String id) {
        if(Lesson.findByTimetable(id).isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public void purge(@PathParam("id") String id) {
        Lesson.deleteTimetable(id);
    }

}
