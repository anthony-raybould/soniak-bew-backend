package org.kainos.ea.core;

import org.kainos.ea.cli.SalesEmployeeRequest;

public class SalesEmployeeValidator {
    public String isValidSalesEmployee(SalesEmployeeRequest request) {
        if (request.getName().length() > 70) {
            return "Name should be less than or equal to 70 characters";
        }
        if (request.getBankAccountNumber().length() > 8) {
            return "Bank account number must be less than or equal to 8 characters";
        }

        if (request.getNiNumber().length() != 9) {
            return "National Insurance number must be 9 characters";
        }

        if (request.getSalary() < 0) {
            return "Salary cannot be negative";
        }

        if (request.getCommissionRate() < 0 || request.getCommissionRate() > 100) {
            return "Invalid commission rate";
        }

        return null;
    }
}
