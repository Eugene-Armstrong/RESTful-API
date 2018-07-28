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
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@EnableSpringDataWebSupport
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
    public void should_get_all_employees(){
        //given
        Employee employee1 = new Employee(0, "Jason", 22,"male",5000);
        Employee employee2 = new Employee(1, "Rose", 20,"female",4000);
        List<Employee> employees = Arrays.asList(employee1, employee2);
        //when
        when(employeeServiceImpl.getEmployeeList()).thenReturn(employees);
        try {
            ResultActions resultActions = mockMvc.perform(get("employees"));
            //then
            resultActions.andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].content.id", is(0)))
                    .andExpect(jsonPath("$[0].content.name", containsString("Jason")))
                    .andExpect(jsonPath("$[0].content.age", is(22)))
                    .andExpect(jsonPath("$[0].content.gender", is("male")))
                    .andExpect(jsonPath("$[0].content.salary", is(5000)))
                    .andExpect(jsonPath("$[1].content.id", is(1)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}