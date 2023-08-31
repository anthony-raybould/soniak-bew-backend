package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AssignDeliveryEmployeesRequest {

    private List<Integer> employeeIds;
    private int projectId;

    @JsonCreator
    public AssignDeliveryEmployeesRequest(@JsonProperty("employeeIds") List<Integer> employeeIds, @JsonProperty("projectId") int projectId) {
        this.employeeIds = employeeIds;
        this.projectId = projectId;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
