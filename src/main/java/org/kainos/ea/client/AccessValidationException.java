package org.kainos.ea.client;

public class AccessValidationException extends Throwable {

    @Override
    public String getMessage() {
        return "Failed to validate access";
    }
}
