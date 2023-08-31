package org.kainos.ea.client;

public class FailedToUpdateProjectException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create project";
    }
}
