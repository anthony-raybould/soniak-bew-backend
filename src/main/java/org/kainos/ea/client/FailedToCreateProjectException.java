package org.kainos.ea.client;

public class FailedToCreateProjectException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create project";
    }
}
