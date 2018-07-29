package com.oocl.restfulapi.serviceImpl;

import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    List<Employee> employeesList1 = new ArrayList<Employee>(){{
        add(new Employee(0,"小马",22,"male",6000));
    }};

    List<Employee> employeesList2 = new ArrayList<Employee>(){{
        add(new Employee(0,"小王",29,"male",8000));
        add(new Employee(1,"小董",20,"female",5000));
    }};

    List<Employee> employeesList3 = new ArrayList<Employee>(){{
        add(new Employee(0,"小刘",19,"male",3000));
        add(new Employee(1,"小卢",23,"male",7000));
        add(new Employee(2,"小李",29,"female",2000));
    }};

    List<Company> companyList = new ArrayList<Company>(){{
        add(new Company("c1",employeesList1));
        add(new Company("c2",employeesList2));
        add(new Company("c3",employeesList3));
    }};

    /**
     * 获取Company列表
     */
    @Override
    public List<Company> getCompanyList(){
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
    public List<Company> handlePage(int page, int pageSize) {
        List<Company> companies = new ArrayList<>();
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
    public List<Employee> getAllEmployeesFromACompany(String name) {
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
                break;
            }
        }
    }

    /**
     * 删除某个company以及名下所有employees
     */
    @Override
    public boolean deleteCompany(String name) {
        for(int i=0;i<companyList.size();i++){
            if(companyList.get(i).getCompanyName().equals(name)){
                String tmp = companyList.get(i).getCompanyName();
                companyList.remove(i);
                return tmp!=name;
            }
        }
        return false;
    }

}
