package org.kainos.ea.db;

import org.kainos.ea.cli.DeliveryEmployee;
import org.kainos.ea.cli.DeliveryEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();

        String query = "SELECT EmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber " +
                "FROM DeliveryEmployees " +
                "LEFT JOIN Employees USING (EmployeeID)";

        PreparedStatement st = c.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        while (rs.next()) {
            deliveryEmployees.add(new DeliveryEmployee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }

        return deliveryEmployees;
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String query = "SELECT EmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber " +
                "FROM DeliveryEmployees " +
                "LEFT JOIN Employees USING (EmployeeID) " +
                "WHERE EmployeeID = ?";

        PreparedStatement st = c.prepareStatement(query);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            return new DeliveryEmployee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }

        return null;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest request) throws SQLException {
        Connection c = databaseConnector.getConnection();

        try {
            c.setAutoCommit(false);

            String insertEmployeesQuery = "INSERT INTO Employees (Name, Salary, BankAccountNumber, NationalInsuranceNumber) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement insertEmployees = c.prepareStatement(insertEmployeesQuery, Statement.RETURN_GENERATED_KEYS);

            insertEmployees.setString(1, request.getName());
            insertEmployees.setDouble(2, request.getSalary());
            insertEmployees.setString(3, request.getBankAccountNumber());
            insertEmployees.setString(4, request.getNiNumber());

            insertEmployees.executeUpdate();

            ResultSet employeeResult = insertEmployees.getGeneratedKeys();

            int insertedId;
            if (employeeResult.next()) {
                insertedId = employeeResult.getInt(1);
            } else {
                c.rollback();
                return -1;
            }

            String insertDeliveryEmployeesQuery = "INSERT INTO DeliveryEmployees (EmployeeID) " +
                    "VALUES (?)";

            PreparedStatement insertDeliveryEmployees = c.prepareStatement(insertDeliveryEmployeesQuery, Statement.RETURN_GENERATED_KEYS);

            insertDeliveryEmployees.setInt(1, insertedId);

            insertDeliveryEmployees.executeUpdate();

            ResultSet rs = insertEmployees.getGeneratedKeys();

            if (rs.next()) {
                c.commit();
                return insertedId;
            }

            c.rollback();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            c.setAutoCommit(true);
        }
        return -1;
    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest request) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String query = "UPDATE Employees SET " +
                "Name = ?," +
                "Salary = ?," +
                "BankAccountNumber = ?," +
                "NationalInsuranceNumber = ?" +
                "WHERE EmployeeID = ?";

        PreparedStatement statement = c.prepareStatement(query);

        statement.setString(1, request.getName());
        statement.setDouble(2, request.getSalary());
        statement.setString(3, request.getBankAccountNumber());
        statement.setString(4, request.getNiNumber());
        statement.setInt(5, id);

        statement.executeUpdate();
    }

    public void deleteDeliveryEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        try {
            c.setAutoCommit(false);

            String deleteFromEmployeesQuery =
                    "DELETE FROM Employees WHERE EmployeeID = ?";
            String deleteFromDeliveryEmployeesQuery =
                    "DELETE FROM DeliveryEmployees WHERE EmployeeID = ?";

            PreparedStatement deleteFromEmployees = c.prepareStatement(deleteFromEmployeesQuery);
            PreparedStatement deleteFromDeliveryEmployees = c.prepareStatement(deleteFromDeliveryEmployeesQuery);

            deleteFromEmployees.setInt(1, id);
            deleteFromDeliveryEmployees.setInt(1, id);

            deleteFromDeliveryEmployees.executeUpdate();
            deleteFromEmployees.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            c.setAutoCommit(true);
        }
    }
}
