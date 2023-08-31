package org.kainos.ea.client;

public class FailedToGetDeliveryEmployeesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get delivery employee";
    }
}
