package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectRequest;
import org.kainos.ea.client.FailedToGetProjectException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public List<Project> getProjects() throws SQLException {

        String queryString = "SELECT ProjectID, Name, Value, TechLead, ClientID, StartDate, EndDate FROM Projects";

        try (Connection conn = DatabaseConnector.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(queryString);

            ResultSet results = stmt.executeQuery();

            List<Project> projects = new ArrayList<>();

            while (results.next()) {
                projects.add(new Project(
                        results.getInt("ProjectID"),
                        results.getString("Name"),
                        results.getDouble("Value"),
                        results.getInt("TechLead"),
                        results.getInt("ClientID"),
                        results.getDate("StartDate"),
                        results.getDate("EndDate")
                ));
            }

            return projects;
        }
    }

    public Project getProjectById(int id) throws FailedToGetProjectException {

        String queryString = "SELECT ProjectID, Name, Value, TechLead, ClientID, StartDate, EndDate FROM Projects" +
                " WHERE ProjectID = ?";

        try (Connection conn = DatabaseConnector.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(queryString);
            stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();

            if (!results.next()) return null;

            return new Project(
                    results.getInt("ProjectID"),
                    results.getString("Name"),
                    results.getDouble("Value"),
                    results.getInt("TechLead"),
                    results.getInt("ClientID"),
                    results.getDate("StartDate"),
                    results.getDate("EndDate")
            );

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectException();
        }
    }

    public int createProject(ProjectRequest request) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String queryString = "INSERT INTO Projects (Name, Value, TechLead, ClientID, StartDate, EndDate) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement insert = c.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

        insert.setString(1, request.getName());
        insert.setDouble(2, request.getValue());
        insert.setInt(3, request.getTechLead());
        insert.setInt(4, request.getClientId());
        insert.setDate(5, request.getStartDate());
        insert.setDate(6, request.getEndDate());

        insert.executeUpdate();

        ResultSet rs = insert.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateProject(int id, ProjectRequest request) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String queryString = "UPDATE Projects SET " +
                "Name = ?," +
                "Value = ?," +
                "TechLead = ?," +
                "ClientID = ?," +
                "StartDate = ?," +
                "EndDate = ?" +
                "WHERE ProjectID = ?";

        PreparedStatement update = c.prepareStatement(queryString);

        update.setString(1, request.getName());
        update.setDouble(2, request.getValue());
        update.setInt(3, request.getTechLead());
        update.setInt(4, request.getClientId());
        update.setDate(5, request.getStartDate());
        update.setDate(6, request.getEndDate());
        update.setInt(7, id);

        update.executeUpdate();
    }
}
