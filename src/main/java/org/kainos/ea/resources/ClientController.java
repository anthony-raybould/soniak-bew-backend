package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ClientService;
import org.kainos.ea.auth.AccessValidator;
import org.kainos.ea.cli.Role;
import org.kainos.ea.client.AccessValidationException;
import org.kainos.ea.client.FailedToGetClientException;
import org.kainos.ea.client.ForbiddenAccessException;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.ProjectDao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Client API")
@Path("/api")
public class ClientController {

    private ClientService clientService = new ClientService(new ClientDao(new DatabaseConnector()));

    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClientSalesEmployees(@QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.SALES);

            return Response.ok(clientService.getAllClientSalesEmployees()).build();
        } catch (FailedToGetClientException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/client/highestvalue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientWithHighestValueProjects(@QueryParam("token") String token) {
        try {
            AccessValidator.validateAccess(token, Role.SALES);

            return Response.ok(clientService.getClientWithHighestValueProjects()).build();
        } catch (FailedToGetClientException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (AccessValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (ForbiddenAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}