package org.kainos.ea.client;

public class SalesEmployeeDoesNotExistException extends Exception {
    private final int id;

    public SalesEmployeeDoesNotExistException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Sales employee employee with ID " + id + " does not exist";
    }
}
