package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Company;

import java.util.ArrayList;

public interface CompanyService {
    //获取company列表
    ArrayList<Company> getCompanyList();

    //获取某个具体Company
    Company queryCompany(String name);

    //分页查询，page等于1，pageSize等于5
    ArrayList<Company> handlePage(int page, int pageSize);

    //获取某个具体company下所有employee列表
    ArrayList<Company> getAllEmployeesFromACompany();

    //增加一个Company
    void addCompany(Company Company);

    //更新某个Company
    void updateCompany(String name, Company Company);

    //删除某个company以及名下所有employees
    void deleteCompany(String name);


}
