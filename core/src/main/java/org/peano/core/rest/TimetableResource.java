package org.peano.core.rest;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.peano.core.model.Timetable;

import io.quarkus.oidc.IdToken;

@Path("/timetables")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimetableResource {
    
    private static final Logger logger = LoggerFactory.getLogger("org.peano.orario");

    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    public List<Timetable> getAll() {
        return Timetable.getTimetablesByOwner(getOwnerFromJWT());
    }

    @GET
    @Path("/{id}")
    public Timetable get(@PathParam("id") String id) {
        Timetable timetable = Timetable.findById(new ObjectId(id));
        if((timetable == null) || (! timetable.owner.equals(getOwnerFromJWT()))) {
            throw new NotFoundException();
        }
        return timetable;
    }

    @POST
    public Timetable create(Timetable timetable) {
        timetable.owner = getOwnerFromJWT();
        timetable.persist();
        return timetable;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Timetable timetable = Timetable.findById(new ObjectId(id));
        if(timetable == null) {
            throw new NotFoundException();
        }
        if(timetable.owner.equals(getOwnerFromJWT())){
            logger.info("[TimetableResource] deleting timetable, requester is also owner, deleting: " + id);
            timetable.delete();
        }
    }

    private String getOwnerFromJWT(){
        String owner = "undefined";
        Object ownerObject = idToken.getClaim("email");
        if (ownerObject != null) {
            owner = ownerObject.toString();
        }    
        return owner;
    }

}
