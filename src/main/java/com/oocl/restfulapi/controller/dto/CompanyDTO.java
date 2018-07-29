package com.oocl.restfulapi.controller.dto;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import java.util.List;

public class CompanyDTO {
    private final String name;
    private final List<Employee> employees;

    public List<Employee> getEmployees() {
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
