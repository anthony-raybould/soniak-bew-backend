package org.kainos.ea.client;

public class FailedToCreateSalesEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create sales employee";
    }
}
