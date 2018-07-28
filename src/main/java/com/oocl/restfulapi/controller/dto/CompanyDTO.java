package com.oocl.restfulapi.controller.dto;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import java.util.ArrayList;

public class CompanyDTO {
    private final String name;
    private final ArrayList<Employee> employees;

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    public CompanyDTO(Company company) {
        this.name = company.getCompanyName();
        this.employees = company.getEmployees();
    }
}
