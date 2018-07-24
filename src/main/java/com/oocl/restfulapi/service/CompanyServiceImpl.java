package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyServiceImpl implements CompanyService {

    ArrayList<Employee> employeesList = new ArrayList<Employee>(){{
        add(new Employee(0,"小马",22,"male",6000));
        add(new Employee(1,"小董",20,"female",5000));
        add(new Employee(2,"小王",29,"male",8000));
        add(new Employee(3,"小刘",19,"male",3000));
        add(new Employee(4,"小卢",23,"male",7000));
        add(new Employee(5,"小李",29,"female",2000));
    }};

    ArrayList<Company> companyList = new ArrayList<Company>(){{
        add(new Company("c1",1,employeesList));
        add(new Company("c2",2,employeesList));
        add(new Company("c3",3,employeesList));
    }};

    /**
     * 获取employee列表
     */
    @Override
    public ArrayList<Company> getCompanyList(){
        return companyList;
    }

    @Override
    public Company queryCompany(String name) {
        try {
            for(int i=0;i<companyList.size();i++){
                if(companyList.get(i).getCompanyName().equals(name)){
                    return companyList.get(i);
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Company> handlePage(int page, int pageSize) {
        return null;
    }

    @Override
    public ArrayList<Company> getAllEmployeesFromACompany() {
        return null;
    }

    @Override
    public void addCompany(Company Company) {

    }

    @Override
    public void updateCompany(String name, Company Company) {

    }

    @Override
    public void deleteCompany(String name) {

    }

    /**
     * 获取某个具体employee
     */


    /**
     * 分页查询，page等于1，pageSize等于5
     */


    /**
     * 筛选出所有男性Employee
     */


    /**
     * 增加一个employee
     */


    /**
     * 更新某个具体employee
     */


    /**
     * 删除某个employee
     */

}
