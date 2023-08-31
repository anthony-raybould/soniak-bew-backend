package org.kainos.ea.api;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.cli.DeliveryEmployeeProject;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeProjectValidator;
import org.kainos.ea.db.DeliveryEmployeesProjectsDao;

public class DeliveryEmployeesProjectsService {

    private final DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao;
    private final DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator;

    public DeliveryEmployeesProjectsService(DeliveryEmployeesProjectsDao deliveryEmployeesProjectsDao,
                                            DeliveryEmployeeProjectValidator deliveryEmployeeProjectValidator) {
        this.deliveryEmployeesProjectsDao = deliveryEmployeesProjectsDao;
        this.deliveryEmployeeProjectValidator = deliveryEmployeeProjectValidator;
    }

    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException,
            InvalidAssignDeliveryEmployeesRequestException, FailedToGetProjectException, ProjectDoesNotExistException,
            FailedToGetDeliveryEmployeeProjectException, FailedToGetDeliveryEmployeesException, DeliveryEmployeeDoesNotExistException, DeliveryEmployeeAlreadyAssignedToProjectException {

        String invalidCause = deliveryEmployeeProjectValidator.validateAssignments(request);
        if (invalidCause != null) {
            throw new InvalidAssignDeliveryEmployeesRequestException(invalidCause);
        }

        deliveryEmployeesProjectsDao.assignDeliveryEmployees(request);
    }

    public void removeDeliveryEmployeeProject(int deliveryEmployeeId, int projectId) throws FailedToGetDeliveryEmployeeProjectException, DeliveryEmployeeProjectAssignmentDoesNotExistException, FailedToRemoveDeliveryEmployeeProjectException {

        DeliveryEmployeeProject assignment = deliveryEmployeesProjectsDao.getDeliveryEmployeeProjectById(deliveryEmployeeId, projectId);

        if (assignment == null) throw new DeliveryEmployeeProjectAssignmentDoesNotExistException();

        deliveryEmployeesProjectsDao.removeDeliveryEmployeeProject(deliveryEmployeeId, projectId);
    }
}
