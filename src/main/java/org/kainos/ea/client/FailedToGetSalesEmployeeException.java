package org.kainos.ea.client;

public class FailedToGetSalesEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get Sales Employee";
    }
}
