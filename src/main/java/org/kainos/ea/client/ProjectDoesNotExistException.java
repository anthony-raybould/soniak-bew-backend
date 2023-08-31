package org.kainos.ea.client;

public class ProjectDoesNotExistException extends Throwable {

    @Override
    public String getMessage() {
        return "The requested project does not exist.";
    }
}
