package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Employee;

import java.util.ArrayList;

public interface EmployeeService {
    //获取employee列表
    ArrayList<Employee> getEmployeeList();

    //获取某个具体employee
    Employee queryEmployee(int id);

    //分页查询，page等于1，pageSize等于5
    ArrayList<Employee> handlePage(int page, int pageSize);

    //筛选出所有男性Employee
    ArrayList<Employee> getAllMaleEmployees();

    //增加一个employee
    void addEmployee(Employee employee);

    //更新某个employee
    void updateEmployee(int id,Employee employee);

    //删除某个employee
    void deleteEmployee(int id);


}
