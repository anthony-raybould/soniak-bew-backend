package org.kainos.ea.resources;


import io.swagger.annotations.Api;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesEmployeeDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Sales Employee API")
@Path("/api")
public class SalesEmployeeController {
    SalesEmployeeService salesEmployeeService = new SalesEmployeeService(
            new SalesEmployeeDao(),
            new SalesEmployeeValidator()
    );

    @GET
    @Path("/salesemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() {
        try {
            return Response.ok(salesEmployeeService.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("id") int id) {
        try {
            return Response.ok(salesEmployeeService.getSalesEmployeeById(id)).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/salesemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployeeRequest salesEmployeeRequest) {
        try {
            return Response.ok(salesEmployeeService.createSalesEmployee(salesEmployeeRequest)).build();
        } catch (FailedToCreateSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployee(@PathParam("id") int id, SalesEmployeeRequest request) {
        try {
            salesEmployeeService.updateSalesEmployee(id, request);

            return Response.ok().build();
        } catch (FailedToUpdateSalesEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException | SalesEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployeeId(@PathParam("id") int id) {
        try {
            salesEmployeeService.deleteSalesEmployee(id);

            return Response.ok().build();
        } catch (FailedToDeleteSalesEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}