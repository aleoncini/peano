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

import org.bson.types.ObjectId;
import org.peano.orario.domain.Teacher;

@Path("/teachers")
@Consumes("application/json")
@Produces("application/json")
public class TeacherResource {

    @GET
    public List<Teacher> list() {
        return Teacher.listAll();
    }

    @GET
    @Path("/{id}")
    public Teacher get(@PathParam("id") String id) {
        return Teacher.findById(new ObjectId(id));
    }

    @POST
    public Teacher create(Teacher teacher) {
        teacher.persist();
        return teacher;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Teacher teacher) {
        teacher.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Teacher teacher = Teacher.findById(new ObjectId(id));
        teacher.delete();
    }

    @GET
    @Path("/search/{name}")
    public Teacher search(@PathParam("name") String name) {
        return Teacher.findByName(name);
    }

    @GET
    @Path("/school/{schoolId}")
    public List<Teacher> searchBySchool(@PathParam("schoolId") String schoolId) {
        return Teacher.findBySchool(schoolId);
    }

    @GET
    @Path("/subject/{subject}")
    public List<Teacher> searchBySubject(@PathParam("subject") String subject) {
        return Teacher.findBySubject(subject);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Teacher.count();
    }

}
