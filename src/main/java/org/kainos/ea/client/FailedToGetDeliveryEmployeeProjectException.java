package org.kainos.ea.client;

public class FailedToGetDeliveryEmployeeProjectException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to get the delivery employee project assignment from the database.";
    }
}
