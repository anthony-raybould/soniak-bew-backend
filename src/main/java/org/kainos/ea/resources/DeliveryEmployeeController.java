package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.auth.AccessValidator;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Role;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Delivery Employee API")
@Path("/api")
public class DeliveryEmployeeController {

    private final DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService(
            new DeliveryEmployeeDao(),
            new DeliveryEmployeeValidator()
    );

    @GET
    @Path("/deliveryemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployee(@QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(deliveryEmployeeService.getDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeesException e) {
            return Response.serverError().build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/deliveryemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeById(id)).build();
        } catch (FailedToGetDeliveryEmployeesException e) {
            return Response.serverError().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/deliveryemployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest request, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(request)).build();
        } catch (FailedToCreateDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (InvalidDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/deliveryemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeliveryEmployee(@PathParam("id") int id, DeliveryEmployeeRequest request, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            deliveryEmployeeService.updateDeliveryEmployee(id, request);

            return Response.ok().build();
        } catch (FailedToUpdateDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (InvalidDeliveryEmployeeException | DeliveryEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deliveryemployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployeeId(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.HR);

            deliveryEmployeeService.deleteDeliveryEmployee(id);

            return Response.ok().build();
        } catch (FailedToDeleteDeliveryEmployeeException e) {
            e.printStackTrace();

            return Response.serverError().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            e.printStackTrace();

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}
