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
import org.peano.orario.domain.Institute;

@Path("/institutes")
@Consumes("application/json")
@Produces("application/json")
public class InstituteResource {

    @GET
    public List<Institute> list() {
        return Institute.listAll();
    }

    @GET
    @Path("/{id}")
    public Institute get(@PathParam("id") String id) {
        return Institute.findById(new ObjectId(id));
    }

    @POST
    public Institute create(Institute institute) {
        institute.persist();
        return institute;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Institute institute) {
        institute.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Institute institute = Institute.findById(new ObjectId(id));
        institute.delete();
    }

    @GET
    @Path("/search/{name}")
    public Institute search(@PathParam("name") String name) {
        return Institute.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Institute.count();
    }

}
