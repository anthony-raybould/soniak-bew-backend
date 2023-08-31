package org.kainos.ea.client;

public class FailedToAssignDeliveryEmployeesException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to assign employees to project in database";
    }
}
