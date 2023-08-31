package org.kainos.ea.client;

public class DeliveryEmployeeProjectAssignmentDoesNotExistException extends Throwable {

    @Override
    public String getMessage() {
        return "The requested delivery employee project assignment does not exist.";
    }
}
