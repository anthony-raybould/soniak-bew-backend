package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DeliveryEmployeeRequest {

    private String name;
    private double salary;
    private String bankAccountNumber;
    private String niNumber;

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

    @JsonCreator
    public DeliveryEmployeeRequest(
            @JsonProperty("name") String name,
            @JsonProperty("salary") double salary,
            @JsonProperty("bankAccountNumber") String bankAccountNumber,
            @JsonProperty("niNumber") String niNumber
    ) {
        this.name = name;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.niNumber = niNumber;
    }
}
