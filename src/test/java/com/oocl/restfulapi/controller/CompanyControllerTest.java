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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Test
    public void should_get_company_by_name() throws Exception{
        //given
        String companyName = "OOCL";
        Employee employee1 = new Employee(0, "Jack", 22,"male",5000);
        Employee employee2 = new Employee(1, "Rose", 20,"female",4000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        Company company = new Company(companyName, employees);
        //when
        when(companyServiceImpl.queryCompany(companyName)).thenReturn(company);
        ResultActions resultActions = mockMvc.perform(get("/companies/OOCL",companyName));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", containsString("OOCL")))
                .andExpect(jsonPath("$.employees[0].name", is("Jack")))
                .andExpect(jsonPath("$.employees[1].name", is("Rose")));
    }
    @Test
    public void should_get_companies_by_page() throws Exception{
        //given
        int page=1,pageSize=5;
        Employee employee1 = new Employee(0, "a", 21,"male",1000);
        Employee employee2 = new Employee(1, "b", 22,"male",2000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        Company company = new Company("OOCL", employees);
        //when
        when(companyServiceImpl.handlePage(page,pageSize)).thenReturn(Arrays.asList(company));
        ResultActions resultActions = mockMvc.perform(get("/companies/page/1/pageSize/5",page,pageSize));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyName", containsString("OOCL")))
                .andExpect(jsonPath("$[0].employees[0].name", is("a")))
                .andExpect(jsonPath("$[0].employees[1].name", is("b")));
    }

    @Test
    public void should_add_company_successfully() throws Exception{
        //given
        Employee employee1 = new Employee(0, "a", 21,"male",1000);
        Employee employee2 = new Employee(1, "b", 22,"male",2000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        Company company = new Company("OOCL", employees);
        companyServiceImpl.addCompany(company);
        //when
        when(companyServiceImpl.getCompanyList()).thenReturn(Arrays.asList(company));
        ResultActions resultActions = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(company)));
        //then
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void should_update_companiesList_given_name_and_employees() throws Exception {
        //given
        String companyName = "test";
        Employee employee1 = new Employee(0, "a", 21,"male",1000);
        Employee employee2 = new Employee(1, "b", 22,"male",2000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        Company company = new Company(companyName, employees);
        when(companyServiceImpl.updateCompany(companyName)).thenReturn(true);
        //when
        ResultActions result = mockMvc.perform
                (put("/companies/test", company.getCompanyName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(company))
                );
        //then
        result.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void should_delete_company_by_name() throws Exception{
        //given
        String companyName = "ali";
        Employee employee1 = new Employee(0, "a", 21,"male",1000);
        Employee employee2 = new Employee(1, "b", 22,"male",2000);
        List<Employee> employees = Arrays.asList(employee1,employee2);
        Company company = new Company(companyName, employees);
        //when
        when(companyServiceImpl.deleteCompany(companyName)).thenReturn(true);
        ResultActions resultActions = mockMvc
                .perform(delete("/companies/ali",company.getCompanyName()));
        //then
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}