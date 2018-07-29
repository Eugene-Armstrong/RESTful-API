package com.oocl.restfulapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.restfulapi.controller.dto.EmployeeDTO;
import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.serviceImpl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_get_all_employees() throws Exception{
        //given
        Employee employee1 = new Employee(0, "Jack", 22,"male",5000);
        Employee employee2 = new Employee(1, "Rose", 20,"female",4000);
        List<Employee> employees = Arrays.asList(employee1, employee2);
        //when
        when(employeeServiceImpl.getEmployeeList()).thenReturn(employees);
        ResultActions resultActions = mockMvc.perform(get("/employees"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", containsString("Jack")))
                .andExpect(jsonPath("$[0].age", is(22)))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[0].salary", is(5000)))
                .andExpect(jsonPath("$[1].id", is(1)));
    }

    @Test
    public void should_get_employee_by_id() throws Exception{
        //given
        int id = 0;
        Employee employee = new Employee(id, "Jack", 22,"male",5000);
        //when
        when(employeeServiceImpl.queryEmployee(id)).thenReturn(employee);
        ResultActions resultActions = mockMvc.perform(get("/employees/0",id));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", containsString("Jack")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.gender", is("male")))
                .andExpect(jsonPath("$.salary", is(5000)));
    }

    @Test
    public void should_get_employees_by_page() throws Exception{
        //given
        int page=1,pageSize=5;
        Employee employee1 = new Employee(0, "a", 21,"male",1000);
        Employee employee2 = new Employee(1, "b", 22,"male",2000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        //when
        when(employeeServiceImpl.handlePage(page,pageSize)).thenReturn(employees);
        ResultActions resultActions = mockMvc.perform(get("/employees/page/1/pageSize/5",page,pageSize));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", containsString("a")))
                .andExpect(jsonPath("$[0].age", is(21)))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[0].salary", is(1000)));
    }

//    @Test
//    public void should_add_employee_successfully() throws Exception{
//        //given
//        Employee employee = new Employee(0, "Jack", 21,"male",5000);
//        List<Employee> employees = Arrays.asList(employee);
//        employeeServiceImpl.addEmployee(employee);
//        //when
//        when(employeeServiceImpl.getEmployeeList()).thenReturn(employees);
//        ResultActions resultActions = mockMvc.perform(post("/employees",employee));
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(0)))
//                .andExpect(jsonPath("$[0].name", containsString("Jack")))
//                .andExpect(jsonPath("$[0].age", is(21)))
//                .andExpect(jsonPath("$[0].gender", is("male")))
//                .andExpect(jsonPath("$[0].salary", is(5000)));
//    }

    @Test
    public void should_delete_employee_by_id() throws Exception{
        //given
        int id = 0;
        Employee employee = new Employee(id, "Jack", 18,"male",5000);
        //when
        when(employeeServiceImpl.deleteEmployee(id)).thenReturn(true);
        ResultActions resultActions = mockMvc.perform(delete("/employees/0",employee.getId()));
        //then
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}