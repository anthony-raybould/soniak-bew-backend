package org.kainos.ea.cli;

import java.sql.Date;

public class Project {

    private int projectId;
    private String name;
    private double value;
    private Integer techLead;
    private int clientId;
    private Date startDate;
    private Date endDate;

    public Project(int projectId, String name, double value, Integer techLead, int clientId, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.name = name;
        this.value = value;
        this.techLead = techLead;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Integer getTechLead() {
        return techLead;
    }

    public void setTechLead(Integer techLead) {
        this.techLead = techLead;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
