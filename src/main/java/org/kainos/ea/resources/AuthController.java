package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.LoginRequest;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Authentication API")
@Path("/api")
public class AuthController {
    private final AuthService authService = new AuthService();

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest login) {
        try {
            return Response.ok(authService.login(login)).build();
        } catch (FailedToLoginException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        } catch (FailedToGenerateTokenException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest request) {
        try {
            authService.register(request);

            return Response.ok().build();
        } catch (FailedToRegisterException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
