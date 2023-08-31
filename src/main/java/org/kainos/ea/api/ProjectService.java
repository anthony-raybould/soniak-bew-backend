package org.kainos.ea.api;

import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private final ProjectDao projectDao;
    private final ProjectValidator projectValidator;

    public ProjectService(ProjectDao projectDao, ProjectValidator projectValidator) {
        this.projectDao = projectDao;
        this.projectValidator = projectValidator;
    }

    public List<Project> getProjects() throws FailedToGetProjectException {
        try {
            return projectDao.getProjects();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProjectException();
        }
    }

    public Project getProjectById(int id) throws FailedToGetProjectException, ProjectDoesNotExistException {

        Project project = projectDao.getProjectById(id);

        if (project == null) throw new ProjectDoesNotExistException();

        return project;
    }

    public int createProject(ProjectRequest request) throws FailedToCreateProjectException, InvalidProjectException {
        try {
            String validation = projectValidator.isValidProject(request);

            if (validation != null) {
                throw new InvalidProjectException(validation);
            }

            int id = projectDao.createProject(request);

            if (id == -1) {
                throw new FailedToCreateProjectException();
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToCreateProjectException();
        }
    }

    public void updateProject(int id, ProjectRequest request) throws FailedToUpdateProjectException, ProjectDoesNotExistException, InvalidProjectException {
        try {
            String validation = projectValidator.isValidProject(request);

            if (validation != null) {
                throw new InvalidProjectException(validation);
            }

            if (projectDao.getProjectById(id) == null) {
                throw new ProjectDoesNotExistException();
            }

             projectDao.updateProject(id, request);
        } catch (SQLException | FailedToGetProjectException e) {
            e.printStackTrace();

            throw new FailedToUpdateProjectException();
        }
    }
}
