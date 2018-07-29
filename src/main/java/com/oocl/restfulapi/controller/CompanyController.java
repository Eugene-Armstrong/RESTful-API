package com.oocl.restfulapi.controller;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.serviceImpl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companiesServiceIpml;

    /**
     * 获取companies列表
     */
    @GetMapping("")
    public List<Company> getCompanyList(){
        return companiesServiceIpml.getCompanyList();
    }

    /**
     * 获取某个具体company
     */
    @GetMapping("{name}")
    public Company queryCompany(@PathVariable String name){
        return companiesServiceIpml.queryCompany(name);
    }

    /**
     * 分页查询，page等于1，pageSize等于5
     */
    @GetMapping("page/{page}/pageSize/{pageSize}")
    public List<Company> handlePage(@PathVariable int page, @PathVariable int pageSize){
        return companiesServiceIpml.handlePage(page,pageSize);
    }

    /**
     * 获取某个具体company下所有employee列表
     */
    @GetMapping("{name}/employees")
    public List<Employee> getAllEmployeesFromACompany(@PathVariable String name){
        return companiesServiceIpml.getAllEmployeesFromACompany(name);
    }

    /**
     * 增加一个company
     */
    @PostMapping("")
    public List<Company> addCompany(@RequestBody Company company){
        companiesServiceIpml.addCompany(company);
        return companiesServiceIpml.getCompanyList();
    }

    /**
     * 更新某个具体company
     */
    @PutMapping("{name}")
    public List<Company> updateCompany(@PathVariable String name){
        companiesServiceIpml.updateCompany(name);
        return companiesServiceIpml.getCompanyList();
    }

    /**
     * 删除某个company
     */
    @DeleteMapping("{name}")
    public List<Company> deleteCompany(@PathVariable String name){
        companiesServiceIpml.deleteCompany(name);
        return companiesServiceIpml.getCompanyList();
    }

}
