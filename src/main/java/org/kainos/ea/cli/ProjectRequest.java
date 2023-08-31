package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProjectRequest {

    private String name;
    private double value;
    private Integer techLead;
    private int clientId;
    private Date startDate;
    private Date endDate;


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

    @JsonCreator
    public ProjectRequest(
            @JsonProperty("name") String name,
            @JsonProperty("value") double value,
            @JsonProperty("techLead") int techLead,
            @JsonProperty("clientId") int clientId,
            @JsonProperty("startDate") Date startDate,
            @JsonProperty("endDate") Date endDate
    ) {
        this.name = name;
        this.value = value;
        this.techLead = techLead;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
