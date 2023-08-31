package org.kainos.ea.cli;

public class Employee {
    private int employeeID;
    private String name;
    private double salary;
    private String bankAccountNumber;
    private String niNumber;

    public Employee(int employeeID, String name, double salary, String bankAccountNumber, String niNumber) {
        this.employeeID = employeeID;
        this.name = name;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.niNumber = niNumber;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getNiNumber() {
        return niNumber;
    }

    public void setNiNumber(String niNumber) {
        this.niNumber = niNumber;
    }
}
