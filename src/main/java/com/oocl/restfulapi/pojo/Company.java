package com.oocl.restfulapi.pojo;

import java.util.ArrayList;

public class Company {
    private String companyName;
    private int employeesNumber;
    private ArrayList<Employee> employees;

    public Company(){

    }

    public Company(String companyName, int employeesNumber, ArrayList<Employee> employees){
        setCompanyName(companyName);
        setEmployeesNumber(employeesNumber);
        setEmployees(employees);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

}
