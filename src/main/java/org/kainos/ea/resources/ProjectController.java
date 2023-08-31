package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.ProjectRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.ProjectDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Project API")
@Path("/api")
public class ProjectController {

    private final ProjectService projectService = new ProjectService(
            new ProjectDao(),
            new ProjectValidator()
    );

    @GET
    @Path("/project")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjects() {
        try {
            return Response.ok(projectService.getProjects()).build();
        } catch (FailedToGetProjectException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/project")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectRequest request) {
        try {
            return Response.ok(projectService.createProject(request)).build();
        } catch (FailedToCreateProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidProjectException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(@PathParam("id") int id, ProjectRequest request) {
        try {
            projectService.updateProject(id, request);

            return Response.ok().build();
        } catch (FailedToUpdateProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (ProjectDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (InvalidProjectException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
