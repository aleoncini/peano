package org.peano.orario.rest;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.bson.types.ObjectId;
import org.peano.orario.domain.User;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    @GET
    public List<User> list() {
        return User.listAll();
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") String id) {
        return User.findById(new ObjectId(id));
    }

    @GET
    @Path("/code/{code}")
    public User getByCode(@PathParam("code") String code) {
        return User.findByCode(code);
    }

    @POST
    public User create(User user) {
        if(user.getCode() == null){
            user.setCode(UUID.randomUUID().toString());
        }
        user.persist();
        return user;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, User user) {
        user.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        User user = User.findById(new ObjectId(id));
        user.delete();
    }
}