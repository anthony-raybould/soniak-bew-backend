package org.kainos.ea.db;

import org.kainos.ea.cli.AssignDeliveryEmployeesRequest;
import org.kainos.ea.cli.DeliveryEmployeeProject;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeesException;
import org.kainos.ea.client.FailedToGetDeliveryEmployeeProjectException;
import org.kainos.ea.client.FailedToRemoveDeliveryEmployeeProjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryEmployeesProjectsDao {

    public void assignDeliveryEmployees(AssignDeliveryEmployeesRequest request) throws FailedToAssignDeliveryEmployeesException {

        try (Connection conn = DatabaseConnector.getConnection()) {

            conn.setAutoCommit(false);

            String insertString = "INSERT INTO DeliveryEmployees_Projects VALUES (?, ?)";

            for (int employeeId : request.getEmployeeIds()) {
                PreparedStatement stmt = conn.prepareStatement(insertString);
                stmt.setInt(1, employeeId);
                stmt.setInt(2, request.getProjectId());
                stmt.execute();
            }

            conn.commit();


        } catch (SQLException e) {
            throw new FailedToAssignDeliveryEmployeesException();
        }
    }

    public DeliveryEmployeeProject getDeliveryEmployeeProjectById(int deliveryEmployeeId, int projectId) throws FailedToGetDeliveryEmployeeProjectException {

        try (Connection conn = DatabaseConnector.getConnection()) {

            String queryString = "SELECT EmployeeID, ProjectID FROM DeliveryEmployees_Projects WHERE EmployeeID = ? AND ProjectID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryString);
            stmt.setInt(1, deliveryEmployeeId);
            stmt.setInt(2, projectId);

            ResultSet results = stmt.executeQuery();
            if (!results.next()) return null;

            return new DeliveryEmployeeProject(
                    results.getInt("EmployeeID"),
                    results.getInt("ProjectID")
            );


        } catch (SQLException e) {
            throw new FailedToGetDeliveryEmployeeProjectException();
        }
    }

    public void removeDeliveryEmployeeProject(int deliveryEmployeeId, int projectId) throws FailedToRemoveDeliveryEmployeeProjectException {

        try (Connection conn = DatabaseConnector.getConnection()) {

            String queryString = "DELETE FROM DeliveryEmployees_Projects WHERE EmployeeID = ? AND ProjectID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryString);
            stmt.setInt(1, deliveryEmployeeId);
            stmt.setInt(2, projectId);

            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToRemoveDeliveryEmployeeProjectException();
        }
    }
}
