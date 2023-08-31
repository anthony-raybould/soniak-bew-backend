package org.kainos.ea.client;

public class FailedToRemoveDeliveryEmployeeProjectException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to delete delivery employee project assignment from the database.";
    }
}
