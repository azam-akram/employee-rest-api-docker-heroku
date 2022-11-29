package com.javaeelab.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javaeelab.webservices.rest.model.EmployeeDTO;
import com.javaeelab.webservices.rest.model.EmployeesDTO;
import org.apache.commons.lang.StringUtils;

/**
 * @author azam.akram
 *
 * Employee information API
 * This API supports HTTP GET, POST and DELETE methods
 * It consumes and produces both XML and JSON.
 *
 */

@Path("employees")
public class EmployeeInfoApi {

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEmployees() {
        return Response.ok(getEmpInfoService().getAllEmployees()).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeById(@PathParam("id") String id) {
        if (StringUtils.isEmpty(id) && Integer.parseInt(id) < 0) {
            return Response.status(Status.BAD_REQUEST).entity("Id must be greater than 0").build();
        }
        EmployeesDTO employees = getEmpInfoService().getEmployeeById(Integer.parseInt(id));

        if (employees != null) {
            return Response.ok().entity(employees).build();
        }
        return Response.ok().entity("No record found").build();
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEmployee(final EmployeesDTO employeesDTO) {
        if (employeesDTO == null) {
            return Response.status(Status.BAD_REQUEST).entity("Empty data to add employee").build();
        }
        getEmpInfoService().addEmployee(employeesDTO);
        return Response.ok().build();
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEmployee(final EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return Response.status(Status.BAD_REQUEST).entity("Empty data to update employee").build();
        }
        if (employeeDTO.getId() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Employee id must not be null").build();
        }
        getEmpInfoService().updateEmployee(employeeDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") String id) {
        getEmpInfoService().deleteEmployee(Integer.parseInt(id));
        return Response.ok().build();
    }

    private EmployeeInfoService getEmpInfoService() {
        return EmployeeInfoServiceFactory.getEmployeeInfoService();
    }
}
