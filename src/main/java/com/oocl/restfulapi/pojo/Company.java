package com.oocl.restfulapi.pojo;

import java.util.List;

public class Company {
    private String companyName;
    private List<Employee> employees;

    public Company(){}

    public Company(String companyName, List<Employee> employees){
        setCompanyName(companyName);
        setEmployees(employees);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
