package org.kainos.ea.client;

public class FailedToGetSalesEmployeesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get Sales Employees";
    }
}
