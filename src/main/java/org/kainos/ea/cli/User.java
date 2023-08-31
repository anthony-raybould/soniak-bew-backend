package org.kainos.ea.cli;

public class User {
    private final int userId;
    private final String username;
    private final Role role;

    public User(int userId, String username, Role role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}
