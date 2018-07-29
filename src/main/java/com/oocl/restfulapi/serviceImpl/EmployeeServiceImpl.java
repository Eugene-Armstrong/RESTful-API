package com.oocl.restfulapi.serviceImpl;

import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employeesList = new ArrayList<Employee>(){{
        add(new Employee(0,"小马",22,"male",6000));
        add(new Employee(1,"小董",20,"female",5000));
        add(new Employee(2,"小王",29,"male",8000));
    }};

    /**
     * 获取employee列表
     */
    @Override
    public List<Employee> getEmployeeList(){
        return employeesList;
    }

    /**
     * 获取某个具体employee
     */
    @Override
    public Employee queryEmployee(int id) {
        try {
            for(int i=0;i<employeesList.size();i++){
                if(employeesList.get(i).getId()==id){
                    return employeesList.get(i);
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
    public List<Employee> handlePage(int page, int pageSize) {
        List<Employee> employees = new ArrayList<>();
        int start = (page-1)*pageSize,
                end = start+pageSize>employeesList.size()
                        ?employeesList.size():start+pageSize;
        for(int i=start;i<end;i++){
            employees.add(employeesList.get(i));
        }
        return employees;
    }

    /**
     * 筛选出所有男性Employee
     */
    @Override
    public List<Employee> getAllMaleEmployees() {
        List<Employee> allMale = new ArrayList<>();
        for(Employee employee:employeesList){
            if(employee.getGender().equals("male")){
                allMale.add(employee);
            }
        }
        return allMale;
    }

    /**
     * 增加一个employee
     */
    @Override
    public void addEmployee(Employee employee) {
        try {
            employeesList.add(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新某个具体employee
     */
    @Override
    public boolean updateEmployee(int id,Employee employee) {
        try {
            for(int i=0;i<employeesList.size();i++){
                if(employeesList.get(i).getId()==id){
                    employeesList.get(i).setName(employee.getName());
                    employeesList.get(i).setAge(employee.getAge());
                    employeesList.get(i).setGender(employee.getGender());
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除某个employee
     */
    @Override
    public boolean deleteEmployee(int id) {
        try {
            for(int i=0;i<employeesList.size();i++){
                if(employeesList.get(i).getId()==id){
                    employeesList.remove(i);
                    return employeesList.get(i).getId()!=id;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
