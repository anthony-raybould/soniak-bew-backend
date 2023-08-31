package org.kainos.ea.client;

public class DeliveryEmployeeDoesNotExistException extends Exception {
    private final int id;

    public DeliveryEmployeeDoesNotExistException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Delivery employee with ID " + id + " does not exist";
    }
}
