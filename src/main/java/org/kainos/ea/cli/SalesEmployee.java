package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    public SalesEmployee(int employeeID, String name, double salary, String bankAccountNumber, String niNumber, float commissionRate) {
        super(employeeID, name, salary, bankAccountNumber, niNumber);
        this.commissionRate = commissionRate;
    }
    private float commissionRate;

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }
}
