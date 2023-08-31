package org.kainos.ea.client;

public class FailedToUpdateSalesEmployeeException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to Update Employee";
    }
}
