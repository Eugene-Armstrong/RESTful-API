package com.oocl.restfulapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.restfulapi.serviceImpl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
//@EnableSpringDataWebSupport
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
    public void should_get_all_employees_without_params(){
        //given
//        CustomerDto customerDto = new CustomerDto(1L, "Jason", "Zhong");
//        CustomerDto customerDto2 = new CustomerDto(2L, "Jason2", "Zhong2");
//        List<CustomerDto> customerDtos = Arrays.asList(customerDto, customerDto2);
//        when(customerService.getCustomerByPage(any())).thenReturn(customerDtos);
//        //when
//        ResultActions result = mvc.perform(get("/customers"));
//        //then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].content.id", is(1)))
//                .andExpect(jsonPath("$[0].content.firstName", containsString("Jason")))
//                .andExpect(jsonPath("$[0].content.lastName", is("Zhong")))
//                .andExpect(jsonPath("$[1].content.id", is(2)));
    }
}