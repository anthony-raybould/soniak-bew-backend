package org.kainos.ea.client;

public class FailedToDeleteDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to delete delivery employee";
    }
}
