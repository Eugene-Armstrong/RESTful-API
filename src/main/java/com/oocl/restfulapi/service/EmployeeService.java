package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    //获取employee列表
    List<Employee> getEmployeeList();

    //获取某个具体employee
    Employee queryEmployee(int id);

    //分页查询，page等于1，pageSize等于5
    List<Employee> handlePage(int page, int pageSize);

    //筛选出所有男性Employee
    List<Employee> getAllMaleEmployees();

    //增加一个employee
    void addEmployee(Employee employee);

    //更新某个employee
    void updateEmployee(int id,Employee employee);

    //删除某个employee
    void deleteEmployee(int id);


}
