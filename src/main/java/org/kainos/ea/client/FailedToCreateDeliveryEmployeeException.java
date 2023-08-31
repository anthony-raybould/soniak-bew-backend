package org.kainos.ea.client;

public class FailedToCreateDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create delivery employee";
    }
}
