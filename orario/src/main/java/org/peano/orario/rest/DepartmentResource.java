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
import org.peano.orario.domain.Department;
import org.peano.orario.domain.Teacher;

@Path("/departments")
@Consumes("application/json")
@Produces("application/json")
public class DepartmentResource {

    @GET
    public List<Department> list() {
        return Department.listAll();
    }

    @GET
    @Path("/{id}")
    public Department get(@PathParam("id") String id) {
        return Department.findById(new ObjectId(id));
    }

    @POST
    public Department create(Department department) {
        department.persist();
        return department;
    }

    @PUT
    public void update(Department department) {
        department.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Department department = Department.findById(new ObjectId(id));
        department.delete();
    }

    @GET
    @Path("/find/{name}")
    public Department find(@PathParam("name") String name) {
        return Department.findByName(name);
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
