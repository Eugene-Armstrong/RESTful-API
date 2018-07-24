package com.oocl.restfulapi.controller;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companiesServiceIpml;

    /**
     * 获取companies列表
     */
    @GetMapping("")
    public ArrayList<Company> getCompanyList(){
        return companiesServiceIpml.getCompanyList();
    }

    /**
     * 获取某个具体companies
     */
    @GetMapping("{name}")
    public Company queryCompany(@PathVariable String name){
        return companiesServiceIpml.queryCompany(name);
    }

    /**
     * 分页查询，page等于1，pageSize等于5
     */
    @GetMapping("page/{page}/pageSize/{pageSize}")
    public ArrayList<Company> handlePage(@PathVariable int page, @PathVariable int pageSize){
        return companiesServiceIpml.handlePage(page,pageSize);
    }

    /**
     * 获取某个具体company下所有employee列表
     */
    @GetMapping("{name}/employees")
    public ArrayList<Employee> getAllEmployeesFromACompany(@PathVariable String name){
        return companiesServiceIpml.getAllEmployeesFromACompany(name);
    }

    /**
     * 增加一个company
     */
    @PostMapping("")
    public ArrayList<Company> addcompanies(@RequestBody Company companies){
        companiesServiceIpml.addCompany(companies);
        return companiesServiceIpml.getCompanyList();
    }

    /**
     * 更新某个具体company
     */
//    @PutMapping("{id}")
//    public ArrayList<companies> updatecompanies(@PathVariable int id,@RequestBody companies companies){
//        companiesServiceIpml.updatecompanies(id,companies);
//        return companiesServiceIpml.getcompaniesList();
//    }

    /**
     * 删除某个company
     */
//    @DeleteMapping("{id}")
//    public ArrayList<companies> deletecompanies(@PathVariable int id){
//        companiesServiceIpml.deletecompanies(id);
//        return companiesServiceIpml.getcompaniesList();
//    }

}
