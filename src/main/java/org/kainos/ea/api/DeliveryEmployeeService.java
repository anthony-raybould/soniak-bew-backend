package org.kainos.ea.api;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.DeliveryEmployeeValidator;
import org.kainos.ea.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {

    private final DeliveryEmployeeDao deliveryEmployeeDao;
    private final DeliveryEmployeeValidator deliveryEmployeeValidator;

    public DeliveryEmployeeService(DeliveryEmployeeDao deliveryEmployeeDao, DeliveryEmployeeValidator deliveryEmployeeValidator) {
        this.deliveryEmployeeDao = deliveryEmployeeDao;
        this.deliveryEmployeeValidator = deliveryEmployeeValidator;
    }

    public List<DeliveryEmployee> getDeliveryEmployees() throws FailedToGetDeliveryEmployeesException {
        try {
            return deliveryEmployeeDao.getDeliveryEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeesException();
        }
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetDeliveryEmployeesException, DeliveryEmployeeDoesNotExistException {
        try {
            DeliveryEmployee deliveryEmployee = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployee == null) {
                throw new DeliveryEmployeeDoesNotExistException(id);
            }

            return deliveryEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeesException();
        }
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest request) throws FailedToCreateDeliveryEmployeeException, InvalidDeliveryEmployeeException {
        try {
            String validation = deliveryEmployeeValidator.isValidDeliveryEmployee(request);

            if (validation != null) {
                throw new InvalidDeliveryEmployeeException(validation);
            }

            int id = deliveryEmployeeDao.createDeliveryEmployee(request);

            if (id == -1) {
                throw new FailedToCreateDeliveryEmployeeException();
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToCreateDeliveryEmployeeException();
        }
    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest request) throws FailedToUpdateDeliveryEmployeeException, InvalidDeliveryEmployeeException, DeliveryEmployeeDoesNotExistException {
        try {
            String validation = deliveryEmployeeValidator.isValidDeliveryEmployee(request);

            if (validation != null) {
                throw new InvalidDeliveryEmployeeException(validation);
            }

            DeliveryEmployee deliveryEmployeeToUpdate = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployeeToUpdate == null) {
                throw new DeliveryEmployeeDoesNotExistException(id);
            }

            deliveryEmployeeDao.updateDeliveryEmployee(id, request);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToUpdateDeliveryEmployeeException();
        }
    }

    public void deleteDeliveryEmployee(int id) throws FailedToDeleteDeliveryEmployeeException, DeliveryEmployeeDoesNotExistException {
        try {
            DeliveryEmployee deliveryEmployeeToDelete = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (deliveryEmployeeToDelete == null) {
                throw new DeliveryEmployeeDoesNotExistException(id);
            }

            deliveryEmployeeDao.deleteDeliveryEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToDeleteDeliveryEmployeeException();
        }
    }

}
