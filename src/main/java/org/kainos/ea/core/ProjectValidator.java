package org.kainos.ea.core;

import org.kainos.ea.cli.ProjectRequest;

public class ProjectValidator {
    public String isValidProject(ProjectRequest request) {
        if (request.getName().length() > 100) {
            return "Name must be fewer than 100 characters";
        }

        if (request.getValue() < 0) {
            return "Value cannot be negative";
        }

        return null;
    }
}
