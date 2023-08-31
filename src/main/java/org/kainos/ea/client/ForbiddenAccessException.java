package org.kainos.ea.client;

public class ForbiddenAccessException extends Exception {

    @Override
    public String getMessage() {
        return "Access to this resource is forbidden";
    }
}
