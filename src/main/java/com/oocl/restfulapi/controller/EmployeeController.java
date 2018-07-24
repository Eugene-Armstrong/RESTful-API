package com.oocl.restfulapi.controller;

import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceIpml;

    /**
     * 获取employee列表
     */
    @GetMapping("")
    public ArrayList<Employee> getEmployeeLists(){
        return employeeServiceIpml.getEmployeeList();
    }

    /**
     * 获取某个具体employee
     */
    @GetMapping("{id}")
    public Employee queryEmployee(@PathVariable int id){
        return employeeServiceIpml.queryEmployee(id);
    }

    /**
     * 分页查询，page等于1，pageSize等于5
     */
    @GetMapping("page/{page}/pageSize/{pageSize}")
    public ArrayList<Employee> handlePage(@PathVariable int page, @PathVariable int pageSize){
        return employeeServiceIpml.handlePage(page,pageSize);
    }
//
//    /**
//     * 筛选出所有男性Employee
//     */
//    @GetMapping("male")
//    public ArrayList<Employee> getAllMaleEmployees(){
//        return employeeServiceIpml.getAllMaleEmployees();
//    }
//
//    /**
//     * 增加一个employee
//     */
//    @PostMapping("")
//    public ArrayList<Employee> addEmployee(@RequestBody Employee employee){
//        employeeServiceIpml.addEmployee(employee);
//        return employeeServiceIpml.getEmployeeList();
//    }
//
//    /**
//     * 更新某个具体employee
//     */
//    @PutMapping("{id}")
//    public ArrayList<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employee){
//        employeeServiceIpml.updateEmployee(id,employee);
//        return employeeServiceIpml.getEmployeeList();
//    }
//
//    /**
//     * 删除某个employee
//     */
//    @DeleteMapping("{id}")
//    public ArrayList<Employee> deleteEmployee(@PathVariable int id){
//        employeeServiceIpml.deleteEmployee(id);
//        return employeeServiceIpml.getEmployeeList();
//    }

}
