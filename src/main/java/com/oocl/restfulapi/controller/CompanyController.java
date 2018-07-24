package com.oocl.restfulapi.controller;

import com.oocl.restfulapi.pojo.Company;
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
     * 筛选出所有男性companies
     */
//    @GetMapping("male")
//    public ArrayList<companies> getAllMalecompaniess(){
//        return companiesServiceIpml.getAllMalecompaniess();
//    }

    /**
     * 增加一个companies
     */
//    @PostMapping("")
//    public ArrayList<companies> addcompanies(@RequestBody companies companies){
//        companiesServiceIpml.addcompanies(companies);
//        return companiesServiceIpml.getcompaniesList();
//    }

    /**
     * 更新某个具体companies
     */
//    @PutMapping("{id}")
//    public ArrayList<companies> updatecompanies(@PathVariable int id,@RequestBody companies companies){
//        companiesServiceIpml.updatecompanies(id,companies);
//        return companiesServiceIpml.getcompaniesList();
//    }

    /**
     * 删除某个companies
     */
//    @DeleteMapping("{id}")
//    public ArrayList<companies> deletecompanies(@PathVariable int id){
//        companiesServiceIpml.deletecompanies(id);
//        return companiesServiceIpml.getcompaniesList();
//    }

}
