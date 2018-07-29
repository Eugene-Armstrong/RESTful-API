package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;

import java.util.List;

public interface CompanyService {
    //获取company列表
    List<Company> getCompanyList();

    //获取某个具体Company
    Company queryCompany(String name);

    //分页查询，page等于1，pageSize等于5
    List<Company> handlePage(int page, int pageSize);

    //获取某个具体company下所有employee列表
    List<Employee> getAllEmployeesFromACompany(String name);

    //增加一个Company
    void addCompany(Company Company);

    //更新某个Company
    boolean updateCompany(String name);

    //删除某个company以及名下所有employees
    boolean deleteCompany(String name);


}
