package org.kainos.ea.client;

public class FailedToGenerateTokenException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to generate token";
    }
}
