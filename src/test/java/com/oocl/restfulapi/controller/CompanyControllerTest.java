package com.oocl.restfulapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.restfulapi.pojo.Company;
import com.oocl.restfulapi.pojo.Employee;
import com.oocl.restfulapi.serviceImpl.CompanyServiceImpl;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_get_all_companies() throws Exception{
        //given
        Employee employee1 = new Employee(0, "Jack", 22,"male",5000);
        Employee employee2 = new Employee(1, "Rose", 20,"female",4000);

        Employee employee3 = new Employee(0, "LotsOfHomework", 28,"male",8000);

        List<Employee> employees = Arrays.asList(employee1,employee2);
        List<Employee> employees1 = Arrays.asList(employee3);

        Company company1 = new Company("OOCL", employees);
        Company company2 = new Company("ITA",employees1);
        List<Company> companies = Arrays.asList(company1,company2);

        //when
        when(companyServiceImpl.getCompanyList()).thenReturn(companies);
        ResultActions resultActions = mockMvc.perform(get("/companies"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].companyName", containsString("OOCL")))
                .andExpect(jsonPath("$[0].employees[0].name", is("Jack")))
                .andExpect(jsonPath("$[0].employees[1].name", is("Rose")))
                .andExpect(jsonPath("$[1].companyName", containsString("ITA")))
                .andExpect(jsonPath("$[1].employees[0].name", is("LotsOfHomework")));
    }
    
}