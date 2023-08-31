package org.kainos.ea.api;


import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private final SalesEmployeeValidator salesEmployeeValidator;
    private final SalesEmployeeDao salesEmployeeDao;

    public SalesEmployeeService(SalesEmployeeDao salesEmployeeDao, SalesEmployeeValidator salesEmployeeValidator) {
        this.salesEmployeeDao = salesEmployeeDao;
        this.salesEmployeeValidator = salesEmployeeValidator;
    }

    public List<SalesEmployee> getAllSalesEmployees() throws FailedToGetSalesEmployeesException {
        try {
            return salesEmployeeDao.getAllSalesEmployees();
        } catch (SQLException e) {
            throw new FailedToGetSalesEmployeesException();
        }
    }

    public SalesEmployee getSalesEmployeeById(int id) throws FailedToGetSalesEmployeeException, SalesEmployeeDoesNotExistException {
        try {
            SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);

            if (salesEmployee == null) {
                throw new SalesEmployeeDoesNotExistException(id);
            }

            return salesEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetSalesEmployeeException();
        }
    }

    public int createSalesEmployee(SalesEmployeeRequest salesEmployeeRequest) throws FailedToCreateSalesEmployeeException, InvalidSalesEmployeeException {
        try {
            String validation = salesEmployeeValidator.isValidSalesEmployee(salesEmployeeRequest);

                if (validation != null) {
                    throw new InvalidSalesEmployeeException(validation);
                }
                int id = salesEmployeeDao.createSalesEmployee(salesEmployeeRequest);

                if (id == -1) {
                    throw new FailedToCreateSalesEmployeeException();
                }
                return id;

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateSalesEmployeeException();
        }
    }

    public void updateSalesEmployee(int id, SalesEmployeeRequest request) throws InvalidSalesEmployeeException, FailedToUpdateSalesEmployeeException, SalesEmployeeDoesNotExistException {
        try {
            String validation = salesEmployeeValidator.isValidSalesEmployee(request);

            if (validation != null) {
                throw new InvalidSalesEmployeeException(validation);
            }

            SalesEmployee salesEmployeeToUpdate = salesEmployeeDao.getSalesEmployeeById(id);

            if (salesEmployeeToUpdate == null) {
                throw new SalesEmployeeDoesNotExistException(id);
            }

            salesEmployeeDao.updateSalesEmployee(id, request);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToUpdateSalesEmployeeException();
        }
    }

    public void deleteSalesEmployee(int id) throws FailedToDeleteSalesEmployeeException, SalesEmployeeDoesNotExistException {
        try {
            SalesEmployee salesEmployeeToDelete = salesEmployeeDao.getSalesEmployeeById(id);

            if (salesEmployeeToDelete == null) {
                throw new SalesEmployeeDoesNotExistException(id);
            }

            salesEmployeeDao.deleteSalesEmployee(id);
        } catch (SQLException e) {
            e.printStackTrace();

            throw new FailedToDeleteSalesEmployeeException();
        }
    }
}
