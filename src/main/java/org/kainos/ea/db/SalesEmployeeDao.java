package org.kainos.ea.db;

import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT EmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate " +
                "FROM SalesEmployees " +
                "LEFT JOIN Employees USING (EmployeeID)");

        List<SalesEmployee> SalesEmployeeList = new ArrayList<>();

        while(rs.next()) {
            SalesEmployee salesEmployee = new SalesEmployee(
                    rs.getInt("EmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getFloat("CommissionRate")
            );

            SalesEmployeeList.add(salesEmployee);
        }
        return SalesEmployeeList;
    }
    public SalesEmployee getSalesEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT EmployeeID, Name, Salary, BankAccountNumber, NationalInsuranceNumber, CommissionRate " +
                "FROM SalesEmployees " +
                "LEFT JOIN Employees USING (EmployeeID) " +
                "WHERE EmployeeID=?";

        PreparedStatement st = c.prepareStatement(selectStatement);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            return new SalesEmployee(
                    rs.getInt("EmployeeID"),
                    rs.getString("Name"),
                    rs.getDouble("Salary"),
                    rs.getString("BankAccountNumber"),
                    rs.getString("NationalInsuranceNumber"),
                    rs.getFloat("CommissionRate")
            );
        }
        return null;
    }

    public int createSalesEmployee(SalesEmployeeRequest request) throws SQLException {
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

            String insertSalesEmployeesQuery = "INSERT INTO SalesEmployees (EmployeeID, CommissionRate) " +
                    "VALUES (?,?)";

            PreparedStatement insertSalesEmployees = c.prepareStatement(insertSalesEmployeesQuery, Statement.RETURN_GENERATED_KEYS);

            insertSalesEmployees.setInt(1, insertedId);
            insertSalesEmployees.setDouble(2, request.getCommissionRate());

            insertSalesEmployees.executeUpdate();

            ResultSet rs = insertEmployees.getGeneratedKeys();

            if (rs.next()) {
                c.commit();
                return insertedId;
            }

            c.rollback();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
        return -1;
        }

    public void updateSalesEmployee(int id, SalesEmployeeRequest request) throws SQLException {
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

        String query2 = "UPDATE SalesEmployees SET " +
                "CommissionRate = ?" +
                "WHERE EmployeeID = ?";

        PreparedStatement statement2 = c.prepareStatement(query2);

        statement2.setDouble(1, request.getCommissionRate());
        statement2.setInt(2, id);

        statement2.executeUpdate();
    }

    public void deleteSalesEmployee(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        try {
            c.setAutoCommit(false);

            String deleteFromEmployeesQuery =
                    "DELETE FROM Employees WHERE EmployeeID = ?";
            String deleteFromDeliveryEmployeesQuery =
                    "DELETE FROM SalesEmployees WHERE EmployeeID = ?";

            PreparedStatement deleteFromEmployees = c.prepareStatement(deleteFromEmployeesQuery);
            PreparedStatement deleteFromSalesEmployees = c.prepareStatement(deleteFromDeliveryEmployeesQuery);

            deleteFromEmployees.setInt(1, id);
            deleteFromSalesEmployees.setInt(1, id);

            deleteFromSalesEmployees.executeUpdate();
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