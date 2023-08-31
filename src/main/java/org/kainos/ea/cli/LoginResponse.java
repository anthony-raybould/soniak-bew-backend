package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    private String token;
    private String username;
    private Role role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonCreator
    public LoginResponse(
            @JsonProperty("token") String token,
            @JsonProperty("username") String username,
            @JsonProperty("role") Role role
    ) {
        this.token = token;
        this.username = username;
        this.role = role;
    }
}
