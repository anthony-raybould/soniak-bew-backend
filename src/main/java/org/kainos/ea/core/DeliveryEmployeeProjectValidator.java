package org.kainos.ea.core;

import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.DeliveryEmployeeDao;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;

public class DeliveryEmployeeProjectValidator {

    private ProjectService projectService;
    private DeliveryEmployeeDao deliveryEmployeeDao;
    private DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao;

    public DeliveryEmployeeProjectValidator(ProjectService projectService, DeliveryEmployeeDao deliveryEmployeeDao, DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao) {
        this.projectService = projectService;
        this.deliveryEmployeeDao = deliveryEmployeeDao;
        this.deliveryEmployeesProjectsDao = deliveryEmployeesProjectsDao;
    }

    public String validateAssignments(AssignDeliveryEmployeesRequest request) throws ProjectDoesNotExistException,
            FailedToGetProjectException, FailedToGetDeliveryEmployeeProjectException,
            FailedToGetDeliveryEmployeesException, DeliveryEmployeeDoesNotExistException, DeliveryEmployeeAlreadyAssignedToProjectException {

        // Will throw exception if project does not exist.
        projectService.getProjectById(request.getProjectId());


        for (int deliveryEmployeeId : request.getEmployeeIds()) {
            try {
                if (deliveryEmployeeDao.getDeliveryEmployeeById(deliveryEmployeeId) == null) {
                    throw new DeliveryEmployeeDoesNotExistException(deliveryEmployeeId);
                }
            } catch (SQLException e) {
                throw new FailedToGetDeliveryEmployeesException();
            }

            if (deliveryEmployeesProjectsDao.getDeliveryEmployeeProjectById(deliveryEmployeeId,
                    request.getProjectId()) != null) {
                throw new DeliveryEmployeeAlreadyAssignedToProjectException();
            }
        }



        return null;
    }
}
