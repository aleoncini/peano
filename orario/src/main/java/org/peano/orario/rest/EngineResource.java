package org.peano.orario.rest;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.peano.orario.domain.TimeTable;
import org.peano.orario.domain.TimeTableBuilder;
import org.peano.orario.service.JobConsumer;

@Path("/engine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EngineResource {

    @Inject
    SolverManager<TimeTable, UUID> solverManager;

    @GET
    @Path("/{id}")
    public SolverStatus getTimeTable(@PathParam("id") UUID id) {
        //return jobManager.get(id).getSolverStatus();
        return solverManager.getSolverStatus(id);
    }
    
    @POST
    @Path("/{id}")
    public Response solve(@PathParam("id") UUID id) {
        JobConsumer consumer = new JobConsumer().setId(id);

        solverManager.solve(id,
                this::findById,
                consumer::save);

        return Response.accepted(id).build();
    }

    protected TimeTable findById(UUID id) {
        return new TimeTableBuilder().build();
    }
}
