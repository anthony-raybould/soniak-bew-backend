package org.kainos.ea.resources;


import io.swagger.annotations.Api;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.auth.AccessValidator;
import org.kainos.ea.cli.Role;
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
    public Response getAllSalesEmployees(@QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(salesEmployeeService.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(salesEmployeeService.getSalesEmployeeById(id)).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/salesemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployeeRequest salesEmployeeRequest, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(salesEmployeeService.createSalesEmployee(salesEmployeeRequest)).build();
        } catch (FailedToCreateSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployee(@PathParam("id") int id, SalesEmployeeRequest request, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            salesEmployeeService.updateSalesEmployee(id, request);

            return Response.ok().build();
        } catch (FailedToUpdateSalesEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException | SalesEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/salesemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployeeId(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            salesEmployeeService.deleteSalesEmployee(id);

            return Response.ok().build();
        } catch (FailedToDeleteSalesEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}