package org.peano.orario.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.peano.orario.domain.Lesson;

@Path("/orario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeTableResource {

    @GET
    @Path("/{id}")
    public List<Lesson> get(@PathParam("id") String id, @QueryParam("t") String teacher, @QueryParam("r") String room) {
        if((teacher != null) && (teacher.length() > 0)){
            return Lesson.findByTeacher(id, teacher);
        }
        if((room != null) && (room.length() > 0)){
            return Lesson.findByRoom(id, room);
        }
        return Lesson.findByTimetable(id);
    }

    @GET
    @Path("/check/{id}")
    public Response check(@PathParam("id") String id) {
        if(Lesson.findByTimetable(id).size() > 0){
            return Response.ResponseBuilder.
        }
        return Lesson.findByTimetable(id);
    }

    @DELETE
    @Path("/{id}")
    public void purge(@PathParam("id") String id) {
        Lesson.deleteTimetable(id);
    }

}
