package com.oocl.restfulapi.controller.dto;


import com.oocl.restfulapi.pojo.Employee;

public class EmployeeDTO {
    private final int id;
    private final String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
    }
}
