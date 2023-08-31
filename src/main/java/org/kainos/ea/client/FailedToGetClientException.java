package org.kainos.ea.client;

public class FailedToGetClientException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get clients from the database";
    }
}
