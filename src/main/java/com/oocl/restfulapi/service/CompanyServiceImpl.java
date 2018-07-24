package com.oocl.restfulapi.service;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyServiceImpl implements CompanyService {

    ArrayList<Employee> employeesList1 = new ArrayList<Employee>(){{
        add(new Employee(0,"小马",22,"male",6000));
        add(new Employee(1,"小董",20,"female",5000));
    }};

    ArrayList<Employee> employeesList2 = new ArrayList<Employee>(){{
        add(new Employee(0,"小王",29,"male",8000));
        add(new Employee(1,"小刘",19,"male",3000));
        add(new Employee(2,"小卢",23,"male",7000));
        add(new Employee(3,"小李",29,"female",2000));
    }};

    ArrayList<Employee> employeesList3 = new ArrayList<Employee>(){{
        add(new Employee(0,"小a",22,"male",6000));
        add(new Employee(1,"小b",20,"female",5000));
        add(new Employee(2,"小c",29,"male",8000));
        add(new Employee(3,"小d",19,"male",3000));
        add(new Employee(4,"小e",23,"male",7000));
        add(new Employee(5,"小f",29,"female",2000));
    }};

    ArrayList<Company> companyList = new ArrayList<Company>(){{
        add(new Company("c1",1,employeesList1));
        add(new Company("c2",2,employeesList2));
        add(new Company("c3",3,employeesList3));
    }};

    /**
     * 获取employee列表
     */
    @Override
    public ArrayList<Company> getCompanyList(){
        return companyList;
    }

    /**
     * 获取某个具体Company
     */
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

    /**
     * 分页查询，page等于1，pageSize等于5
     */
    @Override
    public ArrayList<Company> handlePage(int page, int pageSize) {
        ArrayList<Company> companies = new ArrayList<>();
        int start = (page-1)*pageSize,
                end = start+pageSize>companyList.size()
                        ?companyList.size():start+pageSize;
        for(int i=start;i<end;i++){
            companies.add(companyList.get(i));
        }
        return companies;
    }

    /**
     * 获取某个具体company下所有employee列表
     */
    @Override
    public ArrayList<Employee> getAllEmployeesFromACompany(String name) {
        for(Company company:companyList){
            if(company.getCompanyName().equals(name)){
                return company.getEmployees();
            }
        }
        return null;
    }

    /**
     * 增加一个Company
     */
    @Override
    public void addCompany(Company company) {
        companyList.add(company);
    }

    /**
     * 更新某个Company
     */
    @Override
    public void updateCompany(String name) {
        for(int i=0;i<companyList.size();i++){
            if(companyList.get(i).getCompanyName().equals(name)){
                companyList.get(i).setEmployees(employeesList1);
                companyList.get(i).setEmployeesNumber(employeesList1.size());
                break;
            }
        }
    }

    /**
     * 删除某个company以及名下所有employees
     */
    @Override
    public void deleteCompany(String name) {

    }

}
