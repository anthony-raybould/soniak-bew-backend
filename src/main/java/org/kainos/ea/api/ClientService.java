package org.kainos.ea.api;

import org.kainos.ea.cli.ClientSalesEmployee;
import org.kainos.ea.client.FailedToGetClientException;
import org.kainos.ea.db.ClientDao;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<ClientSalesEmployee> getAllClientSalesEmployees() throws FailedToGetClientException {

        List<ClientSalesEmployee> clientSalesEmployeeList = null;

        try {
            clientSalesEmployeeList = clientDao.getAllClientSalesEmployees();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientException();
        }

        return clientSalesEmployeeList;
    }

    public String getClientWithHighestValueProjects() throws FailedToGetClientException {

        String clientName = null;

        try {
            clientName = clientDao.getClientWithHighestValueProjects();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetClientException();
        }

        return clientName;
    }
}
