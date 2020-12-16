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
import org.peano.orario.domain.StudentGroups;
import org.peano.orario.domain.Teacher;

@Path("/groups")
@Consumes("application/json")
@Produces("application/json")
public class GroupResource {

    @GET
    public List<StudentGroups> list() {
        return StudentGroups.listAll();
    }

    @GET
    @Path("/{id}")
    public StudentGroups get(@PathParam("id") String id) {
        return StudentGroups.findById(new ObjectId(id));
    }

    @POST
    public StudentGroups create(StudentGroups group) {
        group.persist();
        return group;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, StudentGroups group) {
        StudentGroups sg = StudentGroups.findById(new ObjectId(id));
        sg.setGroups(group.getGroups());
        sg.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        StudentGroups sg = StudentGroups.findById(new ObjectId(id));
        sg.delete();
    }

    @GET
    @Path("/school/{schoolId}")
    public StudentGroups getBySchool(@PathParam("schoolId") String schoolId) {
        return StudentGroups.findBySchool(schoolId);
    }

}
