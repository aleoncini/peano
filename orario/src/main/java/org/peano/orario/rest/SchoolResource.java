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
import org.peano.orario.domain.School;

@Path("/schools")
@Consumes("application/json")
@Produces("application/json")
public class SchoolResource {

    @GET
    public List<School> list() {
        return School.listAll();
    }

    @GET
    @Path("/{id}")
    public School get(@PathParam("id") String id) {
        return School.findById(new ObjectId(id));
    }

    @POST
    public School create(School school) {
        school.persist();
        return school;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, School school){
        school.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        School school = School.findById(new ObjectId(id));
        school.delete();
    }

    @GET
    @Path("/admin/{admin}")
    public List<School> searchByAdmin(@PathParam("admin") String admin) {
        return School.findByAdmin(admin);
    }

    @GET
    @Path("/count")
    public Long count() {
        return School.count();
    }

}
