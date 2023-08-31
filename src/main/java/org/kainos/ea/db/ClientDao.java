package org.kainos.ea.db;

import org.kainos.ea.cli.ClientSalesEmployee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private DatabaseConnector databaseConnector;

    public ClientDao(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public boolean validateClientId(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        // Valid request
        ResultSet rs = st.executeQuery("SELECT COUNT(ClientID) FROM Clients WHERE ClientID=" + id);

        while (rs.next()) {
            if (rs.getInt("COUNT(ClientID)") > 0) {
                return true;
            }
        }

        return false;
    }

    public List<ClientSalesEmployee> getAllClientSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Clients.Name AS \"Client Name\", " +
                "Employees.Name AS \"Sales Employee Name\", " +
                "GROUP_CONCAT(Projects.Name) AS Projects FROM Clients " +
                "JOIN SalesEmployees USING (EmployeeID) " +
                "LEFT JOIN Employees USING (EmployeeID) " +
                "JOIN Projects ON Clients.ClientID = Projects.ClientID " +
                "GROUP BY Clients.ClientID;");

        List<ClientSalesEmployee> clientSalesEmployeeList = new ArrayList<>();

        while (rs.next()) {
            ClientSalesEmployee clientSalesEmployee = new ClientSalesEmployee(
                    rs.getString("Client Name"),
                    rs.getString("Sales Employee Name"),
                    rs.getString("Projects")
            );

            clientSalesEmployeeList.add(clientSalesEmployee);
        }

        return clientSalesEmployeeList;
    }

    public String getClientWithHighestValueProjects() throws SQLException {

        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Clients.Name AS Name FROM Projects " +
                        "JOIN Clients on Projects.ClientID = Clients.ClientID " +
                        "GROUP BY Projects.ClientID " +
                        "ORDER BY SUM(Value) DESC LIMIT 1;");

        String clientName = null;

        while (rs.next()) {
            clientName = rs.getString("Name");
        }

        return clientName;
    }
}
