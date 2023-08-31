package org.kainos.ea.cli;

public class ClientSalesEmployee {
    private String clientName;

    private int clientId;
    private String salesEmployeeName;
    private String projects;

    public ClientSalesEmployee(String clientName, int clientId, String salesEmployeeName, String projects) {
        this.clientName = clientName;
        this.clientId = clientId;
        this.salesEmployeeName = salesEmployeeName;
        this.projects = projects;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSalesEmployeeName() {
        return salesEmployeeName;
    }

    public void setSalesEmployeeName(String salesEmployeeName) {
        this.salesEmployeeName = salesEmployeeName;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
