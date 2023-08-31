package org.kainos.ea.client;

public class DeliveryEmployeeAlreadyAssignedToProjectException extends Throwable {

    @Override
    public String getMessage() {
        return "Delivery employee is already assigned to the project.";
    }
}
